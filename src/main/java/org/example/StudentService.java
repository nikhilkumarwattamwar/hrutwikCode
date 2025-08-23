package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import java.util.List;

public class StudentService {
    private final SessionFactory sessionFactory;

    public StudentService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insertStudent(String name, String email, String address, String phone) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            Student student = new Student();
            student.setName(name);
            student.setEmail(email);

            StudentDetail detail = new StudentDetail();
            detail.setAddress(address);
            detail.setPhone(phone);

            student.setStudentDetail(detail);

            session.save(student);
            tx.commit();
            System.out.println(" Student inserted successfully!");
        }
    }

    public void updateStudent(long id, String newName) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Student stu = session.get(Student.class, id);

            if (stu != null) {
                stu.setName(newName);
                session.merge(stu);
                tx.commit();
                System.out.println(" Student updated successfully!");
            } else {
                System.out.println(" No student found with ID " + id);
                tx.rollback();
            }
        }
    }

    public void deleteStudent(long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Student stu = session.get(Student.class, id);

            if (stu != null) {
                session.remove(stu);
                tx.commit();
                System.out.println(" Student deleted successfully!");
            } else {
                System.out.println(" No student found with ID " + id);
                tx.rollback();
            }
        }
    }

    public void viewAllStudents() {
        try (Session session = sessionFactory.openSession()) {
            List<Student> students = session.createQuery("from Student", Student.class).list();
            if (students.isEmpty()) {
                System.out.println(" No students found!");
            } else {
                students.forEach(stu -> System.out.println(
                        "ID: " + stu.getId() +
                                ", Name: " + stu.getName() +
                                " , Email: " + stu.getEmail() +
                                " , Phone: " + (stu.getStudentDetail() != null ? stu.getStudentDetail().getPhone() : "N/A") +
                                " , Address: " + (stu.getStudentDetail() != null ? stu.getStudentDetail().getAddress() : "N/A")
                ));
            }
        }
    }
}
