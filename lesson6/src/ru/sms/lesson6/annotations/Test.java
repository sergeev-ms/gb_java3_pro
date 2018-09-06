package ru.sms.lesson6.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
Priority value();

enum Priority {
        LOWEST(1),
        LOW(2),
        THREE(3),
        FOUR(4),
        NORMAL(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        HIGH(9),
        HIGHEST(10);
        private int priorityValue;


        Priority(int priorityValue) {
            this.priorityValue = priorityValue;
        }
    }
}
