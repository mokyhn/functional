package org.mku.functional.terms;

public class Variable implements Term
{
    String name;
    
    public Variable(String data)
    {
        this.name = data;
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
}
