package com.efimchick.ifmo.collections;

import java.util.Comparator;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

class SortedByAbsoluteValueIntegerSet implements Set<Integer> {
    private final Set<Integer> sortedSet = new TreeSet<>(new IntegerComparator());

    @Override
    public int size() {
        return sortedSet.size();
    }

    @Override
    public boolean isEmpty() {
        return sortedSet.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return sortedSet.contains(o);
    }

    @Override
    public Iterator iterator() {
        return sortedSet.iterator();
    }

    @Override
    public Object[] toArray() {
        return sortedSet.toArray();
    }

    @Override
    public boolean add(Integer o) {
        return sortedSet.add(o);
    }

    @Override
    public boolean remove(Object o) {
        return sortedSet.remove(o);
    }

    @Override
    public boolean addAll(Collection c) {
        return sortedSet.addAll(c);
    }

    @Override
    public void clear() {
        sortedSet.clear();
    }

    @Override
    public boolean removeAll(Collection c) {
        return sortedSet.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection c) {
        return sortedSet.retainAll(c);
    }

    @Override
    public boolean containsAll(Collection c) {
        return sortedSet.containsAll(c);
    }

    @Override
    public Object[] toArray(Object[] a) {
        return sortedSet.toArray(a);
    }

    class IntegerComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return Math.abs(o1) - Math.abs(o2);
        }

    }
}