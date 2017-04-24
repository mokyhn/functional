package org.mku.functional.terms;

import java.util.HashSet;
import java.util.Set;

public class Variable implements Term
{
    String name;
    
    public Variable(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Variable)
        {
            Variable v = (Variable) o;
            return this.getName().equals(v.getName());
        }
        else
        {
            return false;
        }
    }

    @Override
    public Set<Term> vars()
    {
        Set<Term> result = new HashSet<>();
        result.add(this);
        return result;
    }

    @Override
    public int hashCode()
    {
        return name.hashCode();
    }
}
