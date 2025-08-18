package org.ryuu.functional;

import java.util.*;

abstract class Multicast<F extends Unicast> implements Unicast, Iterable<F> {
    protected List<F> unicastList = Collections.emptyList();

    @SuppressWarnings("unchecked")
    public final void add(F functional) {
        if (functional == null) {
            return;
        }

        if (functional instanceof Multicast) {
            addMulticast((Multicast<F>) functional);
        } else /* if (functional instanceof Unicast) */ {
            addUnicast(functional);
        }
    }

    @SuppressWarnings("unchecked")
    public final void remove(F functional) {
        if (functional == null) {
            return;
        }

        if (functional instanceof Multicast) {
            removeMulticast((Multicast<F>) functional);
        } else /* if (functional instanceof Unicast) */ {
            removeUnicast(functional);
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
        unicastList = Collections.emptyList();
    }

    public final int count() {
        return unicastList.size();
    }

    public List<F> getUnicastList() {
        return new ArrayList<>(unicastList);
    }

    private void addUnicast(F unicast) {
        List<F> newList = new ArrayList<>(unicastList);
        newList.add(unicast);
        unicastList = Collections.unmodifiableList(newList);
    }

    private void addMulticast(Multicast<F> multicast) {
        if (multicast.unicastList.isEmpty()) {
            return;
        }

        List<F> newList = new ArrayList<>(unicastList);
        newList.addAll(multicast.unicastList);
        unicastList = Collections.unmodifiableList(newList);
    }

    private void removeUnicast(F unicast) {
        if (!unicastList.contains(unicast)) {
            return;
        }

        List<F> newList = new ArrayList<>(unicastList);
        newList.remove(unicast);
        unicastList = Collections.unmodifiableList(newList);
    }

    private void removeMulticast(Multicast<F> multicast) {
        int sourceCount = count();
        int targetCount = multicast.count();

        for (int i = sourceCount - targetCount; i >= 0; i--) {
            if (equal(multicast.unicastList, i, targetCount)) {
                List<F> newList = new ArrayList<>(unicastList);
                newList.subList(i, i + targetCount).clear();
                unicastList = Collections.unmodifiableList(newList);
                return;
            }
        }
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

    @Override
    public Iterator<F> iterator() {
        return unicastList.iterator();
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
