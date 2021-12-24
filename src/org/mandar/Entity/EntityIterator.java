package org.mandar.Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class EntityIterator implements Iterator<EntityEntry> {

    ArrayList<EntityEntry> entryList;
    int currentIndex = 0;

    public EntityIterator(Set<Map.Entry<Integer, ArrayList<Object>>> entrySet) {
        for(Map.Entry<Integer, ArrayList<Object>> entry : entrySet)
        {
            entryList.add(new EntityEntry(entry));
        }
    }

    @Override
    public boolean hasNext() {
        return currentIndex < entryList.size();
    }

    @Override
    public EntityEntry next() {
        return entryList.get(currentIndex++);
    }
}
