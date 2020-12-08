package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.stream.AsIntStream;

import java.util.ArrayList;
import java.util.Iterator;

public class FlatMapIterator implements Iterator<Integer> {

    private Iterator<Integer> stream;
    private IntToIntStreamFunction func;
    private StreamIterator subIterator = null;

    public FlatMapIterator(Iterator<Integer> stream,
                           IntToIntStreamFunction func)
    {
        this.stream = stream;
        this.func = func;
    }

    @Override
    public boolean hasNext()
    {
        if (subIterator != null && subIterator.hasNext())
        {
            return true;
        }
        if (!stream.hasNext())
        {
            return false;
        }
        AsIntStream newStream = (AsIntStream)
                func.applyAsIntStream(stream.next());
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int value: newStream.toArray())
        {
            arrayList.add(value);
        }
        subIterator = new StreamIterator(arrayList);
        return subIterator.hasNext();
    }

    @Override
    public Integer next()
    {
        return subIterator.next();
    }

}
