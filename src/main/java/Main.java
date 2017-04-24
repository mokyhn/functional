import org.antlr.v4.runtime.*;
import org.mku.functional.terms.Constant;
import org.mku.functional.terms.Function;
import org.mku.functional.terms.Term;
import org.mku.functional.terms.org.mku.functional.FunctionalProgram;

import java.util.ArrayList;
import java.util.List;



public class Main
{

	public static void main(String[] args)
	{
		ANTLRInputStream in = new ANTLRInputStream("f(X,Y)->X\nf(a , b)");
		FunctionalLexer lexer = new FunctionalLexer(in);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		FunctionalParser parser = new FunctionalParser(tokens);

		FunctionalParser.ProgContext progrContext = parser.prog();

		ASTGenerator astGenerator = new ASTGenerator();

		FunctionalProgram functionalProgram = astGenerator.visitProgr(progrContext);

		System.out.println(functionalProgram.toString());
	}

}
