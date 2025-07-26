package hrutwikCode.ArrayExamples;

public class TwoDArray {
    public static void main(String[] args) {
        int[][] arr=new int[3][3];
        int count=5;
        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr.length;j++){
                arr[i][j]=count++;
            }
        }
        for (int i=0;i<arr.length;i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        int sum=0;
        for (int i=0;i<arr.length;i++) {
            for (int j = 0; j < arr.length; j++) {
                sum+=arr[i][j];
                if (i==j){
                    System.out.println(arr[i][j]);
                    System.out.println(arr[i][arr.length-1-i]+"  h");
                }

            }
        }
        System.out.println(sum);
        int[][] transpose=transpose(arr);
        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr.length;j++){
                System.out.print(transpose[i][j]+" ");
            }System.out.println();
        }

    }

    
    static int[][] transpose(int[][] arr){
        int rows=arr.length;
        int col=arr[0].length;
        int[][] transpose=new int[col][rows];

        for (int i=0;i<rows;i++){
            for (int j=0;j<col;j++){
                transpose[j][i]=arr[i][j];
            }
        }
        return transpose;
    }
}
