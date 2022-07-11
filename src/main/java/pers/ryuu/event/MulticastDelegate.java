package pers.ryuu.event;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashSet;
import java.util.List;

public abstract class MulticastDelegate<E extends EventListener> {

    protected final List<E> delegates = new ArrayList<>();

    protected int index;

    public final boolean add(E listener) {
        int index = delegates.indexOf(listener);
        if (index <= this.index) {
            this.index++;
        }
        return delegates.add(listener);
    }

    public final boolean remove(E listener) {
        int index = delegates.indexOf(listener);
        if (index == -1) {
            return false;
        } else {
            if (index < this.index) {
                this.index--;
            }
            return delegates.remove(listener);
        }
    }

    public final boolean contains(E listener) {
        return delegates.contains(listener);
    }

    public final boolean contains(MulticastDelegate<E> multicastDelegate) {
        if (multicastDelegate == null) return false;
        return new HashSet<>(this.delegates).containsAll(multicastDelegate.delegates);
    }

    public final void clear() {
        delegates.clear();
    }

    public final int count() {
        return delegates.size();
    }
}
