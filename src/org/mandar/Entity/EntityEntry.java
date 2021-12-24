package org.mandar.Entity;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class EntityEntry implements Map.Entry<Integer, ArrayList<Object>> {

   /* Integer key;
    ArrayList<Object> value;*/

    Map.Entry<Integer, ArrayList<Object>> entry;

    public EntityEntry(Map.Entry<Integer, ArrayList<Object>> entry)
    {
        this.entry = entry;
    }

    @Override
    public Integer getKey() {
        return entry.getKey();
    }

    @Override
    public ArrayList<Object> getValue() {
        return entry.getValue();
    }

    @Override
    public ArrayList<Object> setValue(ArrayList<Object> value) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityEntry that = (EntityEntry) o;
        return getKey().equals(that.getKey()) && getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getValue());
    }
}
