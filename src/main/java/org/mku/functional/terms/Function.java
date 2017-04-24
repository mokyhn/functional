package org.mku.functional.terms;

import java.util.*;

public class Function implements Term
{
    private final String name;
    private final List<Term> subTerms;
    
    public Function(String name, Term... terms)
    {
        this.name = name;
        subTerms = new ArrayList<>(Arrays.asList(terms));
    }

    public Function(String data, List<Term> terms)
    {
        this.name = data;
        subTerms = terms;
    }

    public String getName()
    {
        return name;
    }
    
    public List<Term> getTerms() 
    {
        return subTerms;
    }

    @Override
    public String toString()
    {
        String args = "";
        for (Term t : subTerms)
        {
            if (args.isEmpty())
            {
                args = t.toString();
            }
            else
            {
                args += "," + t.toString();
            }
        }

        return name + "(" + args +  ")";
    }


    public int getArity()
    {
        return subTerms.size();
    }


    @Override
    public Set<Term> vars()
    {
        Set<Term> result = new HashSet<>();

        for (Term t : subTerms)
        {
            result.addAll(t.vars());
        }
        return result;
    }
}
