package org.sff.prototype;

import java.io.*;

/**
 * 原型模式
 */
public class Student implements Serializable, Cloneable {
    private String name;
    private Address address;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Student shallowCopy() throws CloneNotSupportedException {
        Student clone = (Student) super.clone();
        return clone;
    }

    public Student deepCopy() throws Exception {
        /**
         * 将对象序列化写到二进制流
         */
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        /**
         *从二进制流中读出新对象
         */
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        Student student = (Student) ois.readObject();
        return student;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
