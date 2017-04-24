package org.mku.functional.terms.org.mku.functional;


import org.mku.functional.terms.Term;
import org.mku.functional.terms.TermDefinition;

import java.util.List;

public class FunctionalProgram
{
	List<TermDefinition> termDefinitions;
	Term main;

	public FunctionalProgram(List<TermDefinition> termDefinitions, Term main)
	{
		this.termDefinitions = termDefinitions;
		this.main = main;
	}


	@Override
	public String toString()
	{
		String result = "";

		for (TermDefinition td : termDefinitions)
		{
			result += td.toString() + "\n";
		}

		result += "\n" + main.toString();

		return result;

	}

}
