package practice;

public class StrBuffer {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        System.out.println(sb.capacity()); //default capacity for string buffer is 16
        sb.append("abcdefghijklmnop");//16char
        System.out.println(sb.capacity());
        sb.append("q");//by adding 17th char a new bigger string buffer obj will be created with the
        // capacity of (current capacity+1)*2  (16+1)*2=34...likewise (34+1)*2=70...142...286
        System.out.println(sb.capacity());

//     StringBuffer sb1= new StringBuffer(int initialcapacity); for large capacity we can directly initially set the capacity to overcome the creation of SB obj again and again
        StringBuffer sb1= new StringBuffer(1000);
        System.out.println("---X---");

//      StringBuffer sb2= new StringBuffer(String s); //capacity=string.length()+16
      StringBuffer sb2= new StringBuffer("Pratiksha");
        System.out.println(sb2.capacity());//9+16

    }

    public static class StrBufferMethod {
        public static void main(String[] args) {
            StringBuffer sb= new StringBuffer("Pratiksha");
            System.out.println(sb.capacity());
            System.out.println(sb.substring(0,6));
            sb.setCharAt(0,'R');  //public void setCharAt(int index, char new char)
            System.out.println(sb);

    //        public SB append(String s)  //public SB append(byte b) //public SB append(int i)
    //        like this there are multiple methods of append are available with different argument so append is overloaded method
            StringBuffer sb1= new StringBuffer();
            sb1.append("value of PI is :");
            sb1.append(3.14);
            System.out.println(sb1);
            sb.append(" Bhukan");
            System.out.println(sb);

    //        public SB insert(int index, String s); insert is also overloaded method containing different argument int long byte boolean etc
            System.out.println(sb.insert(3,"dfg"));
            System.out.println(sb.insert(3,324));
            System.out.println(sb.insert(3,true));

    //        public SB delete(int begin, int end); deletes char from begin index to end-1 index
            StringBuffer sb3= new StringBuffer("abcdefgh");
            System.out.println(sb3.delete(1,5));
            System.out.println(sb3.deleteCharAt(0)); //public SB deleteChatAt(int index) deletes chat at particular index

            StringBuffer sb4= new StringBuffer("Refrigerator");
            System.out.println(sb4.reverse());//public SB reverse(); will reverse order of char sequence of string

    //        public void setLength(int length); sets length for the object to be created
            StringBuffer sb5= new StringBuffer("PratikshaBhukan");
    //        System.out.println(sb5.setLength(7));
            sb5.setLength(9);
            System.out.println(sb5);

    //        public SB ensureCapacity();  used to increase the capacity based on requirement after the creation of obj
            StringBuffer sb6= new StringBuffer("abcd");
            sb6.ensureCapacity(1000);
            System.out.println(sb6.capacity());
            sb6.trimToSize(); //all extra allocated free memory will ger deallocate and memory will allocated only for 4 char
            System.out.println(sb6.capacity());


        }
    }
}
