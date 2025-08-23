package org.ryuu.functional;

import java.util.*;

abstract class MulticastDelegate<F extends Delegate> implements Delegate, Iterable<F> {
    private final Object delegatesLock = new Object();
    private List<F> delegates = Collections.emptyList();

    @Override
    public final Iterator<F> iterator() {
        return delegates.iterator();
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MulticastDelegate<?>)) {
            return false;
        }

        MulticastDelegate<?> multicastDelegate = (MulticastDelegate<?>) obj;
        return delegates.equals(multicastDelegate.delegates);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(delegates);
    }

    public final void add(F functional) {
        synchronized (delegatesLock) {
            addInternal(functional);
        }
    }

    public final void remove(F functional) {
        synchronized (delegatesLock) {
            removeInternal(functional);
        }
    }

    public final void addAsync(F functional) {
        synchronized (delegatesLock) {
            addInternal(functional);
        }
    }

    public final void removeAsync(F functional) {
        synchronized (delegatesLock) {
            removeInternal(functional);
        }
    }

    @SuppressWarnings("unchecked")
    public final boolean contains(F functional) {
        if (functional == null) {
            return false;
        } else if (functional instanceof MulticastDelegate) {
            return containsMulticast((MulticastDelegate<F>) functional);
        } else /* if (functional instanceof Delegate) */ {
            return containsUnicast(functional);
        }
    }

    public final void clear() {
        delegates = Collections.emptyList();
    }

    public final int count() {
        return delegates.size();
    }

    public final List<F> getDelegates() {
        return new ArrayList<>(delegates);
    }

    @SuppressWarnings("unchecked")
    private void removeInternal(F functional) {
        if (functional == null) {
            return;
        }

        if (functional instanceof MulticastDelegate) {
            removeMulticast((MulticastDelegate<F>) functional);
        } else /* if (functional instanceof Delegate) */ {
            removeUnicast(functional);
        }
    }

    @SuppressWarnings("unchecked")
    private void addInternal(F functional) {
        if (functional == null) {
            return;
        }

        if (functional instanceof MulticastDelegate) {
            addMulticast((MulticastDelegate<F>) functional);
        } else /* if (functional instanceof Delegate) */ {
            addUnicast(functional);
        }
    }

    private void addUnicast(F unicast) {
        List<F> newList = new ArrayList<>(delegates);
        newList.add(unicast);
        delegates = Collections.unmodifiableList(newList);
    }

    private void addMulticast(MulticastDelegate<F> multicastDelegate) {
        if (multicastDelegate.delegates.isEmpty()) {
            return;
        }

        List<F> newList = new ArrayList<>(delegates);
        newList.addAll(multicastDelegate.delegates);
        delegates = Collections.unmodifiableList(newList);
    }

    private void removeUnicast(F unicast) {
        if (!delegates.contains(unicast)) {
            return;
        }

        List<F> newList = new ArrayList<>(delegates);
        newList.remove(unicast);
        delegates = Collections.unmodifiableList(newList);
    }

    private void removeMulticast(MulticastDelegate<F> multicastDelegate) {
        int sourceCount = count();
        int targetCount = multicastDelegate.count();

        for (int i = sourceCount - targetCount; i >= 0; i--) {
            if (equal(multicastDelegate.delegates, i, targetCount)) {
                List<F> newList = new ArrayList<>(delegates);
                newList.subList(i, i + targetCount).clear();
                delegates = Collections.unmodifiableList(newList);
                return;
            }
        }
    }

    private boolean containsUnicast(F unicast) {
        return delegates.contains(unicast);
    }

    private boolean containsMulticast(MulticastDelegate<F> multicastDelegate) {
        int sourceCount = count();
        int targetCount = multicastDelegate.count();

        for (int i = sourceCount - targetCount; i >= 0; i--) {
            if (equal(multicastDelegate.delegates, i, targetCount)) {
                return true;
            }
        }

        return false;
    }

    private boolean equal(List<F> unicastList, int start, int count) {
        for (int i = 0; i < count; i++) {
            if (!this.delegates.get(i + start).equals(unicastList.get(i))) {
                return false;
            }
        }
        return true;
    }
}
