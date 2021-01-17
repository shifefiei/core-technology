package org.sff.builder;

public class BuilderTest {

    public static void main(String[] args) {

        Student student = new Student.StudentBuilder().age(1).name("builder").build();
        System.out.println(student);

    }


}
