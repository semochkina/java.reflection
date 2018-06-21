package reflections;

import human.Human;
import human.Student;
import org.junit.Assert;
import org.junit.Test;
import reflextions.ReflectionDemo;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class ReflectionDemoTest{

    @Test
    public void testGetCountOfHumanAndDerives() {
        List<Object> list = new ArrayList<Object>() {{
            add(new Object());
            add(new Human("1", "1", "1", 10));
            add(new Student("2", "2", "2", 10, "2"));
            add(new Student("2", "2", "2", 10, "2"));
            add(new Human("1", "1", "1", 10));
        }};
        assertEquals(4, ReflectionDemo.getCountOfHuman(list));
    }

    @Test
    public void testGetNamesOfPublicMethods() {
        Object o = new Object();
        List<String> expected = new LinkedList<String>() {{
            add("getClass");
            add("equals");
            add("hashCode");
            add("toString");
            add("notify");
            add("notifyAll");
            add("wait");
            add("wait");
            add("wait");
        }};
        List<String> actual = ReflectionDemo.getNamesOfPublicMethods(o);
        Assert.assertTrue(actual.containsAll(expected));
        assertEquals(actual.size(), expected.size());
    }

    @Test
    public void testGetAllSuperclasses() {
        Student student = new Student("", "", "", 0, "");
        List<String> expected = new LinkedList<String>() {{
            add("Human");
            add("Object");
        }};
        assertEquals(expected, ReflectionDemo.getAllSuperclasses(student));
    }

    @Test
    public void testCalcExecutable() {
        List<Object> list = new ArrayList<Object>() {{
            add(new Object());
            add(new Human("1", "1", "1", 10));
            add(new Student("2", "2", "2", 10, "faculty1"));
            add(new Student("2", "2", "2", 10, "faculty2"));
        }};
        Assert.assertEquals(2, ReflectionDemo.calkExecutable(list));
    }

    @Test
    public void testListGetOrSet() {
        List<String> list = ReflectionDemo.listGetOrSet(new Student("2", "2", "2", 10, "fio1"));
        System.out.println(list);
        Assert.assertEquals(10, list.size());
    }
}
