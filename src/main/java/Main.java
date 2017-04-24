import org.antlr.v4.runtime.*;
import org.mku.functional.terms.Constant;
import org.mku.functional.terms.Function;
import org.mku.functional.terms.Term;

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

		parser.addParseListener(new MyVisitor());

		FunctionalParser.ProgContext progrContext = parser.prog();

		visitProgr(progrContext);



	}

	private static void visitProgr(FunctionalParser.ProgContext progrContext)
	{
		if (progrContext.definition() != null)
		{

		}
		if (progrContext.term() != null)
		{
			Term term = visitTerm(progrContext.term());
			System.out.println("Parsed result: " + term.toString());
		}
	}

	private static Term visitTerm(FunctionalParser.TermContext termContext)
	{
		if (termContext.CONSTANT() != null)
		{
			return new Constant(termContext.CONSTANT().getText());
		}
		if (termContext.VARIABLE() != null)
		{
			return new Constant(termContext.VARIABLE().getText());
		}
		if (termContext.function() != null)
		{
			FunctionalParser.TermsContext termsContext = termContext.function().terms();
			return new Function(termContext.function().CONSTANT().getText(), visitTerms(termsContext));
		}
		return null;
	}

	private static List<Term> visitTerms(FunctionalParser.TermsContext termsContext)
	{
		List<Term> result = new ArrayList<>();

		List<FunctionalParser.TermContext> termContextList = termsContext.term();

		for (FunctionalParser.TermContext c : termContextList)
		{
			result.add(visitTerm(c));
		}

		return result;
	}

	//Something along these lines...
	/*

   * Visits the branches in the expression tree recursively until we hit a leaf.
   */
	/*
	private int visit(final ExprContext context) {
		if (context.number() != null) { //Just a number
			return Integer.parseInt(context.number().getText());
		}
		else if (context.BR_CLOSE() != null) { //Expression between brackets
			return visit(context.expr(0));
		}
		else if (context.TIMES() != null) { //Expression * expression
			return visit(context.expr(0)) * visit(context.expr(1));
		}
		else if (context.DIV() != null) { //Expression / expression
			return visit(context.expr(0)) / visit(context.expr(1));
		}
		else if (context.PLUS() != null) { //Expression + expression
			return visit(context.expr(0)) + visit(context.expr(1));
		}
		else if (context.MINUS() != null) { //Expression - expression
			return visit(context.expr(0)) - visit(context.expr(1));
		}
		else {
			throw new IllegalStateException();
		}
	}
	*/

}
