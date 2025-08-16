package practice;

public class StringMethod {
    public static void main(String[] args) {
        //public boolean isEmpty()
        String s = "";
        System.out.println(s.isEmpty());
        String s1="java";
        System.out.println(s1.isEmpty());

        //public int length()
        String s3= "Pratiksha";
        System.out.println(s3.length());

        //length variable is applicable for array to check lenght of array
        int[] x={1,3,4,5};
        System.out.println(x.length);

        //public String replace(char old, char new)
        String a= "yellow";
        System.out.println(a.replace('l','d'));

        //public String substring(int begin)  return from begin index to end of string
        String b= "abcdefg";
        System.out.println(b.substring(3));

        //public String substring(int begin, int end) return from begin index to end-1 index of string
        String c= "abcdefg";
        System.out.println(c.substring(3,6));
        //public String indexof(char ch);
        System.out.println(c.indexOf('d'));
        System.out.println(c.indexOf('z')); //if specified character is not available it will return -1
        String d="wwoohoo";
        System.out.println(d.indexOf('o'));//when soecified character appears multiple times the index of first occurence return

        //public String lastIndexOf();// return the index of last occurance of the given char
        System.out.println(d.lastIndexOf('o'));
        //public String tolowercase();
        String e= "Manu";
        System.out.println(e.toLowerCase());
        System.out.println(e.toUpperCase() );
        System.out.println(e.charAt(2));
//        System.out.println(e.charAt(5));   StringIndexOutOfBoundsException



    }
}
