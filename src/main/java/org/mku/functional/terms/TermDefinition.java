package org.mku.functional.terms;


public class TermDefinition
{
	private final Term from;
	private final Term to;

	public TermDefinition(Term from, Term to)
	{
		this.from = from;
		this.to = to;
	}

	public String toString()
	{
		return from.toString() + "->" + to.toString();
	}
}
