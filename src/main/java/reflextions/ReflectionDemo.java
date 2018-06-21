package reflextions;

import human.Human;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReflectionDemo {

    //Найдите количество элементов списка, которые являются объектами типа Human
    public static long getCountOfHuman(List<Object> list) {
        return list.stream().filter(Human.class::isInstance).count();
    }

    //Для объекта получить список имен его открытых методов
    public static List<String> getNamesOfPublicMethods(Object o) {
        return Arrays.stream(o.getClass().getMethods()).map(Method::getName).collect(Collectors.toList());
    }

    //Для объекта получить список (в виде списка строк) имен всех его суперклассов до класса Object включительно
    public static List<String> getAllSuperclasses(Object obj){
        List<String> result = new ArrayList<>();
        Class<?> cl = obj.getClass();
        Class s = cl.getSuperclass();
        while (s!=null){
            result.add(s.getSimpleName());
            s = s.getSuperclass();
        }
        return result;
    }
}
