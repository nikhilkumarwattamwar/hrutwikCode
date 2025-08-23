package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DepratmentService {
    private final SessionFactory sessionFactory;
    public DepratmentService(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }
    void insertDepratment(String deptName, List<String> employeeNames){

        try(Session session=sessionFactory.openSession()) {
            Transaction tx=session.beginTransaction();
            Department department=new Department(deptName);
            for (String name:employeeNames){
                Employee e1=new Employee(name);
                department.addEmployee(e1);
            }
            session.save(department);
            tx.commit();
            System.out.println(" Department inserted successfully!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void updateDepartment(int id,String deptName){
        try (Session session=sessionFactory.openSession()){
            Transaction tx=session.beginTransaction();
           Department department= session.get(Department.class,id);
           if (department != null){
               department.setName(deptName);
               session.merge(department);
               tx.commit();
               System.out.println(" Depratment updated successfully!");
           }else {
               System.out.println("Department is Not found");
               tx.rollback();
           }

        }
    }
    void deleteDeparment(Integer id){
        try(Session session=sessionFactory.openSession()) {
            Transaction tx=session.beginTransaction();
            Department department=session.get(Department.class,id);
            if (department!=null){
                session.remove(department);
                tx.commit();
                System.out.println("Deleted succeffulyy:");
            }else {
                System.out.println("Department is not present:");
                tx.rollback();
            }

        }
    }
    public void viewAllDepartments() {
        try (Session session = sessionFactory.openSession()) {
            List<Department> departments = session.createQuery("from Department", Department.class).list();
            if (departments.isEmpty()) {
                System.out.println(" No departments found!");
            } else {
                departments.forEach(dept -> {
                    System.out.println("Dept ID: " + dept.getId() + ", Name: " + dept.getName());
                    dept.getEmployees().forEach(emp ->
                            System.out.println("   Emp ID: " + emp.getId() + ", Name: " + emp.getFullName())
                    );
                });
            }
        }
    }
}
