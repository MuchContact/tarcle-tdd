package com.tarcle.moment;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;


@RunWith(TarcleRobolectricTestRunner.class)
public class RxJavaTest {
    List<Student> students = Arrays.asList(new Student("Li"), new Student("Zhang"));
    private String tag = "RXJAVA";

    Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onNext(String name) {
            System.out.println(name);
            Log.d(tag, name);
        }
    };

    @Test
    public void testRxJava() throws Exception {
        Observable.from(students)
                .map(new Func1<Student, String>() {
                    @Override
                    public String call(Student student) {
                        return student.getName();
                    }
                })
                .subscribe(subscriber);

    }

    private class Student {
        private final String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public List<Course> getCourses(){
            return Arrays.asList(
                    new Course(String.format("%s-math", name)),
                    new Course(String.format("%s-Language", name))
            );
        }
    }

    private class Course {
        private final String name;

        public Course(String name) {
            this.name = name;
        }
    }
}