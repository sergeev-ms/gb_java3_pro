package ru.sms.lesson6;

import ru.sms.lesson6.annotations.AfterSuite;
import ru.sms.lesson6.annotations.BeforeSuite;
import ru.sms.lesson6.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

class Tester {
    static void start(Class clazz) throws InvocationTargetException, IllegalAccessException {
        final List<Method> methods = Arrays.asList(clazz.getMethods());
        final List<Method> setBeforeSuite = methods.stream()
                .filter(method -> method.getAnnotation(BeforeSuite.class) != null)
                .collect(Collectors.toList());
        if (setBeforeSuite.size() > 1)
            throw new RuntimeException("BeforeSuite annotation must be in one exemplar");
        if (setBeforeSuite.size() > 0){
            final Method method = setBeforeSuite.get(0);
            method.invoke(new ForTesting());
        }

        final Set<Method> setTest = methods.stream()
                .filter(method -> method.getAnnotation(Test.class) != null)
                .collect(Collectors.toSet());
        Map<Method, Test.Priority> methodPriorityMap = new HashMap<>();
        while (setTest.iterator().hasNext()) {
            final Method method = setTest.iterator().next();
            final Test.Priority priority = method.getAnnotation(Test.class).value();
            methodPriorityMap.put(method, priority);
        }
        //TODO: Отсортировать мапу и выполнить все методы.

        final Set<Method> setAfterSuite = methods.stream()
                .filter(method -> method.getAnnotation(AfterSuite.class) != null)
                .collect(Collectors.toSet());
        if (setAfterSuite.size() > 1) {
            throw new RuntimeException("AfterSuite annotation must be in one exemplar");
        }
        else if (setAfterSuite.iterator().hasNext()) {
            setAfterSuite.iterator().next().invoke(new ForTesting());
        }


    }
}
