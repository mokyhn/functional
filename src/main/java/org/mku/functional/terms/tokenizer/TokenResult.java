package org.mku.functional.terms.tokenizer;

public class TokenResult {
    private final Token  result;
    private final String remaining;
    
    public TokenResult(Token result, String remaining)
    {
        this.result = result;
        this.remaining = remaining;
        
    }
    
    public String getRemaining()
    {
        return remaining;
    }

    public Token getResult() 
    {
        return result;
    }
}
