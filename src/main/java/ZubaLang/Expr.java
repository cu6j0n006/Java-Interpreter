package zubaLang ;

abstract class Expr {

static class Binary extends Expr {

 public Binary(Expr left, Token operator, Expr right){
    this.Expr left = left
    this. Token operator = Token
    this. Expr right = Expr
    }

final Expr left
final  Token operator
final  Expr right
}
Binary
static class Grouping extends Expr {

 public Grouping(Expr expression){
    this.Expr expression = expression
    }

final Expr expression
}
Grouping
static class Literal extends Expr {

 public Literal(Object value){
    this.Object value = value
    }

final Object value
}
Literal
static class Unary extends Expr {

 public Unary(Token operator, Expr right){
    this.Token operator = operator
    this. Expr right = Expr
    }

final Token operator
final  Expr right
}
Unary
}
