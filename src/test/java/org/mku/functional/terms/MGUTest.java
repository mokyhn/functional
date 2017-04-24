package org.mku.functional.terms;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;


public class MGUTest
{
	@Test
	public void mgu1()
	{
		Term s = new Variable("x");
		Term t = new Constant("c");

		Substitution substitution = MGU.mostGeneralUnifier(s, t);
		assertEquals("x>c", substitution.toString());
	}

	@Test
	public void mgu2()
	{
		Term s = new Constant("c");
		Term t = new Variable("x");

		Substitution substitution = MGU.mostGeneralUnifier(s, t);
		assertEquals("x>c", substitution.toString());
	}

	@Test(expected=UnificationException.class)
	public void mgu3()
	{
		Term s = new Function("f");
		Term t = new Function("g");
		MGU.mostGeneralUnifier(s, t);

	}

	@Test
	public void mgu4()
	{
		Term s = new Function("f", new Constant("c"));
		Term t = new Function("f", new Variable("x"));
		Substitution substitution = MGU.mostGeneralUnifier(s, t);
		assertEquals("x>c", substitution.toString());
	}


	@Test
	public void mgu5()
	{
		Term x1 = new Variable("x1");
		Term x2 = new Variable("x2");
		Term x3 = new Variable("x3");
		Term x4 = new Variable("x4");
		Term x5 = new Variable("x5");

		Term s = new Function("f", x1, x2, x3, x4);
		Term t = new Function( "f", new Function("g", x2, x2), new Function("g", x3, x3), new Function("g", x4, x4),  new Function("g", x5, x5));


		Substitution substitution = MGU.mostGeneralUnifier(s, t);
		assertEquals("x1>g(g(x3,x3),g(x3,x3)),x2>g(x3,x3),x3>g(g(x5,x5),g(x5,x5)),x4>g(x5,x5)", substitution.toString()); // Verify this solution
	}

	@Test
	public void mgu6()
	{

		Term s = new Function("f", new Variable("x"));
		Term t = new Function("f", new Variable("y"));


		Substitution substitution = MGU.mostGeneralUnifier(s, t);
		assertEquals("x>y", substitution.toString()); // Verify this solution
	}

	@Test
	public void mgu7()
	{

		Term s = new Function("f", new Variable("X"), new Function("g", new Constant("42")));
		Term t = new Function("f", new Variable("Z"), new Variable("W"));


		Substitution substitution = MGU.mostGeneralUnifier(s, t);
		assertEquals("W>g(42),X>Z", substitution.toString()); // Verify this solution
	}

}