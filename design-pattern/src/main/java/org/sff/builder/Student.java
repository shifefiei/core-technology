package org.sff.builder;


/**
 * 什么时候使用比较合理：读取的配置文件参数，构造参数会随时变化的情况下比较合适
 */
public class Student {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static class StudentBuilder {
        private Student student = new Student();

        public StudentBuilder name(String name) {
            student.setName(name);
            return this;
        }

        public StudentBuilder age(int age) {
            student.setAge(age);
            return this;
        }

        public Student build() {
            return student;
        }
    }
}
