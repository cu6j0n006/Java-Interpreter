package ZubaLang;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

class Scanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int start = 0;
    private int current = 0;
    private int line = 1;

    private static Map<String, TokenType> keywords = new HashMap<>();
    static {
        keywords = new HashMap<>();
        keywords.put("na", TokenType.AND);
    }

    Scanner (String source){
        this.source = source;
    }
    List<Token> scanTokens(){
        while(!isAtEnd()){
            start = current;
            scanToken();
        }
        tokens.add(new Token(TokenType.EOF, "", null, line));
        return tokens;
    }

    private boolean isAtEnd(){
        return current >= source.length();
    }

    private void scanToken(){
        char c = advance();
        switch(c){
            case '(': addToken(TokenType.LEFT_PAREN);  break;
            case ')': addToken(TokenType.RIGHT_PAREN);  break;
            case '{': addToken(TokenType.LEFT_BRACE);  break;
            case '}': addToken(TokenType.RIGHT_BRACE);  break;
            case '.': addToken(TokenType.DOT);  break;
            case ';': addToken(TokenType.SEMICOLON);  break;
            case '-': addToken(TokenType.MINUS);  break;
            case '+': addToken(TokenType.PLUS);  break;
            case ',': addToken(TokenType.COMMA);  break;
            case '*': addToken(TokenType.STAR);  break;
            case '!':
                addToken(match('=') ? TokenType.BANG_EQUAL : TokenType.BANG);
                break;
            case '=':
                addToken(match('=') ? TokenType.EQUAL_EQUAL : TokenType.EQUAL);
                break;
            case '<':
                addToken(match('=') ? TokenType.LESS_EQUAL : TokenType.LESS);
                break;
            case '>':
                addToken(match('=') ? TokenType.GREATER_EQUAL : TokenType.GREATER);
                break;
            case '/': // we keep consuming the characters until the end of the line
                if (match('/')) {
                    // we check if it goes until the line ends
                    while(peek() != '\n' && !isAtEnd()) advance();
                } else {
                    addToken(TokenType.SLASH);
                }
                break;
            // Skipping character cases
            case ' ':
            case '\r':
            case '\t':
                //ignore whitespace
                break;
            case '\n':
                line++;
                break;
            case '"': string();  break;
            default:
                if (isDigit(c)){
                    number();
                } else if (isAlpha(c)) {
                    identifier();
                }else {
                    Zuba.error(line, "Unexpected character");
                }
                Zuba.error(line, "Unexpected character in expression");
                break;
        }
    }

    private char advance(){
        current += 1;
        return source.charAt(current-1);
    }

    private void addToken(TokenType type){
        addToken(type, null);
    }

    private void addToken(TokenType type, Object literal){
        String text  = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }

    private boolean match(char expected){
        if(isAtEnd()) return false;
        if(source.charAt(current) != expected) return false;
        current++;
        return true;
    }
    private char peek(){
        if(isAtEnd()) return '\0';
        return source.charAt(current);
    }
    private void string(){
        //if we're still between "" and not at the end of our file, we consume.
        while(peek()!='"' && !isAtEnd()){
            if(peek() == '\n') line++;
            advance();
        }
        if (isAtEnd()){
            Zuba.error(line, "Missing string literal");
            return;
        };
        //closing "
        advance();

        //Exctract string contents
        String text = source.substring(start+1, current-1);
        addToken(TokenType.STRING, text);
    }
    private boolean isDigit(char c){
        return c >= '0' && c <= '9';
    }
    //Consuming numbers
    private void number(){
        while(isDigit(peek())){
            advance();
        }
        if(peek() == '.' && isDigit(peekNext())){
            advance(); // we consume the .
            while(isDigit(peek())){
                advance(); // then consume the rest of the numbers after .
            }
        }

        addToken(TokenType.NUMBER, Double.parseDouble(source.substring(start, current)));
    }
    private char peekNext(){
        if (current +1 >=  source.length()) return '\0';//we are outOfBound
        return source.charAt(current+1);
    }
    private void identifier(){
        while (isAlphaNumeric(peek())) advance();
        addToken(TokenType.IDENTIFIER, source.substring(start, current));
    }
    private boolean isAlpha(char c){
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')||c=='_';
    }
    private boolean isAlphaNumeric(char c){
        return isAlpha(c) || isDigit(c);
    }

}

