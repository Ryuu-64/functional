package org.ryuu.functional;

import org.junit.jupiter.api.Test;

import java.util.List;

public class FunctionalTest {
    private FunctionalTest() {
    }

    @Test
    void addGenericCheck() {
        Action1Arg<Integer> action1 = new Action1Arg<>();
//        action1.add((IAction1Arg<Double>) (Double $double) -> System.out.println($double));
//        action1.add((Double $double) -> System.out.println($double));
        Action1Arg<Double> action2 = new Action1Arg<>();
//        action1.add(action2);
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfStringBuilder")
    @Test
    void addMulticast() {
        StringBuilder stringBuilder = new StringBuilder();
        Action1Arg<Integer> action1 = new Action1Arg<>();
        action1.add(stringBuilder::append);
        action1.add(($int) -> stringBuilder.append($int + 1));
        action1.add(($int) -> stringBuilder.append($int + 2));
        action1.invoke(0);
        assert stringBuilder.toString().equals("012");
        stringBuilder.delete(0, stringBuilder.length());

        Action1Arg<Integer> action2 = new Action1Arg<>();
        action2.add(stringBuilder::append);
        action2.add(($int) -> stringBuilder.append($int + 1));
        action2.add(($int) -> stringBuilder.append($int + 2));
        action2.invoke(0);
        assert stringBuilder.toString().equals("012");
        stringBuilder.delete(0, stringBuilder.length());

        action1.add(action2);
        action1.invoke(0);
        assert stringBuilder.toString().equals("012012");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void addNull() {
        Action action = new Action();
        assert !action.add(null);
        assert action.count() == 0;
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfStringBuilder")
    @Test
    void removeMulticast() {
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

        assert action1.remove(action2);
        assert !action1.remove(action2);

        action1.invoke();
        assert stringBuilder.toString().equals("012");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void removeNull() {
        Action action = new Action();
        assert !action.remove(null);
        assert action.count() == 0;
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfStringBuilder")
    @Test
    void getFunctionalList() {
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
        assert stringBuilder.toString().equals("01234");
        stringBuilder.delete(0, stringBuilder.length());

        // copy list remove
        functionalList.clear();
        assert stringBuilder.toString().equals("");
        stringBuilder.delete(0, stringBuilder.length());

        // copy list add
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            functionalList.add(() -> stringBuilder.append(finalI));
        }
        for (IAction act : functionalList) {
            act.invoke();
        }
        assert stringBuilder.toString().equals("01234");
        stringBuilder.delete(0, stringBuilder.length());

        // origin not change
        action.invoke();
        assert stringBuilder.toString().equals("01234");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void containsNull() {
        Action action = new Action();
        assert !action.contains(null);
    }

    @Test
    void containsMulticast() {
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

        assert action1.contains(action2);

        action2.add(iAction4);

        assert !action1.contains(action2);
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfStringBuilder")
    @Test
    void clear() {
        StringBuilder stringBuilder = new StringBuilder();
        Action1Arg<Integer> action = new Action1Arg<>();
        action.add(stringBuilder::append);
        action.add(($int) -> stringBuilder.append($int + 1));
        action.add(($int) -> action.clear());
        action.add(($int) -> stringBuilder.append($int + 2));
        action.invoke(0);
        assert stringBuilder.toString().equals("01");
        stringBuilder.delete(0, stringBuilder.length());
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    void generic() {
        Action1Arg<Object> action1 = new Action1Arg<>();
//        action1.add((Float $float) -> System.out.println($float));
        Action1Arg<Object> action2 = new Action1Arg<>();
        action2.add((Object object) -> System.out.println(object));
        assert action1.add(action2);
//        action1.add(new Action1Arg<Float>());
    }

    @SuppressWarnings("UnnecessaryBoxing")
    @Test
    void contravariant() {
        Action1Arg<Number> action = new Action1Arg<>();
        action.add(System.out::println);
        action.invoke(Double.valueOf(1));
    }

    @Test
    void covariant() {
        Action1Arg<Double> action = new Action1Arg<>();
        action.add(System.out::println);
//        action.invoke(new Number() {
//            @Override
//            public int intValue() {
//                return 0;
//            }
//
//            @Override
//            public long longValue() {
//                return 0;
//            }
//
//            @Override
//            public float floatValue() {
//                return 0;
//            }
//
//            @Override
//            public double doubleValue() {
//                return 0;
//            }
//        });
    }

    @Test
    void multiThread() {
        final String[] result = {""};
        Action action = new Action();
        action.add(() -> result[0] += "1");
        action.add(() -> result[0] += "2");
        action.add(() -> result[0] += "3");
        action.add(() -> result[0] += "4");
        action.add(() -> result[0] += "5");
        action.add(() -> {
            System.out.println(result[0]);
            assert result[0].equals("12345");
            result[0] = "";
        });

        for (int i = 0; i < 16; i++) {
            new Thread(() -> {
                while (true) {
                    action.invoke();
                }
            }).start();

        }
    }

    @Test
    void equals() {
        IAction println1 = () -> System.out.println(1);
        IAction println2 = () -> System.out.println(2);
        Action action1 = new Action();
        Action action2 = new Action();
        action1.add(println1);
        action1.add(println2);
        action2.add(println1);
        action2.add(println2);
        assert action1.equals(action2);
        assert action2.equals(action1);
        action1.add(println1);
        assert !action1.equals(action2);
        assert !action2.equals(action1);
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
        assert action1.hashCode() == action2.hashCode();
        action1.add(println1);
        assert action1.hashCode() != action2.hashCode();
    }
}