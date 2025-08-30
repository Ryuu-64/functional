package org.ryuu.functional;

import java.util.*;

public abstract class MulticastDelegate<T extends Delegate> implements Iterable<T>, Delegate, Event<T> {
    private interface DelegatesHolder<T> {
        List<T> get();

        void addDelegate(T delegate);

        void addAll(List<T> delegates);

        void removeDelegate(T delegate);

        void removeSubList(int start, int count);

        void clear();
    }

    private static final class PlainDelegatesHolder<T> implements DelegatesHolder<T> {
        private List<T> delegates = Collections.emptyList();

        @Override
        public List<T> get() {
            return delegates;
        }

        @Override
        public void addDelegate(T delegate) {
            List<T> newDelegates = new ArrayList<>(delegates);
            newDelegates.add(delegate);
            this.delegates = Collections.unmodifiableList(newDelegates);
        }

        @Override
        public void addAll(List<T> delegatesToAdd) {
            if (delegatesToAdd.isEmpty()) return;
            List<T> newDelegates = new ArrayList<>(delegates);
            newDelegates.addAll(delegatesToAdd);
            this.delegates = Collections.unmodifiableList(newDelegates);
        }

        @Override
        public void removeDelegate(T delegate) {
            if (!delegates.contains(delegate)) return;
            List<T> newDelegates = new ArrayList<>(delegates);
            newDelegates.remove(delegate);
            this.delegates = Collections.unmodifiableList(newDelegates);
        }

        @Override
        public void removeSubList(int start, int count) {
            List<T> newDelegates = new ArrayList<>(delegates);
            newDelegates.subList(start, start + count).clear();
            this.delegates = Collections.unmodifiableList(newDelegates);
        }

        @Override
        public void clear() {
            this.delegates = Collections.emptyList();
        }
    }

    private static final class EventDelegatesHolder<T> implements DelegatesHolder<T> {
        private volatile List<T> delegates = Collections.emptyList();

        @Override
        public List<T> get() {
            return delegates;
        }

        @Override
        public synchronized void addDelegate(T delegate) {
            List<T> newDelegates = new ArrayList<>(delegates);
            newDelegates.add(delegate);
            this.delegates = Collections.unmodifiableList(newDelegates);
        }

        @Override
        public synchronized void addAll(List<T> delegatesToAdd) {
            if (delegatesToAdd.isEmpty()) return;
            List<T> newDelegates = new ArrayList<>(delegates);
            newDelegates.addAll(delegatesToAdd);
            this.delegates = Collections.unmodifiableList(newDelegates);
        }

        @Override
        public synchronized void removeDelegate(T delegate) {
            if (!delegates.contains(delegate)) return;
            List<T> newDelegates = new ArrayList<>(delegates);
            newDelegates.remove(delegate);
            this.delegates = Collections.unmodifiableList(newDelegates);
        }

        @Override
        public synchronized void removeSubList(int start, int count) {
            List<T> newDelegates = new ArrayList<>(delegates);
            newDelegates.subList(start, start + count).clear();
            this.delegates = Collections.unmodifiableList(newDelegates);
        }

        @Override
        public synchronized void clear() {
            this.delegates = Collections.emptyList();
        }
    }

    private final DelegatesHolder<T> delegatesHolder;

    public MulticastDelegate(boolean isEvent) {
        delegatesHolder = isEvent ? new EventDelegatesHolder<T>() : new PlainDelegatesHolder<T>();
    }

    @Override
    public final Iterator<T> iterator() {
        return delegatesHolder.get().iterator();
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
        return delegatesHolder.get().equals(multicastDelegate.delegatesHolder.get());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(delegatesHolder.get());
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void add(T delegate) {
        if (delegate == null) {
            return;
        }

        if (delegate instanceof MulticastDelegate) {
            addMulticastDelegate((MulticastDelegate<T>) delegate);
        } else /* if (delegate instanceof Delegate) */ {
            addDelegate(delegate);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public final void remove(T delegate) {
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
        delegatesHolder.clear();
    }

    public final int count() {
        return delegatesHolder.get().size();
    }

    public final List<T> getDelegates() {
        return new ArrayList<>(delegatesHolder.get());
    }

    private void addDelegate(T delegate) {
        delegatesHolder.addDelegate(delegate);
    }

    private void addMulticastDelegate(MulticastDelegate<T> other) {
        delegatesHolder.addAll(other.delegatesHolder.get());
    }

    private void removeDelegate(T delegate) {
        delegatesHolder.removeDelegate(delegate);
    }

    private void removeMulticastDelegate(MulticastDelegate<T> target) {
        int sourceCount = count();
        int targetCount = target.count();
        List<T> targetDelegates = target.delegatesHolder.get();
        for (int i = sourceCount - targetCount; i >= 0; i--) {
            if (subListEquals(targetDelegates, i, targetCount)) {
                delegatesHolder.removeSubList(i, targetCount);
                return;
            }
        }
    }

    private boolean containsDelegate(T delegate) {
        return delegatesHolder.get().contains(delegate);
    }

    private boolean containsMulticastDelegate(MulticastDelegate<T> target) {
        int sourceCount = count();
        int targetCount = target.count();
        List<T> targetDelegates = target.delegatesHolder.get();
        for (int i = sourceCount - targetCount; i >= 0; i--) {
            if (subListEquals(targetDelegates, i, targetCount)) {
                return true;
            }
        }

        return false;
    }

    private boolean subListEquals(List<T> target, int start, int count) {
        List<T> delegates = delegatesHolder.get();
        for (int i = 0; i < count; i++) {
            if (!delegates.get(i + start).equals(target.get(i))) {
                return false;
            }
        }
        return true;
    }
}
