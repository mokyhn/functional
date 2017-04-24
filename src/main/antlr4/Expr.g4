grammar Expr;
prog:	(expr NEWLINE)* ;
expr:	expr (OP_MULT|OP_DIVIDE) expr
    |	expr (OP_PLUS|OP_MINUS) expr
    |	INT
    |	'(' expr ')'
    ;

OP_MULT : '*' ;
OP_DIVIDE : '/' ;
OP_PLUS : '+' ;
OP_MINUS : '-' ;


NEWLINE : [\r\n]+ ;
INT     : [0-9]+ ;