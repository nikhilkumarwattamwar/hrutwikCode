package hrutwikCode.ArrayExamples;

public class StringArray {
    public static void main(String[] args) {
        String str[]={"abc","zyzdf","pqr"};
        String rev="";
            for (int i=0;i<str.length;i++){
                for (int j=0;j<str[i].length();j++){
                    if (j==0){
                        rev+=toUpper(str[i].charAt(j));
                    }else {
                        rev+=str[i].charAt(j);
                    }
                }
            }
        System.out.println(specificName(str,"pqr"));
        System.out.println(rev);
        String[] sort=sortArray(str);
        for(int i=0;i<sort.length;i++){
            System.out.println(sort[i]);
        }
        System.out.println(countMoreThanFive(str));
    }
    public static String specificName(String[] str,String c){
        String sName="";
        String find=c;
        for (int i=0;i<str.length;i++){
            String name="";
            for (int j=0;j<str[i].length();j++){
                name=name+str[i].charAt(j);
            }
            if (name.equals(find))return name;
        }
        return "NOt found" ;
    }
    static String[] sortArray(String[] str){
        for (int i=0;i<str.length-1;i++){
            for (int j=1;j<str[i].length();j++){
                if(str[i].compareTo(str[j])>0) {
                    String temp = str[i];
                    str[i] = str[j];
                    str[j] = temp;
                }
            }
        }
        return str;
    }
    static int countMoreThanFive(String[] str){
        int count=0;
        for (int i=0;i<str.length;i++){
            if (str[i].length()>=2){
                count++;
            }
        }
        return count;
    }
    public static char toUpper(char c){
        return (char)(c-('a'-'A'));
    }
}
