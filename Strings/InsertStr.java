public class InsertStr {
    public static void main(String[] args) {
        String s= "hands";
        int index=3;
        String strtoinsert="book";
        String newStr="";
        for(int i=0;i<s.length();i++){
            newStr+=s.charAt(i);

            if(index==i){
                newStr+=strtoinsert;
            }
        }
        System.out.println(newStr);
    }
}
