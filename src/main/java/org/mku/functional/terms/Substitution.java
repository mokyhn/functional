package org.mku.functional.terms;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Substitution extends HashMap<String, Term>
{
	/**
	 *
	 * @param t the term to apply the substitution to
	 * @return the resulting term
	 */
	public Term apply(Term t)
	{
		if (t instanceof Constant)
		{
			return t;
		}

		if (t instanceof Variable)
		{
			Variable v = (Variable) t;
			if (this.keySet().contains(v.getName()))
			{
				return this.get(v.getName());
			}
			else
			{
				return v;
			}
		}

		if (t instanceof Function)
		{
			Function function = (Function) t;

			List<Term> terms = function.getTerms();

			List<Term> newTerms = new ArrayList<>();

			for (Term tmp : terms)
			{
				newTerms.add(this.apply(tmp));
			}

			return new Function(function.getName(), newTerms);

		}
		throw new IllegalStateException("Term not matched.");
	}

	public String toString()
	{
		String result = "";

		for (String x : this.keySet())
		{
			if (result.isEmpty())
			{
				result = x + ">" + this.get(x).toString();
			}
			else
			{
				result += "," + x + ">" + this.get(x).toString();
			}
		}

		return result;
	}
}
