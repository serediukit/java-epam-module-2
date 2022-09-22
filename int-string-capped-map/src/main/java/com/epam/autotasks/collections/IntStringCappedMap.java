package com.epam.autotasks.collections;

import java.util.*;

class IntStringCappedMap extends AbstractMap<Integer, String> {

    private final long capacity;
    private Map<Integer, String> map;

    public IntStringCappedMap(final long capacity) {
        this.capacity = capacity;
        map = new LinkedHashMap<>((int) capacity);
    }

    public long getCapacity() {
        return capacity;
    }

    @Override
    public Set<Entry<Integer, String>> entrySet() {
        return new AbstractSet<>() {
            @Override
            public Iterator<Entry<Integer, String>> iterator() {
                return new Iterator<>() {
                    Iterator<Entry<Integer, String>> value = map.entrySet().iterator();

                    @Override
                    public boolean hasNext() {
                        return value.hasNext();
                    }

                    @Override
                    public Entry<Integer, String> next() {
                        if (value.hasNext())
                            return value.next();
                        else
                            throw new NoSuchElementException();
                    }
                };
            }

            @Override
            public int size() {
                return IntStringCappedMap.this.size();
            }
        };
    }

    @Override
    public String get(final Object key) {
        return map.get(key);
    }

    @Override
    public String put(final Integer key, final String value) {
        if (value.length() > capacity)
            throw new IllegalArgumentException();
        String prevValue = null;
        if (map.containsKey(key)) {
            prevValue = map.remove(key);
        }
        while (totalSize() + value.length() > capacity) {
            map.remove(getFirst(map).getKey());
        }

        String temp = map.put(key, value);
        if (prevValue != null)
            return prevValue;
        return temp;
    }

    @Override
    public String remove(final Object key) {
        return map.remove(key);
    }

    @Override
    public int size() {
        return map.size();
    }

    private static <K, V> Entry<K, V> getFirst(Map<K, V> m) {
        if(m.isEmpty())
            return null;
        return m.entrySet().iterator().next();
    }

    private long totalSize() {
        long ts = 0;
        for (Map.Entry<Integer, String> n : map.entrySet()) {
            ts += n.getValue().length();
        }
        return ts;
    }

}
