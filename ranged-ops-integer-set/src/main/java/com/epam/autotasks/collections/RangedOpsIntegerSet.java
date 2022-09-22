package com.epam.autotasks.collections;

import java.util.*;

class RangedOpsIntegerSet extends AbstractSet<Integer> {

    private ArrayList<Integer> lst = new ArrayList<>();

    public boolean add(int fromInclusive, int toExclusive) {
        boolean flag = false;
        for(int i = fromInclusive; i < toExclusive; i++) {
            if(add(i) && !flag)
                flag = true;
        }
        return flag;
    }

    public boolean remove(int fromInclusive, int toExclusive) {
        Integer count = 0;
        for (int i = fromInclusive; i < toExclusive; i++) {
            if(lst.contains(i)) {
                lst.remove((Integer) i);
                count++;
            }
        }
        return count > 0;
    }

    @Override
    public boolean add(final Integer integer) {
        if (!lst.contains(integer)) {
            lst.add(integer);
            Collections.sort(lst);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(final Object o) {
        if (lst.contains((Integer) o)) {
            lst.remove(o);
            Collections.sort(lst);
            return true;
        }
        return false;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new ListIterator();
    }

    @Override
    public int size() {
        return lst.size();
    }

    private boolean checkElement(Integer num) {
        for (Integer n : lst) {
            if(n.equals(num))
                return true;
        }
        return false;
    }

    class ListIterator implements Iterator<Integer> {
        int it = 0;

        @Override
        public boolean hasNext() {
            return lst.size() > it;
        }

        @Override
        public Integer next() {
            Integer[] a = lst.toArray(new Integer[0]);
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return a[it++];
        }
    }
}
