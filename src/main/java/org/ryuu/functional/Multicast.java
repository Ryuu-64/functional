package org.ryuu.functional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

abstract class Multicast<F extends Unicast> implements Unicast, Iterable<F> {
    protected final List<F> unicastList = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public final boolean add(F functional) {
        if (functional == null) {
            return false;
        } else if (functional instanceof Multicast) {
            return addMulticast((Multicast<F>) functional);
        } else /* if (functional instanceof Unicast) */ {
            return addUnicast(functional);
        }
    }

    @SuppressWarnings("unchecked")
    public final boolean remove(F functional) {
        if (functional == null) {
            return false;
        } else if (functional instanceof Multicast) {
            return removeMulticast((Multicast<F>) functional);
        } else /* if (functional instanceof Unicast) */ {
            return removeUnicast(functional);
        }
    }

    @SuppressWarnings("unchecked")
    public final boolean contains(F functional) {
        if (functional == null) {
            return false;
        } else if (functional instanceof Multicast) {
            return containsMulticast((Multicast<F>) functional);
        } else /* if (functional instanceof Unicast) */ {
            return containsUnicast(functional);
        }
    }

    public final void clear() {
        unicastList.clear();
    }

    public final int count() {
        return unicastList.size();
    }

    public List<F> getUnicastList() {
        return new ArrayList<>(unicastList);
    }

    private boolean addUnicast(F unicast) {
        return unicastList.add(unicast);
    }

    private boolean addMulticast(Multicast<F> multicast) {
        boolean isAdd = false;
        for (F unicast : multicast.unicastList) {
            isAdd = addUnicast(unicast);
        }
        return isAdd;
    }

    private boolean removeUnicast(F unicast) {
        return unicastList.remove(unicast);
    }

    private boolean removeMulticast(Multicast<F> multicast) {
        int sourceCount = count();
        int targetCount = multicast.count();

        for (int i = sourceCount - targetCount; i >= 0; i--) {
            if (equal(multicast.unicastList, i, targetCount)) {
                delete(unicastList, i, targetCount);
                return true;
            }
        }

        return false;
    }

    private boolean containsUnicast(F unicast) {
        return unicastList.contains(unicast);
    }

    private boolean containsMulticast(Multicast<F> multicast) {
        int sourceCount = count();
        int targetCount = multicast.count();

        for (int i = sourceCount - targetCount; i >= 0; i--) {
            if (equal(multicast.unicastList, i, targetCount)) {
                return true;
            }
        }

        return false;
    }

    private boolean equal(List<F> unicastList, int start, int count) {
        for (int i = 0; i < count; i++) {
            if (!this.unicastList.get(i + start).equals(unicastList.get(i))) {
                return false;
            }
        }
        return true;
    }

    private void delete(List<F> unicastList, int start, int count) {
        unicastList.subList(start, start + count).clear();
    }

    @Override
    public Iterator<F> iterator() {
        List<F> snapshot = new ArrayList<>(unicastList);
        return snapshot.iterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Multicast<?>)) {
            return false;
        }
        Multicast<?> multicast = (Multicast<?>) obj;
        return unicastList.equals(multicast.unicastList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unicastList);
    }
}
