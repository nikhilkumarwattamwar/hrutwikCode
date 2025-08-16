public class CharCountOccurence {
    public static void main(String[] args) {


        String s = "this is a string";
        char ch = 'i';
        int count = 0;
        for (int i = 0; i < s.length();i++) {
           if(ch==s.charAt(i)){
               count+=1;
           }
        }
        System.out.println("the number of time "+ch+" occured is "+ count);
    }
}
