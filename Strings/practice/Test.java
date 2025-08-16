package practice;

public class Test {
    public static void main(String[] args) {
        String s1= new String("hello world");
        String s2= new String("hello world");
        System.out.println(s1==s2);//false : variable point out to different object in heap
        System.out.println(s1.equals(s2));
        System.out.println("---------");
        String s3= "hello world"; //in scp
        System.out.println(s1==s3); //false

        String s4="hello world";
        System.out.println(s4==s3);//true==s4 pointing to same object as s3 in scp

        String s5= "hello "+"world";//here both are constant so it will get execute at compile time by directly pointing to existing object in scp i.es3
        System.out.println(s4==s5);// true

        String s6="hello";
        String s7=s6+"world";//here variable+constant will get execute at runtime. first world object will be created in scp and hello world will be created in heap
        System.out.println(s7==s4); // both var are pointing to different objects even though content is same

        final String s8="hello "; //here because of final s8 becomes constat
        String s9=s8+"world";//here both are constant and will run at compile time creating object inside scp will point at exising object
        System.out.println(s4==s9);
    }
}
