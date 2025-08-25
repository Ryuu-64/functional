package org.ryuu.functional;

import java.util.*;

public abstract class MulticastDelegate<T extends Delegate> implements Iterable<T>, Delegate, Event<T> {
    private final boolean isEvent;
    private final Object delegatesLock = new Object();
    private List<T> delegates = Collections.emptyList();

    public MulticastDelegate(boolean isEvent) {
        this.isEvent = isEvent;
    }

    @Override
    public final Iterator<T> iterator() {
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
    public final void add(T delegate) {
        if (isEvent) {
            addSync(delegate);
        } else {
            addAsync(delegate);
        }
    }

    @Override
    public final void remove(T delegate) {
        if (isEvent) {
            removeSync(delegate);
        } else {
            removeAsync(delegate);
        }
    }

    @SuppressWarnings("unchecked")
    public final boolean contains(T delegate) {
        if (delegate == null) {
            return false;
        } else if (delegate instanceof MulticastDelegate) {
            return containsMulticastDelegate((MulticastDelegate<T>) delegate);
        } else /* if (delegate instanceof Delegate) */ {
            return containsDelegate(delegate);
        }
    }

    public final void clear() {
        delegates = Collections.emptyList();
    }

    public final int count() {
        return delegates.size();
    }

    public final List<T> getDelegates() {
        return new ArrayList<>(delegates);
    }

    private void addSync(T delegate) {
        synchronized (delegatesLock) {
            addInternal(delegate);
        }
    }

    private void removeSync(T delegate) {
        synchronized (delegatesLock) {
            removeInternal(delegate);
        }
    }

    private void addAsync(T delegate) {
        addInternal(delegate);
    }

    private void removeAsync(T delegate) {
        removeInternal(delegate);
    }

    @SuppressWarnings("unchecked")
    private void removeInternal(T delegate) {
        if (delegate == null) {
            return;
        }

        if (delegate instanceof MulticastDelegate) {
            removeMulticastDelegate((MulticastDelegate<T>) delegate);
        } else /* if (delegate instanceof Delegate) */ {
            removeDelegate(delegate);
        }
    }

    @SuppressWarnings("unchecked")
    private void addInternal(T delegate) {
        if (delegate == null) {
            return;
        }

        if (delegate instanceof MulticastDelegate) {
            addMulticastDelegate((MulticastDelegate<T>) delegate);
        } else /* if (delegate instanceof Delegate) */ {
            addDelegate(delegate);
        }
    }

    private void addDelegate(T delegate) {
        List<T> newList = new ArrayList<>(delegates);
        newList.add(delegate);
        delegates = Collections.unmodifiableList(newList);
    }

    private void addMulticastDelegate(MulticastDelegate<T> multicastDelegate) {
        if (multicastDelegate.delegates.isEmpty()) {
            return;
        }

        List<T> newList = new ArrayList<>(delegates);
        newList.addAll(multicastDelegate.delegates);
        delegates = Collections.unmodifiableList(newList);
    }

    private void removeDelegate(T delegate) {
        if (!delegates.contains(delegate)) {
            return;
        }

        List<T> newList = new ArrayList<>(delegates);
        newList.remove(delegate);
        delegates = Collections.unmodifiableList(newList);
    }

    private void removeMulticastDelegate(MulticastDelegate<T> multicastDelegate) {
        int sourceCount = count();
        int targetCount = multicastDelegate.count();

        for (int i = sourceCount - targetCount; i >= 0; i--) {
            if (subListEquals(multicastDelegate.delegates, i, targetCount)) {
                List<T> newList = new ArrayList<>(delegates);
                newList.subList(i, i + targetCount).clear();
                delegates = Collections.unmodifiableList(newList);
                return;
            }
        }
    }

    private boolean containsDelegate(T delegate) {
        return delegates.contains(delegate);
    }

    private boolean containsMulticastDelegate(MulticastDelegate<T> multicastDelegate) {
        int sourceCount = count();
        int targetCount = multicastDelegate.count();

        for (int i = sourceCount - targetCount; i >= 0; i--) {
            if (subListEquals(multicastDelegate.delegates, i, targetCount)) {
                return true;
            }
        }

        return false;
    }

    private boolean subListEquals(List<T> delegates, int start, int count) {
        for (int i = 0; i < count; i++) {
            if (!this.delegates.get(i + start).equals(delegates.get(i))) {
                return false;
            }
        }
        return true;
    }
}
