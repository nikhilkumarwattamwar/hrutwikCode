package practice;

public class EqualsMethod {
    public static void main(String[] args) {

        String s1= new String("hello");
        String s2= new String("hello"); //s1 and s2 points out ro different object
        System.out.println(s1==s2); //false == operator is meant for reference comparison
        System.out.println(s1.equals(s2));

        StringBuffer sb1= new StringBuffer("hello");
        StringBuffer sb2= new StringBuffer("hello");
        System.out.println(sb1==sb2);  //false
        System.out.println(sb1.equals(sb2));

        System.out.println("----------X---------");
        String a= new String("pratiksha");
        String b=a.toLowerCase();
        String c=a.toUpperCase();
        System.out.println(a==b);//true
        System.out.println(a==c);//false

    }
}