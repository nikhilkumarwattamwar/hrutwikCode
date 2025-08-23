package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Transaction tx = session.beginTransaction();
//            Student s = new Student();
//            s.setName("Hrutwik Kale");
//            s.setEmail("hrutwik@example.com");
//            StudentDetail d = new StudentDetail();
//            d.setPhone("9876543210");
//            d.setAddress("Pune, India");
//            s.setStudentDetail(d);
//
//            session.persist(s);
//
//            tx.commit();
//            System.out.println(" Saved Student and StudentDetail.");
//        }

//        Configuration cfg = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(Student.class)
//                .addAnnotatedClass(StudentDetail.class);
//
//        // 2. Build SessionFactory
//        SessionFactory sessionFactory = cfg.buildSessionFactory();
//
//        // 3. Open session
//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
//
//        // 4. Create Student object
//        Student student = new Student();
//        student.setName("Alice Johnson");
//        student.setEmail("alice.johnson@example.com");
//
//        // 5. Create StudentDetail object
//        StudentDetail detail = new StudentDetail();
//        detail.setAddress("123 Park Avenue, New York");
//        detail.setPhone("9876543210");
//
//        // Set one-to-one relationship
//        student.setStudentDetail(detail);
//
//        // 6. Save student (cascade saves details too)
//        session.persist(student);
//
//        tx.commit();
//
//        try {
//            Transaction t = session.beginTransaction();
//
//            // ✅ Option 1: Fetch by ID (example: ID = 1)
//            Student stu = session.get(Student.class, 3L);
//
//            // ✅ Option 2: Fetch by name "Alice"
//            // Query<Student> query = session.createQuery("from Student where name = :name", Student.class);
//            // query.setParameter("name", "Alice");
//            // Student student = query.uniqueResult();
//
//            if (stu != null) {
//                System.out.println("Before Update: " + stu.getName());
//
//                // Update student name
//                stu.setName("Om");
//
//                // Hibernate automatically tracks changes (dirty checking)
//                session.merge(stu); // or session.update(student)
//
//                System.out.println("✅ Student name updated to: " + stu.getName());
//            } else {
//                System.out.println("⚠️ No student found!");
//            }
//
//            t.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//            sessionFactory.close();
//        }
//        session.close();
//        sessionFactory.close();


        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(StudentDetail.class)
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        StudentService service = new StudentService(sessionFactory);
        DepratmentService depratmentService=new DepratmentService(sessionFactory);
        Scanner sc = new Scanner(System.in);


        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Insert Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. View All Students");
            System.out.println("5. Insert DepratMent");
            System.out.println("6. Update DepratMent");
            System.out.println("7. Delete7 DepratMent");
            System.out.println("8. View All DepratMent");
            System.out.println("8. Exit");
            System.out.print(" Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Address: ");
                    String address = sc.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();

                    service.insertStudent(name, email, address, phone);
                    break;

                case 2:
                    System.out.print("Enter Student ID to Update: ");
                    long updateId = sc.nextLong();
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();

                    service.updateStudent(updateId, newName);
                    break;

                case 3:
                    System.out.print("Enter Student ID to Delete: ");
                    long deleteId = sc.nextLong();
                    sc.nextLine();

                    service.deleteStudent(deleteId);
                    break;

                case 4:
                    service.viewAllStudents();
                    break;

                case 5:
                    System.out.println(" Department Name : ");
                    String deptNAme=sc.nextLine();
                    System.out.println("How many Employee are there : " );
                    int num=sc.nextInt();
                    List<String> empNames=new ArrayList<>();
                    for (int i=1;i<=num;i++){
                        sc.nextLine();
                        System.out.println("Enter EMployee Name "+i+" : ");

//                        System.out.print("Enter Employee " + i + " Name: ");
                        empNames.add(sc.nextLine());
                    }
                    depratmentService.insertDepratment(deptNAme,empNames);
                    break;

                case 6:
                    System.out.println("Enter Depratment Id TO update");
                    int id=sc.nextInt();
                    sc.nextLine();
                    System.out.println("ENter a Depatment Name:");
                    String uDeptName=sc.nextLine();
                    depratmentService.updateDepartment(id,uDeptName);
                    break;


                case 7:
                    System.out.println("Enter Id to Delete of depratment : ");
                    Integer dId=sc.nextInt();
                    sc.nextLine();
                    depratmentService.deleteDeparment(dId);
                    break;
                case 8:
                    depratmentService.viewAllDepartments();
                    break;

                case 9:
                    System.out.println(" Exiting... Goodbye!");
                    sessionFactory.close();
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println(" Invalid choice, please try again!");
            }
        }


    }
}