import org.antlr.v4.runtime.*;

/**
 * Created by mku on 4/18/17.
 */
public class Main
{

	public static void main(String[] args)
	{
		ANTLRInputStream in = new ANTLRInputStream("12*(5-6)\n");
		ExprLexer lexer = new ExprLexer(in);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ExprParser parser = new ExprParser(tokens);

		parser.addParseListener(new MyVisitor());

		ExprParser.ProgContext progrContext = parser.prog();

		visitProgr(progrContext);



	}

	private static void visitProgr(ExprParser.ProgContext progrContext)
	{
		if (progrContext.expr() != null) {
		}
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
