package practice;

public class StringContructor {
    public static void main(String[] args) {


        String s = new String();//created an empty string object
//        String s1 = new String(String literal); string contructor for string liternal
//        String s2 = new String(StringBuffer sb); string contructor for given string buffer sb
//        String s2 = new String(StringBuilder sb); string contructor for given string Builder sb
//        String s3= new String(char[] ch);string contructor for given char array
//        String s = new String (byte[] b);  string contructor for given byte array
        char[] ch = {'j', 'a', 'v', 'a'};
        String chs = new String(ch);
        System.out.println(ch);

        byte[] b={97,98,99,100};
        String bs= new String(b);
        System.out.println(bs);
    }

}
