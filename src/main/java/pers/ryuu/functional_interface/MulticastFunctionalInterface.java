package pers.ryuu.functional_interface;

import java.util.ArrayList;
import java.util.List;

public abstract class MulticastFunctionalInterface<T extends IFunctionalInterface> {

    protected final List<T> interfaces = new ArrayList<>();

    protected int index;

    @SuppressWarnings("unchecked")
    public final boolean add(T listener) {
        if (listener == null) {
            return false;
        } else if (listener instanceof MulticastFunctionalInterface) {
            return addMulticast((MulticastFunctionalInterface<T>) listener);
        } else /* if (listener instanceof IFunctionalInterface) */ {
            return addUnicast(listener);
        }
    }

    public final boolean remove(T listener) {
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

    public final boolean contains(T listener) {
        return interfaces.contains(listener);
    }

    public final void clear() {
        interfaces.clear();
    }

    public final int count() {
        return interfaces.size();
    }

    private boolean addUnicast(T listener) {
        return interfaces.add(listener);
    }

    private boolean addMulticast(MulticastFunctionalInterface<T> listener) {
        boolean res = false;
        for (T t : listener.interfaces) {
            add(t);
        }
        return res;
    }
}
