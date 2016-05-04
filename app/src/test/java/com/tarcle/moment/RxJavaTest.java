package com.tarcle.moment;

import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;


@RunWith(TarcleRobolectricTestRunner.class)
public class RxJavaTest {
    List<Student> students = Arrays.asList(new Student("Li"), new Student("Zhang"));
    private String tag = "RXJAVA";

    Subscriber<Course> subscriber = new Subscriber<Course>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onNext(Course course) {
            System.out.println(course.name);
        }
    };

    @Test
    public void testRxJava() throws Exception {
        Action1<Course> action = new Action1<Course>() {
            @Override
            public void call(Course course) {
                System.out.println(course.name);
            }
        };
        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCourses());
                    }
                })
                .subscribe(action);

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