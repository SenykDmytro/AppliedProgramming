package com.senyk.laba2.realization;

import com.senyk.laba2.entity.Student;
import com.senyk.laba2.enums.Group;
import com.senyk.laba2.enums.Level;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Students {
    private List<Student> students;

    public Students(List<Student> students) {
        this.students = students;
    }
    public Students(int N) {
        generateNStudents(N);
    }
    public Students() {
    }
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void generateNStudents(int N) {
        List<Student> list =new ArrayList<>();
        String name="name_";
        String surname="surname_";
        String father_name="father_name_";
        String address="address_";
        String phone="phone_";
        int x=0;
        for (int i = 1; i <= N; i++) {
            Date birthday=Students.randomHireday();
            Student s =new Student(name+i,surname+i,father_name+i,birthday,
                    address+i,phone+i,Level.generateLevel(),Group.generateGroup());
            list.add(s);
        }
        this.students = list;
    }
    public static Date randomHireday() {
        int startYear=1995;
        int endYear=2008;
        long start = Timestamp.valueOf(startYear+1+"-1-1 0:0:0").getTime();
        long end = Timestamp.valueOf(endYear+"-1-1 0:0:0").getTime();
        long ms=(long) ((end-start)*Math.random()+start);
        Date hireday=new Date(ms);
        return hireday;
    }

    @Override
    public String toString() {
        String s="";
        for (Student st:students) {
            s+=st+"\n";
        }
        return s;
    }
}
