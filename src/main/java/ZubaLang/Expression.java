package ZubaLang;

/***
 * All expressions classes will inherit this class
 */
abstract class Expression {
    static class Binary extends Expression {

        final Expression left;
        final Expression right;
        final Token operator;

        Binary(Expression left,Token operator, Expression right) {
            this.left = left;
            this.operator  = operator;
            this.right = right;
        }
    }

    static class Unary extends Expression {
        final Token operator;
        final Expression right;
        Unary(Token operator, Expression right) {
            this.operator = operator;
            this.right = right;
        }
    }

    static class Literal extends Expression {
        final Object literal;
        Literal(Object literal) {
            this.literal = literal;
        }
    }

    static class Grouping extends Expression {
        final Expression expr;
        Grouping(Expression expr) {
            this.expr = expr;
        }
    }
}
