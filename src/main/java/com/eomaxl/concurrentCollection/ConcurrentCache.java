package com.eomaxl.concurrentCollection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentCache {
    private static final Map<String, String> cache = new ConcurrentHashMap<String, String>();

    public static void main (String[] args) {
        for(int i  =0; i< 10; i++){
            final int threadNum = i;

            new Thread(()->{
                String key = "Key @ "+threadNum;
                for(int j=0; j< 3; j++){
                    String value = getCachedValue(key);
                    System.out.println("Thread "+Thread.currentThread().getName() + " : Key = "+ key + " value = " + value);
                }
            }).start();
        }
    }

    private static String getCachedValue(String key) {
        String cachedValue = cache.get(key);

        if(cachedValue == null){
            cachedValue = compute(key);
            cache.put(key, cachedValue);
        }

        return cachedValue;
    }

    private static String compute(final String key) {
        System.out.println("Key not present in cache");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        return "Value for "+key;
    }
}


/**
 * Implementation:
 * 1. ConcurrentHashMap
 * 2. ConcurrentSkipListMap
 * 3. ConcurrentLinkedHashMap
 * 4. ConcurrentNavigableMap
 *
 *
 * Internal implementation of concurrent hashmap
 * Adding an element to concurrent hashmap
 * 1. Hashing and determining segment
 * 2. Acquiring lock
 * 3. Insertion in Segment
 * 4. Releasing Lock
 *
 * Fetching an element from concurrent hash map
 * 1. Hashing and determining segment
 * 2. Acquiring lock
 * 3. Searching in Segment
 * 4. Releasing lock
 *
 *
 * */