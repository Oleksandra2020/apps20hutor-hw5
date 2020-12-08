package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iterators.FilterIterator;
import ua.edu.ucu.iterators.FlatMapIterator;
import ua.edu.ucu.iterators.MapIterator;
import ua.edu.ucu.iterators.StreamIterator;

import java.util.ArrayList;
import java.util.Iterator;

public class AsIntStream implements IntStream {

    private Iterator<Integer> stream;

    private AsIntStream(int... values)
    {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int value: values)
        {
            arrayList.add(value);
        }
        stream = new StreamIterator(arrayList);
    }

    private AsIntStream(Iterator<Integer> stream)
    {
        this.stream = stream;
    }

    private AsIntStream() { }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    public void checkIfEmpty() throws IllegalArgumentException
    {
        if (! stream.hasNext())
        {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Double average() {
        checkIfEmpty();
        int size = 0;
        int sum = 0;
        while (stream.hasNext())
        {
            size += 1;
            sum += stream.next();
        }
        return (double) (sum / size);
    }

    @Override
    public Integer max() {
        checkIfEmpty();
        int maximum = (int) Double.NEGATIVE_INFINITY;
        for (Iterator<Integer> it = stream; it.hasNext();)
        {
            int element = it.next();
            if (element > maximum)
            {
                maximum = element;
            }
        }
        return maximum;
    }

    @Override
    public Integer min() {
        checkIfEmpty();
        int minimum = (int) Double.POSITIVE_INFINITY;
        for (Iterator<Integer> it = stream; it.hasNext();)
        {
            int element = it.next();
            if (element < minimum)
            {
                minimum = element;
            }
        }
        return minimum;
    }

    @Override
    public long count() {
        checkIfEmpty();
        int size = 0;
        while (stream.hasNext())
        {
            size += 1;
            stream.next();
        }
        return size;
    }

    @Override
    public Integer sum() {
        checkIfEmpty();
        int sum = 0;
        for (Iterator<Integer> it = stream; it.hasNext(); ) {
            int element = it.next();
            sum += element;
        }
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterIterator(stream, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        while (stream.hasNext())
        {
            int element = stream.next();
            action.accept(element);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapIterator(stream, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatMapIterator(stream, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        while (stream.hasNext())
        {
            int element = stream.next();
            identity = op.apply(identity, element);
        }
        return identity;
    }

    @Override
    public int[] toArray() {
        checkIfEmpty();
        ArrayList<Integer> arrayList = new ArrayList<>();
        while(stream.hasNext())
        {
            arrayList.add(stream.next());
        }
        int[] array = new int[arrayList.size()];
        int i = 0;
        while (i < arrayList.size())
        {
            array[i] = arrayList.get(i);
            i += 1;
        }
        return array;
    }

}
