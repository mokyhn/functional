import org.mku.functional.terms.Constant;
import org.mku.functional.terms.Function;
import org.mku.functional.terms.Term;
import org.mku.functional.terms.TermDefinition;
import org.mku.functional.terms.org.mku.functional.FunctionalProgram;

import java.util.ArrayList;
import java.util.List;

public class ASTGenerator
{
	public FunctionalProgram visitProgr(FunctionalParser.ProgContext progrContext)
	{
		List<TermDefinition> termDefinitions = new ArrayList<>();

		if (progrContext.definition() != null)
		{
			List<FunctionalParser.DefinitionContext> definitionContextList = progrContext.definition();
			for (FunctionalParser.DefinitionContext c : definitionContextList)
			{
				Term from = visitTerm(c.term().get(0));
				Term to = visitTerm(c.term().get(1));
				TermDefinition termDefinition = new TermDefinition(from, to);
				termDefinitions.add(termDefinition);
			}
		}

		Term term = null;
		if (progrContext.term() != null)
		{
			term = visitTerm(progrContext.term());
		}

		FunctionalProgram functionalProgram = new FunctionalProgram(termDefinitions, term);

		return functionalProgram;

	}

	private Term visitTerm(FunctionalParser.TermContext termContext)
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

	private List<Term> visitTerms(FunctionalParser.TermsContext termsContext)
	{
		List<Term> result = new ArrayList<>();

		List<FunctionalParser.TermContext> termContextList = termsContext.term();

		for (FunctionalParser.TermContext c : termContextList)
		{
			result.add(visitTerm(c));
		}

		return result;
	}

}
