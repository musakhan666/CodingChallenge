package com.android.designpattern.util;

import java.util.HashMap;

public class TempStorage {

    static public TempStorage storage = new TempStorage();


    private HashMap<String, Object> map = new HashMap<>();


    public boolean setObject(String key, Object object) {

        if (map.containsKey(key)) {
            return false;
        }

        map.put(key, object);

        return true;
    }

    public Object getObject(String key) {

        if (map.containsKey(key)) {
            return map.get(key);
        }

        return null;
    }

    public boolean deleteObject(String key) {

        if (map.containsKey(key)) {
            map.remove(key);
            return true;
        }

        return false;
    }

    public boolean hasObject(String key) {

        if (map.containsKey(key)) {
            return true;
        }

        return false;

    }
}
