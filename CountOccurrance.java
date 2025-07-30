package GitFolder.hrutwikCode;

public class CountOccurrance {
    public static void main(String[] args) {

        String name="nikhikjijkklkumar";
        int occurance[]=new int[26];


        for (int i=0;i<name.length();i++){
            occurance[name.charAt(i)-'a']++;
        }
        int max=0;
        for (int i=0;i<occurance.length;i++){
            if (max<occurance[i]){
                max=occurance[i];
            }
        }
        for (int i=0;i<occurance.length;i++){

            if(occurance[i]==max){
                System.out.println((char)(i + 'a') + " = " + occurance[i]);
             }
        }
    }
}
