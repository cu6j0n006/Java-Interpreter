package ZubaLang;

import java.util.ArrayList;
import java.util.List;

import static ZubaLang.TokenType.*;


public class Parser {
    private final List<Token> tokens;
    private int current = 0;
    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    //equality -> comparison ( ( "!=" | "==" ) comparison )* ;
    private Expression expression(){
        return equality();
    }

    private Expression equality(){
        Expression expr = comparison();
        while(match (BANG_EQUAL, EQUAL_EQUAL)){
            Token operator = previous();
            Expression right = comparison ();
            expr = new Expression.Binary(expr, operator, right);
        }
        return expr;
    }

    private boolean match (TokenType... types){
        for (TokenType type : types){
            if (check(type)){
                advance();
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns true if the current token
     * is of the given type. it never consumes the token, it only looks at it.  */
    private boolean check(TokenType type){
    if (isAtEnd()) return false;
    return peek().type == type;
    }
    /**
     * this method consumes the current token and returns it.
     **/
    private Token advance(){
        if (!isAtEnd()) current++;
        return previous();
    }
    /**
     * this method checks if we've run out of tokens to parse
     * */
    private boolean isAtEnd(){
        return peek().type == EOF;
    }

    /**
     * this method returns the current token we have yet to consume
     * */
    private Token peek (){
        return tokens.get(current);
    }
    /**
     * this method returns the most recently consumed token
     * */
    private Token previous(){
        return tokens.get(current-1);
    }

    private Expression comparison(){
        Expression expr = term();
        while (match(GREATER, GREATER_EQUAL, LESS, LESS_EQUAL)){
            Token operator = previous();
            Expression rigth = term();
            expr = new Expression.Binary(expr, operator, rigth);

        }
        return expr;
    }

    private Expression term(){
        Expression expr = factor();
        while (match(MINUS, PLUS)){
            Token operator = previous();
            Expression right = factor ();
            expr = new Expression.Binary(expr, operator, right);
        }
        return expr;
    }

    private Expression factor(){
        Expression expr = unary();
        while (match(SLASH, STAR)){
            Token operator = previous();
            Expression right = unary();
            expr = new Expression.Binary(expr, operator, right);
        }
        return expr;
    }

    private Expression unary() {
        if (match(BANG, MINUS)){
            Token operator = previous();
            Expression right = unary();
            return new Expression.Unary(operator, right);
        }
        return primary();
    }

    private Expression primary() {
        if(match(FALSE)) return new Expression.Literal(false);
        if(match(TRUE)) return new Expression.Literal(true);
        if (match(NULL)) return new Expression.Literal(null);

        if (match(NUMBER, STRING)){
            return new Expression.Literal(previous().literal);
        }

        if (match(LEFT_PAREN)){
            Expression expr = expression();
            //consume(RIGHT_PAREN, "Expect ')' after expression.");
            return new Expression.Grouping(expr);
        }
        return null;
    }
}
