package org.mku.functional.terms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Function implements Term
{
    String name;
    
    List<Term> subTerms;
    
    public Function(String data, Term... terms)
    {
        this.name = data;
        subTerms = new ArrayList<>(Arrays.asList(terms));
    }

    public Function(String data, List<Term> terms)
    {
        this.name = data;
        subTerms = terms;
    }

    public String getData()
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
}
