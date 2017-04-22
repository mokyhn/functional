package org.mku.functional.terms.tokenizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mku.functional.terms.tokenizer.Token.TokenType;

public class Tokenizer {
    private final String constantPatternStr = "[a-z]+\\w*";
    private final String variablePatternStr = "[A-Z]+\\w*";
                                              
    
    Pattern constantPattern;
    Pattern variablePattern;

    public Tokenizer()
    {
        constantPattern = Pattern.compile(constantPatternStr);
        variablePattern = Pattern.compile(variablePatternStr);
    }
        
    public TokenResult tokenizeConstant(String s)
    {
        Matcher matcher = constantPattern.matcher(s);
        String match = "";
        boolean found = matcher.find();
        
        try
        {
            match = matcher.group(0);
        } catch (Exception e) { }

        String rest;
        Token token = null;
        if (found && matcher.start() == 0) 
        {
            token = new Token(Token.TokenType.CONSTANT, match);
            rest = s.substring(matcher.end(), s.length());
        }
        else
        {
            rest = s;
        }
        return new TokenResult((token), rest);
    }

    public TokenResult tokenizeVariable(String s)
    {
        Matcher matcher = variablePattern.matcher(s);
        String match = "";
        boolean found = matcher.find();
        
        try
        {
            match = matcher.group(0);
        } catch (Exception e) { }

        String rest;
        Token token = null;
        if (found && matcher.start() == 0) 
        {
            token = new Token(Token.TokenType.VARIABLE, match);
            rest = s.substring(matcher.end(), s.length());
        }
        else
        {
            rest = s;
        }
        return new TokenResult((token), rest);
    }

    public TokenResult tokenizeSymbol(String s)
    {
        char c = s.charAt(0);
        
        TokenType tokenType = null;
        
        switch (c) 
        {
            case '(': 
                tokenType = TokenType.LPAREN;
                break;
            case ')':
                tokenType = TokenType.RPAREN;
                break;
            case ',':
                tokenType = TokenType.COMMA;
                break;
            case '=':
                tokenType = TokenType.EQUALS;
                break;
            case ' ':
                tokenType = TokenType.WHITESPACE;
                break;
            case '\n':
                tokenType = TokenType.NEWLINE;
                break;
            case '\t':
                tokenType = TokenType.WHITESPACE;
                break;
        }
        
        return new TokenResult(new Token(tokenType, ""), s.substring(1, s.length()));
    }

    
    public Collection<Token> tokenize(String s)
    {
        s = removeComments(s);
        
        String remaining = s;
        
        ArrayList<Token> result = new ArrayList<>();
        
        TokenResult tokenResult;
        
        while (!remaining.isEmpty())
        {
            tokenResult = tokenizeConstant(remaining);
            if (tokenResult.getResult() != null) 
            {
                remaining = tokenResult.getRemaining();
                result.add(tokenResult.getResult());
                continue;
            }
            
            tokenResult = tokenizeVariable(remaining);
            if (tokenResult.getResult() != null) 
            {
                remaining = tokenResult.getRemaining();
                result.add(tokenResult.getResult());
                continue;
            }

            tokenResult = tokenizeSymbol(remaining);
            if (tokenResult.getResult() != null) 
            {
                remaining = tokenResult.getRemaining();
                result.add(tokenResult.getResult());
            }
        }
        
        return result;
    }
    
    private String removeComments(String s)
    {
        String result = "";
        
        boolean removing = false;
        
        for (char c : s.toCharArray()) 
        {
            if (c == '#') 
            {
                removing = true;
            } 
            
            if (c == '\n') 
            {
                removing = false;
            }
            
            if (!removing)
            {
                result = result + c;
            }            
        }
        
        return result;
    }
}
