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
    
    public String getData()
    {
        return name;
    }
    
    public List<Term> getTerms() 
    {
        return subTerms;
    }
}
