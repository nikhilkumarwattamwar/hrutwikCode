package oops;

public class Encapsulation {
    public static void main(String[] args) {
  Person p = new Person();
  p.setEmpid(101);
        System.out.println(p.getEmpid());;
    }
}

class Person{
    private int empid;
    public void setEmpid(int eid){
        empid= eid;
    }

    public int getEmpid() {
        return empid;
    }
}

