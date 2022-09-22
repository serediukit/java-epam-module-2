package com.efimchick.ifmo.collections;

import java.util.*;

class MedianQueue implements Queue<Integer>{
    private final LinkedList<Integer> queue = new LinkedList<>();

    @Override
    public boolean add(final Integer integer) {
        return queue.add(integer);
    }

    @Override
    public boolean offer(final Integer integer) {
        queue.offer(integer);
        range();
        return true;
    }

    @Override
    public Integer remove() {
        Integer removed = queue.remove();
        range();
        return removed;
    }

    @Override
    public Integer poll() {
        Integer removed = queue.poll();
        range();
        return removed;
    }

    @Override
    public Integer element() {
        return queue.element();
    }

    @Override
    public Integer peek() {
        return queue.peek();
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean remove(final Object o) {
        if (queue.remove(o)) {
            range();
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean contains(final Object o) {
        return queue.contains(o);
    }

    @Override
    public Iterator<Integer> iterator() {
        return queue.iterator();
    }

    @Override
    public Object[] toArray() {
        return queue.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        return queue.toArray(a);
    }


    @Override
    public boolean containsAll(final Collection<?> c) {
        return queue.containsAll(c);
    }

    @Override
    public boolean addAll(final Collection<? extends Integer> c) {
        queue.addAll(c);
        range();
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        if (queue.removeAll(c)) {
            range();
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return queue.retainAll(c);
    }

    @Override
    public void clear() {
        queue.clear();
    }


    private void range() {
        LinkedList<Integer> lst = new LinkedList<>(queue);
        Collections.sort(lst);
        clear();
        int initialSize = lst.size();
        for (int i = 0; i < initialSize; i++) {
            if (even(i)) {
                queue.addFirst(lst.pollLast());
            } else {
                queue.addFirst(lst.pollFirst());
            }
        }
    }

    private boolean even(int number) {
        return number % 2 == 0;
    }
}
