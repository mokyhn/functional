import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

//http://niels.nu/blog/2015/antlr-is-awesome.html

public class MyVisitor extends ExprBaseListener
{
	@Override
	public void enterProg(ExprParser.ProgContext ctx)
	{
		System.out.println("Visiting PROG " + ctx.toStringTree());
	}


	@Override
	public void enterExpr(ExprParser.ExprContext ctx)
	{
		System.out.println("Visiting EXPR " + ctx.toStringTree());

	}

	@Override
	public void enterEveryRule(ParserRuleContext ctx)
	{
		System.out.println("enterEveryRule " + ctx.toStringTree());

	}

	@Override
	public void visitTerminal(TerminalNode node)
	{
		System.out.println("visitTerminal " + node.toStringTree());
	}

}
