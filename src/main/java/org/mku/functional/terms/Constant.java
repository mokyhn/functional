package org.mku.functional.terms;

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
}
