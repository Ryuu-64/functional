package pers.ryuu.functional_interface;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashSet;
import java.util.List;

public abstract class MulticastFunctionalInterface<E extends EventListener> {

    protected final List<E> interfaces = new ArrayList<>();

    protected int index;

    public final boolean add(E listener) {
        if (listener == null) {
            return false;
        }

        return interfaces.add(listener);
    }

    public final boolean remove(E listener) {
        int index = interfaces.indexOf(listener);
        if (index == -1) {
            return false;
        } else {
            if (index < this.index) {
                this.index--;
            }
            return interfaces.remove(listener);
        }
    }

    public final boolean contains(E listener) {
        return interfaces.contains(listener);
    }

    public final boolean contains(MulticastFunctionalInterface<E> multicastFunctionalInterface) {
        if (multicastFunctionalInterface == null) {
            return false;
        }
        return new HashSet<>(this.interfaces).containsAll(multicastFunctionalInterface.interfaces);
    }

    public final void clear() {
        interfaces.clear();
    }

    public final int count() {
        return interfaces.size();
    }
}
