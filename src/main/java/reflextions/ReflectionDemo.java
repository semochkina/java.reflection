package reflextions;
import human.Human;
import interfaces.Executable;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
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
    public static List<String> getAllSuperclasses(Object obj) {
        List<String> result = new ArrayList<>();
        Class<?> cl = obj.getClass();
        Class s = cl.getSuperclass();
        while (s != null) {
            result.add(s.getSimpleName());
            s = s.getSuperclass();
        }
        return result;
    }

    //Напишите метод, который для списка объектов находит его элементы, реализующие этот интерфейс, и выполняет в таких объектах метод execute().
    //Метод возвращает количество найденных элементов.
    public static long calkExecutable(List<Object> objects) {
        return objects == null ? 0 : objects.stream()
                .filter(r -> r instanceof Executable)
                .peek(r -> ((Executable) r).execute())
                .count();
    }

    // Для объекта получить список его геттеров и сеттеров (в виде списка строк с заголовками методов).
    public static List<String> listGetOrSet(Object obj) {
        Method[] methods = obj.getClass().getMethods();
        return Arrays.stream(methods)
                .filter(ReflectionDemo::isGetOrSet)
                .map(Method::getName)
                .collect(Collectors.toList());
    }

    public static boolean isGetOrSet(Method method) {
        // берем только не статические методы
        if (!Modifier.isStatic(method.getModifiers())) {
            Class<?> returnType = method.getReturnType();
            int parameterCount = method.getParameterCount();
            String name = method.getName();
            if (name.startsWith("set") && parameterCount == 1 && "void".equals(returnType.getName())) {
                return true;
            }
            if (parameterCount == 0 && !"void".equals(returnType.getName())) {
                if (name.startsWith("get") && !name.equals("getClass")) {
                    return true;
                }
                if ((name.startsWith("has") || name.startsWith("is"))
                        && "boolean".equalsIgnoreCase(returnType.getName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
