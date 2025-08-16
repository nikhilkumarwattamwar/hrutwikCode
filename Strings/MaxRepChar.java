public class MaxRepChar {
    public static void main(String[] args) {
        String str = "abbcccdddd";
        int[] arr = new int[256];
        for (int i = 0; i < str.length();i++) {
            arr[str.charAt(i)]= arr[str.charAt(i)]+1;
        }

        int max=-1;
        char ch=' ';
        for(int i=0;i<str.length();i++){
            if(max<arr[str.charAt(i)]){
                max=arr[str.charAt(i)];
                ch= str.charAt(i);
            }
        }

        System.out.println("max repeated character is :"+ch);
    }
}
