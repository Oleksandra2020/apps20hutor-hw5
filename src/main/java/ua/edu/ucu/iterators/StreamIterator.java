package ua.edu.ucu.iterators;

import java.util.ArrayList;
import java.util.Iterator;

public class StreamIterator implements Iterator<Integer> {

    private ArrayList<Integer> container;
    private int initialIndex;

    public StreamIterator(ArrayList<Integer> arrayList)
    {
        container = arrayList;
        initialIndex = 0;
    }

    @Override
    public boolean hasNext()
    {
        return initialIndex < container.size();
    }

    @Override
    public Integer next()
    {
        int currentElement = container.get(initialIndex);
        initialIndex += 1;
        return currentElement;
    }
}
