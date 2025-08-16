public class Intern {
    public static void main(String[] args) {
        //interning method ensures that all strings having same contents shares the same memory
        String s= new String("hello");
        String s1= s; //s1 will point at object in heap area
        String s2=s.intern();// by using intern method s2 will point object in scp area
        System.out.println(s1==s);
        System.out.println(s2==s);
        System.out.println(s1.equals(s));
        System.out.println(s2.equals(s));
        String s3="hello";
        System.out.println(s3==s2);
        String s4=new String("world");
        String s5=new String("world");
        System.out.println(s4==s5);//false
        s4=s4.intern();
        s5=s5.intern();
        System.out.println(s4==s5);//True



    }

}
