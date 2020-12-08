package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntUnaryOperator;

import java.util.Iterator;

public class MapIterator implements Iterator<Integer> {

    private Iterator<Integer> stream;
    private IntUnaryOperator mapper;

    public MapIterator(Iterator<Integer> stream, IntUnaryOperator mapper)
    {
        this.stream = stream;
        this.mapper = mapper;
    }

    @Override
    public boolean hasNext()
    {
        return stream.hasNext();
    }

    @Override
    public Integer next()
    {
        return mapper.apply(stream.next());
    }
}
