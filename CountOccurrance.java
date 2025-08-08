package GitFolder.hrutwikCode;

public class CountOccurrance {
    public static void main(String[] args) {

        System.out.println( recursive("ABC"));


        String name="nikhABGFikjijkklkumar";


//        int occurance[]=new int[26];
//
//
//        for (int i=0;i<name.length();i++){
//            occurance[name.charAt(i)-'a']++;
//        }
//        int max=0;
//        for (int i=0;i<occurance.length;i++){
//            if (max<occurance[i]){
//                max=occurance[i];
//            }
//        }
//        for (int i=0;i<occurance.length;i++){
//
//            if(occurance[i]==max){
//                System.out.println((char)(i + 'a') + " = " + occurance[i]);
//             }
//        }
        int[] count = new int[256];  // for extended ASCII

        for (char ch : name.toCharArray()) {
            count[ch]++;
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                System.out.println((char)i + ": " + count[i]);
            }
    }
    }

   static String recursive(String a){
       if (a.length()<=1 || a==null){
                return a;
            }
    return recursive(a.substring(1))+a.charAt(0);
   }
}
