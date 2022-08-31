package com.senyk.laba2.entity;

import com.senyk.laba2.enums.Faculty;
import com.senyk.laba2.enums.Group;
import com.senyk.laba2.enums.Level;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
    private static int current_id;
    private int id;
    private String name;
    private String surname;
    private String father_name;
    private Date birthday;
    private String address;
    private String phone;
    private Faculty faculty;
    private Level level;
    private Group group;

    public Student(String name, String surname, String father_name, Date birthday,
                   String address, String phone, Level level, Group group) {

        Student.current_id++;
        this.id=current_id;
        this.name = name;
        this.surname = surname;
        this.father_name = father_name;
        this.birthday = birthday;
        this.address = address;
        this.phone = phone;
        this.level = level;
        this.group = group;
        this.faculty= group.getFaculty();
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setLevel(Level level) {
        this.level = level;
    }
    public void setGroup(Group group) {
        this.group = group;
        this.faculty = group.getFaculty();
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getFather_name() {
        return father_name;
    }
    public Date getBirthday() {
        return birthday;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }
    public Faculty getFaculty() {
        return faculty;
    }
    public Level getLevel() {
        return level;
    }
    public Group getGroup() {
        return group;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", father_name='" + father_name + '\'' +
                ", birthday=" +  formatter.format(birthday) + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", faculty=" + faculty +
                ", level=" + level +
                ", group=" + group +
                '}';
    }
}
