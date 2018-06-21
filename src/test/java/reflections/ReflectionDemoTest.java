package reflections;

import human.Human;
import human.Student;
import org.junit.Assert;
import org.junit.Test;
import reflextions.ReflectionDemo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        Assert.assertEquals(4, ReflectionDemo.getCountOfHuman(list));
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
        Assert.assertEquals(actual.size(), expected.size());
    }

    @Test
    public void testGetAllSuperclasses() {
        Student student = new Student("", "", "", 0, "");
        List<String> expected = new LinkedList<String>() {{
            add("Human");
            add("Object");
        }};
        Assert.assertEquals(expected, ReflectionDemo.getAllSuperclasses(student));
    }
}
