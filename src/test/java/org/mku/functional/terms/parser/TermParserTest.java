
package org.mku.functional.terms.parser;

import org.mku.functional.terms.parser.TermParser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mku.functional.terms.Term;
import static org.junit.Assert.*;

public class TermParserTest 
{
    
    TermParser termParser = new TermParser();

    @org.junit.Test
    public void testSomeMethod() {
        Term result = termParser.parse("x987,og");
    }
    
}
