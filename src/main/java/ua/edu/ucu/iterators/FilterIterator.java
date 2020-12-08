package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;

public class FilterIterator implements Iterator<Integer> {

    private Iterator<Integer> stream;
    private IntPredicate predicate;
    private Integer nextValid;

    public FilterIterator(Iterator<Integer> stream, IntPredicate predicate)
    {
        this.stream = stream;
        this.predicate = predicate;
    }

    @Override
    public boolean hasNext()
    {
        int element;
        while (stream.hasNext())
        {
            element = stream.next();
            if (predicate.test(element))
            {
                nextValid = element;
                return true;
            }
        }
        nextValid = null;
        return false;
    }

    @Override
    public Integer next()
    {
        return nextValid;
    }
}
