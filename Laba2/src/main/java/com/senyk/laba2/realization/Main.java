package com.senyk.laba2.realization;

import com.senyk.laba2.entity.Student;
import com.senyk.laba2.enums.Faculty;
import com.senyk.laba2.enums.Group;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Dmytro Senyk
 * @version 0.1
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Students students=new Students(10);
        Group group=Group.generateGroup();
        Faculty faculty=group.getFaculty();
        Date date=Students.randomHireday();

        System.out.println(students);
        System.out.println("****************************************************************************************");
        printList(date,students);
        System.out.println("****************************************************************************************");
        printList(group,students);
        System.out.println("****************************************************************************************");
        printList(faculty,students);
    }

    /**
     * друкуєм результативний список студентів
     * @param t тип даних
     * @param students список студентів
     * @param <T>
     */
    public static <T> void printList(T t,Students students){
        List<Student> list=null;
        if (Date.class.equals(t.getClass())) {
            list = getStudentAfterYear(students.getStudents(), (Date) t);
        } else if (Faculty.class.equals(t.getClass())) {
            list = getStudentFromFaculty(students.getStudents(), (Faculty) t);
        } else if (Group.class.equals(t.getClass())) {
            list = getStudentFromGroup(students.getStudents(), (Group) t);
        }
        list.stream().forEach(System.out::println);
    }

    /**
     * визначає студентів які народились після заданого року
     * @param students список студентів
     * @param date рік
     * @return список студентів
     */
    public static List<Student> getStudentAfterYear(List<Student> students, Date date){
        return students.stream().filter(f->f.getBirthday().getYear()>=date.getYear()).collect(Collectors.toList());
    }

    /**
     * визначає студентів з заданого факультету
     * @param students список студентів
     * @param faculty факультет
     * @return список студентів
     */
    public static List<Student> getStudentFromFaculty(List<Student> students, Faculty faculty){
        return students.stream().filter(f->f.getFaculty()==faculty).collect(Collectors.toList());
    }

    /**
     * визначає студентів з заданої групи
     * @param students список студентів
     * @param group група
     * @return список студентів
     */
    public static List<Student> getStudentFromGroup(List<Student> students, Group group){
        return students.stream().filter(f->f.getGroup()==group).collect(Collectors.toList());
    }

}
