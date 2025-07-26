package hrutwikCode.ArrayExamples;


public class SingleDArray {
    public static void main(String[] args) {
        int[] arr=new int[5];
        int count=6;
        for (int i=0;i<arr.length;i++){

            arr[i]=count++;
        }
        int sum=0;
        for (int i=0;i<arr.length;i++){
            sum+=arr[i];
        }
        System.out.println("Sum of all Elements "+sum);
        System.out.println("============================================================================================");
        System.out.println("============================================================================================");

        int max=0;
        int min=arr[3];
        for (int i=0;i<arr.length;i++){
            if(max<arr[i]){
                max=arr[i];
            }
            if (min>arr[i]){
                min=arr[i];
            }
        }
        System.out.println("Maximum Element :"+max+" Minimum Element "+min);
        System.out.println("============================================================================================");
        System.out.println("============================================================================================");

        int[] rev=new int[arr.length];
        int index=0;
        for (int i= arr.length-1;i>=0;i--){
            rev[index++]=arr[i];
        }
        System.out.println("Reverse Index");
        for (int i=0;i<rev.length;i++){
            System.out.print(rev[i]+" ");
        }
        System.out.println();
        System.out.println("============================================================================================");

        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
}
