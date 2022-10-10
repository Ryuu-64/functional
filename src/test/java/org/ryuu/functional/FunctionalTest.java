package org.ryuu.functional;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionalTest {
    @SuppressWarnings("MismatchedQueryAndUpdateOfStringBuilder")
    @Test
    void addTest() {
        StringBuilder stringBuilder = new StringBuilder();
        Action action1 = new Action();
        action1.add(() -> stringBuilder.append(0));
        action1.add(() -> stringBuilder.append(1));
        action1.add(() -> stringBuilder.append(2));
        action1.invoke();
        assertEquals(stringBuilder.toString(), "012");

        stringBuilder.delete(0, stringBuilder.length());
        Action action2 = new Action();
        action2.add(() -> stringBuilder.append(0));
        action2.add(() -> stringBuilder.append(1));
        action2.add(() -> stringBuilder.append(2));
        action2.invoke();
        assertEquals(stringBuilder.toString(), "012");

        stringBuilder.delete(0, stringBuilder.length());
        action1.add(action2);
        action1.invoke();
        assertEquals(stringBuilder.toString(), "012012");
    }

    @Test
    void addNullTest() {
        Action action = new Action();
        assertFalse(action.add(null));
        assertEquals(action.count(), 0);
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfStringBuilder")
    @Test
    void removeTest() {
        StringBuilder stringBuilder = new StringBuilder();
        Action action1 = new Action();
        Action action2 = new Action();

        IAction iAction0 = () -> stringBuilder.append(0);
        IAction iAction1 = () -> stringBuilder.append(1);
        IAction iAction2 = () -> stringBuilder.append(2);
        IAction iAction3 = () -> stringBuilder.append(3);
        IAction iAction4 = () -> stringBuilder.append(4);

        action1.add(iAction0);
        action1.add(iAction1);
        action1.add(iAction2);
        action1.add(iAction3);
        action1.add(iAction4);

        action2.add(iAction3);
        action2.add(iAction4);

        assertTrue(action1.remove(action2));
        assertFalse(action1.remove(action2));

        action1.invoke();
        assertEquals(stringBuilder.toString(), "012");
    }

    @Test
    void removeNullTest() {
        Action action = new Action();
        assertFalse(action.remove(null));
        assertEquals(action.count(), 0);
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfStringBuilder")
    @Test
    void getFunctionalListTest() {
        Action action = new Action();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            action.add(() -> stringBuilder.append(finalI));
        }
        List<IAction> functionalList = action.getUnicastList();
        for (IAction act : functionalList) {
            act.invoke();
        }
        assertEquals(stringBuilder.toString(), "01234");
        stringBuilder.delete(0, stringBuilder.length());

        // copy list remove
        functionalList.clear();
        assertEquals(stringBuilder.toString(), "");

        stringBuilder.delete(0, stringBuilder.length());
        // copy list add
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            functionalList.add(() -> stringBuilder.append(finalI));
        }
        for (IAction act : functionalList) {
            act.invoke();
        }
        assertEquals(stringBuilder.toString(), "01234");

        stringBuilder.delete(0, stringBuilder.length());
        action.invoke();
        // origin functional list will not change
        assertEquals(stringBuilder.toString(), "01234");
    }

    @Test
    void containsNullTest() {
        Action action = new Action();
        assertFalse(action.contains(null));
    }

    @Test
    void containsTest() {
        Action action1 = new Action();
        Action action2 = new Action();

        IAction iAction0 = () -> System.out.println(0);
        IAction iAction1 = () -> System.out.println(1);
        IAction iAction2 = () -> System.out.println(2);
        IAction iAction3 = () -> System.out.println(3);
        IAction iAction4 = () -> System.out.println(4);

        action1.add(iAction0);
        action1.add(iAction1);
        action1.add(iAction2);
        action1.add(iAction3);
        action1.add(iAction4);

        action2.add(iAction2);
        action2.add(iAction3);
        action2.add(iAction4);
        assertTrue(action1.contains(action2));

        action2.add(iAction4);
        assertFalse(action1.contains(action2));
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfStringBuilder")
    @Test
    void clearTest() {
        StringBuilder stringBuilder = new StringBuilder();
        Action action = new Action();
        action.add(() -> stringBuilder.append(0));
        action.add(() -> stringBuilder.append(1));
        action.add(action::clear);
        action.add(() -> stringBuilder.append(2));
        action.invoke();
        assertEquals(stringBuilder.toString(), "01");
    }

    @SuppressWarnings("UnnecessaryBoxing")
    @Test
    void contravariantTest() {
        IFunc<Number> action = () -> Double.valueOf(1);
        assertEquals(action.invoke(), 1.0d);
    }

    @Test
    void multiThreadSafeTest() {
        final String[] result = {""};
        Action action = new Action();
        action.add(() -> result[0] += "1");
        action.add(() -> result[0] += "2");
        action.add(() -> result[0] += "3");
        action.add(() -> result[0] += "4");
        action.add(() -> result[0] += "5");
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            threads.add(new Thread(action::invoke));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        StringBuilder expected = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            expected.append("12345");
        }
        while (true) {
            if (threads.stream().noneMatch(Thread::isAlive)) {
                assertEquals(expected.toString(), result[0]);
                return;
            }
        }
    }

    @Test
    void equalsTest() {
        IAction println1 = () -> System.out.println(1);
        IAction println2 = () -> System.out.println(2);
        Action action1 = new Action();
        Action action2 = new Action();
        action1.add(println1);
        action1.add(println2);
        action2.add(println1);
        action2.add(println2);
        assertEquals(action1, action2);
        assertEquals(action2, action1);

        action1.add(println1);
        assertNotEquals(action1, action2);
        assertNotEquals(action2, action1);
    }

    @Test
    void hashCodeTest() {
        IAction println1 = () -> System.out.println(1);
        IAction println2 = () -> System.out.println(2);
        Action action1 = new Action();
        Action action2 = new Action();
        action1.add(println1);
        action1.add(println2);
        action2.add(println1);
        action2.add(println2);
        assertEquals(action1.hashCode(), action2.hashCode());

        action1.add(println1);
        assertNotEquals(action1.hashCode(), action2.hashCode());
    }
}