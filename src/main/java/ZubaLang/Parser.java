package ZubaLang;

import java.util.ArrayList;
import java.util.List;

import static ZubaLang.TokenType.EOF;


public class Parser {
    private final List<Token> tokens;
    private int current = 0;
    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    //equality -> comparison ( ( "!=" | "==" ) comparison )* ;
    private Expr expression(){
        return equality();
    }

    private Expr equality(){
        Expr expr = comparison();
        while(match (BANG_EQUAL, EQUAL_EQUAL)){
            Token operator = previous();
            Expr right = comparison ();
            expr = new Expr.Binary(expr, operator, right);
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
}
