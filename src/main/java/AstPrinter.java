import ZubaLang.Expression;
import ZubaLang.Expression.*;


public class AstPrinter implements Expression.Visitor<String>{
    String print(Expression expr){
        return Expression.accept(this);
    }

    @Override
    public String visitBinaryExpression(Expression.Binary expression){
        return parenthesize(expression.operator.lexeme, expression.left, expression.right);
    }

    @Override
    public String visitGroupingExpr(Expression.Grouping expression){
        return parenthesize("group", expression.expression);
    }

    @Override
    public String visitLiteralExpr(Expression.Literal expression){

    }

}
