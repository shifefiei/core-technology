package org.sff;

import org.sff.prototype.Address;
import org.sff.prototype.Student;

public class Test {

    public static void main(String[] args) {

        try {
            testPrototype();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void testPrototype() throws Exception {

        Student s = new Student();
        s.setName("aaa");
        s.setAddress(new Address());
        Student student = s.deepCopy();
        System.out.println("原始对象：" + s);
        System.out.println("原始对象Address：" + s.getAddress());

        System.out.println("浅拷贝对象：" + student);
        System.out.println("浅拷贝对象Address：" + student.getAddress());


    }

}
