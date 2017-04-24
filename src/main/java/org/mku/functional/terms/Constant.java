package org.mku.functional.terms;

import java.util.HashSet;
import java.util.Set;

public class Constant implements Term
{
    String data;

    public Constant(String data)
    {
        this.data = data;
    }

    public String getData()
    {
        return data;
    }

    @Override
    public String toString()
    {
        return data;
    }

    @Override
    public Set<Term> vars()
    {
        return new HashSet<>();
    }

}