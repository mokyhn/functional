package org.mku.functional.terms.tokenizer;

public class Token 
{
    public enum TokenType
    {
        CONSTANT,
        VARIABLE,
        LPAREN,
        RPAREN, 
        COMMA,
        EQUALS, 
        WHITESPACE,
        NEWLINE   
    }
    
    TokenType tokenType;
    String content;
    
    public Token(TokenType tokenType, String content)
    {
        this.tokenType = tokenType;
        this.content = content;
    }
}