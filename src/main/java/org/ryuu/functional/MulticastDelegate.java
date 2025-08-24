package org.ryuu.functional;

import java.util.*;

public abstract class MulticastDelegate<F extends Delegate> implements Iterable<F>, Delegate, Event<F> {
    private final boolean isEvent;
    private final Object delegatesLock = new Object();
    private List<F> delegates = Collections.emptyList();

    public MulticastDelegate(boolean isEvent) {
        this.isEvent = isEvent;
    }

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

    @Override
    public final void add(F delegate) {
        if (isEvent) {
            addSync(delegate);
        } else {
            addAsync(delegate);
        }
    }

    @Override
    public final void remove(F delegate) {
        if (isEvent) {
            removeSync(delegate);
        } else {
            removeAsync(delegate);
        }
    }

    @SuppressWarnings("unchecked")
    public final boolean contains(F delegate) {
        if (delegate == null) {
            return false;
        } else if (delegate instanceof MulticastDelegate) {
            return containsMulticast((MulticastDelegate<F>) delegate);
        } else /* if (delegate instanceof Delegate) */ {
            return containsUnicast(delegate);
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

    private void addSync(F delegate) {
        synchronized (delegatesLock) {
            addInternal(delegate);
        }
    }

    private void removeSync(F delegate) {
        synchronized (delegatesLock) {
            removeInternal(delegate);
        }
    }

    private void addAsync(F delegate) {
        addInternal(delegate);
    }

    private void removeAsync(F delegate) {
        removeInternal(delegate);
    }

    @SuppressWarnings("unchecked")
    private void removeInternal(F delegate) {
        if (delegate == null) {
            return;
        }

        if (delegate instanceof MulticastDelegate) {
            removeMulticast((MulticastDelegate<F>) delegate);
        } else /* if (delegate instanceof Delegate) */ {
            removeUnicast(delegate);
        }
    }

    @SuppressWarnings("unchecked")
    private void addInternal(F delegate) {
        if (delegate == null) {
            return;
        }

        if (delegate instanceof MulticastDelegate) {
            addMulticast((MulticastDelegate<F>) delegate);
        } else /* if (delegate instanceof Delegate) */ {
            addUnicast(delegate);
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
