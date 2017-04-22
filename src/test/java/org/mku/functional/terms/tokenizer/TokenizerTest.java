package org.mku.functional.terms.tokenizer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TokenizerTest {
    
    Tokenizer tokenizer;
    
    @Before
    public void setUp() 
    {
        tokenizer = new Tokenizer();
        
    }
    
    private String getFile(String fileName) 
    {
        try 
        {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            return new String(Files.readAllBytes(file.toPath()));
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    @Test
    public void testTokenizeConstant() {
        TokenResult result = tokenizer.tokenizeConstant("c3287d,Xanother");
        assertNotNull(result.getResult());
        assertEquals(",Xanother", result.getRemaining());
    }
    
    @Test
    public void testTokenizeVariable() {
        TokenResult result = tokenizer.tokenizeVariable("Xc3287d,Xanother");
        assertNotNull(result.getResult());
        assertEquals(",Xanother", result.getRemaining());
    }
    
    @Test
    public void testTokenize() {
        Collection<Token> result = tokenizer.tokenize("(X42,c367)");
    }
    
    @Test
    public void testExample1()
    {
        String s = getFile("example1.func");
        Collection<Token> result = tokenizer.tokenize(s);
    }
}
