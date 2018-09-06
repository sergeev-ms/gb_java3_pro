package ru.sms.lesson6;

import ru.sms.lesson6.annotations.AfterSuite;
import ru.sms.lesson6.annotations.BeforeSuite;
import ru.sms.lesson6.annotations.Test;

public class ForTesting {
    @BeforeSuite
    public void beforeClass(){
        System.out.println("before class");
    }
    @Test(Test.Priority.NORMAL)
    public void test1(){
        System.out.println("test1");
    }
    @Test(Test.Priority.LOWEST)
    public void test2(){
        System.out.println("test2");
    }
    @Test(Test.Priority.LOW)
    public void test3(){
        System.out.println("test3");
    }
    @Test(Test.Priority.HIGHEST)
    public void test4(){
        System.out.println("test4");
    }
    @Test(Test.Priority.HIGH)
    public void test5(){
        System.out.println("test5");
    }
    @Test(Test.Priority.NORMAL)
    public void test6(){
        System.out.println("test6");
    }
    @Test(Test.Priority.NORMAL)
    public void test7(){
        System.out.println("test7");
    }
    @AfterSuite
    public void afterClass(){
        System.out.println("after class");
    }

}
