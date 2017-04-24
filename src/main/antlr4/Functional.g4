grammar Functional;

prog:	(definition NEWLINE)* term ;

definition : term OP_DEFINES term ;

term : CONSTANT
     | VARIABLE
     | function
     ;

function : CONSTANT '(' terms ')' ;

terms :
      term (',' term)* ;

OP_DEFINES : '->' ;

NEWLINE : [\r\n]+ ;
CONSTANT : [a-z]+.*? ;
VARIABLE : [A-Z]+.*? ;
SPACE   : (' ' | '\t' | '\r' | '\n') {skip();};