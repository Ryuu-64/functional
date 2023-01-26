package org.ryuu.functional;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MulticastTest {
    @Test
    void invokeMultiThreadSafe() {
        final String[] result = {""};
        Actions actions = new Actions();
        actions.add(() -> result[0] += "1");
        actions.add(() -> result[0] += "2");
        actions.add(() -> result[0] += "3");
        actions.add(() -> result[0] += "4");
        actions.add(() -> result[0] += "5");
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            threads.add(new Thread(actions::invoke));
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
    void add() {
        StringBuilder stringBuilder = new StringBuilder();
        Actions actions1 = new Actions();
        actions1.add(() -> stringBuilder.append(0));
        actions1.add(() -> stringBuilder.append(1));
        actions1.add(() -> stringBuilder.append(2));
        actions1.invoke();
        assertEquals(stringBuilder.toString(), "012");

        stringBuilder.delete(0, stringBuilder.length());
        Actions actions2 = new Actions();
        actions2.add(() -> stringBuilder.append(0));
        actions2.add(() -> stringBuilder.append(1));
        actions2.add(() -> stringBuilder.append(2));
        actions2.invoke();
        assertEquals(stringBuilder.toString(), "012");

        stringBuilder.delete(0, stringBuilder.length());
        actions1.add(actions2);
        actions1.invoke();
        assertEquals(stringBuilder.toString(), "012012");
    }

    @Test
    void addNull() {
        Actions actions = new Actions();
        assertFalse(actions.add(null));
        assertEquals(actions.count(), 0);
    }

    @SuppressWarnings("UnnecessaryBoxing")
    @Test
    void addContravariantUnicast() {
        Func<Number> func = () -> Double.valueOf(1);
        assertEquals(func.invoke(), 1.0d);
    }

    @Test
    void remove() {
        StringBuilder stringBuilder = new StringBuilder();
        Actions actions1 = new Actions();
        Actions actions2 = new Actions();

        Action iAction0 = () -> stringBuilder.append(0);
        Action iAction1 = () -> stringBuilder.append(1);
        Action iAction2 = () -> stringBuilder.append(2);
        Action iAction3 = () -> stringBuilder.append(3);
        Action iAction4 = () -> stringBuilder.append(4);

        actions1.add(iAction0);
        actions1.add(iAction1);
        actions1.add(iAction2);
        actions1.add(iAction3);
        actions1.add(iAction4);

        actions2.add(iAction3);
        actions2.add(iAction4);

        assertTrue(actions1.remove(actions2));
        assertFalse(actions1.remove(actions2));

        actions1.invoke();
        assertEquals(stringBuilder.toString(), "012");
    }

    @Test
    void removeNull() {
        Actions actions = new Actions();
        assertFalse(actions.remove(null));
        assertEquals(actions.count(), 0);
    }

    @Test
    void contains() {
        Actions actions1 = new Actions();
        Actions actions2 = new Actions();

        Action action0 = () -> System.out.println(0);
        Action action1 = () -> System.out.println(1);
        Action action2 = () -> System.out.println(2);
        Action action3 = () -> System.out.println(3);
        Action action4 = () -> System.out.println(4);

        actions1.add(action0);
        actions1.add(action1);
        actions1.add(action2);
        actions1.add(action3);
        actions1.add(action4);

        actions2.add(action2);
        actions2.add(action3);
        actions2.add(action4);
        assertTrue(actions1.contains(actions2));

        actions2.add(action4);
        assertFalse(actions1.contains(actions2));
    }

    @Test
    void containsNull() {
        Actions actions = new Actions();
        assertFalse(actions.contains(null));
    }

    @Test
    void clear() {
        StringBuilder stringBuilder = new StringBuilder();
        Actions actions = new Actions();
        actions.add(() -> stringBuilder.append(0));
        actions.add(() -> stringBuilder.append(1));
        actions.clear();
        actions.invoke();
        assertEquals("", stringBuilder.toString());
    }

    @Test
    void cleanTheMulticastInTheMulticast() {
        StringBuilder stringBuilder = new StringBuilder();
        Actions actions = new Actions();
        actions.add(() -> stringBuilder.append(0));
        actions.add(() -> stringBuilder.append(1));
        actions.add(actions::clear);
        actions.add(() -> stringBuilder.append(2));
        actions.invoke();
        assertEquals(stringBuilder.toString(), "01");
    }

    @Test
    void count() {
        Actions actions = new Actions();
        assertEquals(0, actions.count());
        actions.add(() -> {});
        assertEquals(1, actions.count());
    }

    @Test
    void getFunctionalList() {
        Actions actions = new Actions();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            actions.add(() -> stringBuilder.append(finalI));
        }
        List<Action> functionalList = actions.getUnicastList();
        for (Action act : functionalList) {
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
        for (Action act : functionalList) {
            act.invoke();
        }
        assertEquals(stringBuilder.toString(), "01234");

        stringBuilder.delete(0, stringBuilder.length());
        actions.invoke();
        // origin functional list will not change
        assertEquals(stringBuilder.toString(), "01234");
    }

    @Test
    void iterator() {
        final int[] res = {0};
        Actions actions = new Actions();
        actions.add(() -> res[0]++);
        actions.add(() -> res[0]++);
        actions.add(() -> res[0]++);
        actions.add(() -> res[0]++);
        actions.add(() -> res[0]++);
        for (Action action : actions) {
            action.invoke();
        }
        assertEquals(5, res[0]);
    }

    @Test
    void testEquals() {
        Action println1 = () -> System.out.println(1);
        Action println2 = () -> System.out.println(2);

        Actions actions1 = new Actions();
        Actions actions2 = new Actions();

        actions1.add(println1);
        actions1.add(println2);
        actions2.add(println1);
        actions2.add(println2);
        assertEquals(actions1, actions2);
        assertEquals(actions2, actions1);

        actions1.add(println1);
        assertNotEquals(actions1, actions2);
        assertNotEquals(actions2, actions1);
    }

    @Test
    void testHashCode() {
        Action println1 = () -> System.out.println(1);
        Action println2 = () -> System.out.println(2);
        Actions actions1 = new Actions();
        Actions actions2 = new Actions();
        actions1.add(println1);
        actions1.add(println2);
        actions2.add(println1);
        actions2.add(println2);
        assertEquals(actions1.hashCode(), actions2.hashCode());

        actions1.add(println1);
        assertNotEquals(actions1.hashCode(), actions2.hashCode());
    }

    @Test
    void sideEffect() {
        final int[] res = {0};
        Actions actions1 = new Actions();
        actions1.add(() -> res[0]++);

        Actions actions2 = new Actions();
        actions2.add(actions1);

        actions1.add(() -> res[0]++);

        actions2.invoke();
        assertEquals(1, res[0]);
    }

    @Test
    void testJavaObjectLayout() {
        Actions actions = new Actions();
/*
org.ryuu.functional.Actions object internals:
OFF  SZ                                           TYPE DESCRIPTION               VALUE
  0   8                                                (object header: mark)     0x0000000000000001 (non-biasable; age: 0)
  8   4                                                (object header: class)    0x20044100
 12   4                                 java.util.List Multicast.unicastList     (object)
 16   4   org.ryuu.functional.Multicast.SharedIterator Multicast.iterator        (object)
 20   4                                                (object alignment gap)
Instance size: 24 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
*/
        ClassLayout classLayout = ClassLayout.parseInstance(actions);
        assertEquals(8 + 4 + 4 + 4 + 4, classLayout.instanceSize());
        System.out.println(classLayout.toPrintable());

/*
org.ryuu.functional.Actions@4189d70bd object externals:
SIZE TYPE                                         PATH                           VALUE
  16 [Ljava.lang.Object;                          .unicastList.elementData       []
  24 org.ryuu.functional.Actions                                                 (object)
  24 java.util.ArrayList                          .unicastList                   (object)
  24 org.ryuu.functional.Multicast$SharedIterator .iterator                      (object)

org.ryuu.functional.Multicast$SharedIterator object internals:
OFF  SZ                            TYPE DESCRIPTION               VALUE
  0   8                                 (object header: mark)     0x00000058fe049901 (hash: 0x58fe0499; age: 0)
  8   4                                 (object header: class)    0x20044156
 12   4                             int SharedIterator.cursor     0
 16   4   org.ryuu.functional.Multicast SharedIterator.this$0     (object)
 20   4                                 (object alignment gap)
Instance size: 24 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
*/
        GraphLayout graphLayout = GraphLayout.parseInstance(actions);
        assertEquals(16 + 24 + 24 + 24, graphLayout.totalSize());
        System.out.println(graphLayout.toPrintable());
    }
}