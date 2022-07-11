package pers.ryuu.event;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DelegateConcurrentTest {

    @Test
    void removeAfterTargetInvoke() {
        ArrayList<Integer> res = new ArrayList<>();

        Action action = new Action();
        IAction test1 = () -> res.add(1);
        IAction test2 = () -> res.add(2);
        IAction test3 = () -> res.add(3);
        IAction test4 = () -> res.add(4);
        IAction test5 = () -> res.add(5);
        IAction removeTest3 = () -> action.remove(test3);

        action.add(test1);
        action.add(test2);
        action.add(test3);
        action.add(removeTest3);
        action.add(test4);
        action.add(test5);

        action.invoke();

        assert res.contains(1);
        assert res.contains(2);
        assert res.contains(3);
        assert res.contains(4);
        assert res.contains(5);
        System.out.println(res);

        res.clear();
        action.invoke();

        assert res.contains(1);
        assert res.contains(2);
        assert res.contains(4);
        assert res.contains(5);
        System.out.println(res);
    }

    @Test
    void removeBeforeTargetInvoke() {
        ArrayList<Integer> res = new ArrayList<>();

        Action action = new Action();
        IAction test1 = () -> res.add(1);
        IAction test2 = () -> res.add(2);
        IAction test3 = () -> res.add(3);
        IAction test4 = () -> res.add(4);
        IAction test5 = () -> res.add(5);
        IAction removeTest3 = () -> action.remove(test3);

        action.add(test1);
        action.add(test2);
        action.add(removeTest3);
        action.add(test3);
        action.add(test4);
        action.add(test5);

        action.invoke();

        assert res.contains(1);
        assert res.contains(2);
        assert res.contains(4);
        assert res.contains(5);
        System.out.println(res);
    }

    @Test
    void remove() {
        ArrayList<Integer> res = new ArrayList<>();

        Action action = new Action();
        IAction test1 = () -> res.add(1);
        IAction test2 = () -> res.add(2);
        IAction test3 = () -> res.add(3);
        IAction test4 = () -> res.add(4);
        IAction test5 = () -> res.add(5);
        IAction removeTest3 = () -> action.remove(test3);

        action.add(test1);
        action.add(test2);
        action.add(removeTest3);
        action.add(test3);
        action.add(test3);
        action.add(removeTest3);
        action.add(test4);
        action.add(test5);

        action.invoke();

        assert res.contains(1);
        assert res.contains(2);
        assert res.contains(3);
        assert res.contains(4);
        assert res.contains(5);

        System.out.println(res);
    }
}
