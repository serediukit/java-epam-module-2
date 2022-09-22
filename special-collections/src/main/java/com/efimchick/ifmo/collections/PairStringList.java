package com.efimchick.ifmo.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

class PairStringList implements List<String> {
    private List<String> list = new ArrayList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<String> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(String s) {
        list.add(s);
        list.add(s);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        list.remove(o);
        list.remove(o);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        for (String string : c) {
            add(string);
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        for (String string : c) {
            add(cortIndex(index), string);
            index += 2;
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public String get(int index) {
        return list.get(index);
    }

    @Override
    public String set(int index, String element) {
        list.set(index, element);
        return list.set(getSecIndex(index), element);
    }

    @Override
    public void add(int index, String element) {
        list.add(cortIndex(index), element);
        list.add(cortIndex(index), element);
    }

    @Override
    public String remove(int index) {
        list.remove(index);
        return list.remove(getSecIndex(index));
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<String> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private int cortIndex(int index) {
        if (!even(index)) {
            return ++index;
        } else {
            return index;
        }
    }

    private boolean even(int number) {
        return number % 2 == 0;
    }

    private int getSecIndex(int index) {
        if (even(index)) {
            return ++index;
        } else {
            return --index;
        }
    }

}