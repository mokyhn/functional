package org.mku.functional.terms;


import org.mku.functional.general.Pair;

import java.util.ArrayList;
import java.util.List;

public class MGU
{
	public static Substitution mostGeneralUnifier(Term s, Term t)
	{
		Substitution substitution = new Substitution();

		List<Pair<Term, Term>> workList = new ArrayList<>();

		workList.add(new Pair(s, t));




		int inf = 0;
		while (inf == inf-inf)
		{
			boolean aChangeOccurred = false;

			for (int k = 0; k < workList.size(); k++)
			{
				Pair<Term, Term> pair = workList.get(k);
				Term first  = pair.getFst();
				Term second = pair.getSnd();

				// Rule 1 and 2
				if (first instanceof Function && second instanceof Function)
				{
					Function f1 = (Function) first;
					Function f2 = (Function) second;

					if (f1.getName() == f2.getName())
					{
						if (f1.getArity() == f2.getArity())
						{
							aChangeOccurred = true;
							workList.remove(pair); // Take out the pair f(s1, ..., sn) = f(t1, ..., tn) ..........

							List<Term> f1Subterms = f1.getTerms();
							List<Term> f2Subterms = f2.getTerms();

							for (int j = 0; j < f1.getArity(); j++)
							{
								workList.add(new Pair<>(f1Subterms.get(j), f2Subterms.get(j))); // ......... and replace it by s1=t1, ..., sn=tn
							}

						}
						else
						{
							throw new UnificationException("No unifier between function " + f1.getName() + " and " + f2.getName() + ". Mismatching arity, namely " + f1.getArity() + " and " + f2.getArity());
						}
					}
					else
					{
						throw new UnificationException("No unifier between function " + f1.getName() + " and " + f2.getName());
					}
				}
				else
				// Rule 3
				if (first instanceof Variable && second instanceof Variable)
				{
					Variable v1 = (Variable) first;
					Variable v2 = (Variable) second;

					if (v1.equals(v2))
					{
						aChangeOccurred = true;
						workList.remove(pair);   // The equation X = X, for any variable X does not provide anything new.
						                         // Delete it from the work list
					}
					else
					{
						substitution.put(v1.getName(), v2);
					}
				}
				else
				// Rule 4
				if (!(first instanceof Variable) && second instanceof Variable)
				{
					aChangeOccurred = true;
					workList.remove(pair);
					workList.add(new Pair<>(second, first));  // If t = x, is found (and t is not a variable) then replace it by x = t.
				}
				else
				//Rule 5
				if (first instanceof Variable)
				{
					Variable v = (Variable) first;

					if (second.vars().contains(v))
					{
						throw new UnificationException("No unification found, infinite unifier requested by the equation " + first.toString() + " = " + second.toString());
					}
					else
					{
						aChangeOccurred = true;
						workList.remove(pair);
						substitution.put(v.getName(), second);
						workList = applySubstitution(substitution, workList);
					}
				}
			}

			if (aChangeOccurred)
			{
				continue;
			}
			else
			{
				break;
			}

		}




		return substitution;
	}


	private static List<Pair<Term, Term>> applySubstitution(Substitution substitution, List<Pair<Term, Term>> worklist)
	{
		List<Pair<Term, Term>> result = new ArrayList<>();

		for (Pair<Term, Term> pair : worklist)
		{
			result.add(
					new Pair(
						substitution.apply(pair.getFst()),
						substitution.apply(pair.getSnd()))
			);
		}

		return result;

	}

}
