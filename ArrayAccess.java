package CustomException;

public class ArrayAccess {
    static void getAccess(int[] arr,int index) throws ArrauyOutOfBound{
        if(index<0) throw new InvalidIndexException();
        if (arr.length<index) throw new ArrauyOutOfBound("Array out Bound Exception");
        System.out.println(arr[index]);
    }

    public static void main(String[] args) {
        int arr[]={14,75,752,75};
        try{
//            getAccess(arr,-1);
            getAccess(arr,6);
        }catch (InvalidIndexException e){
            System.out.println(e.getMessage());
        }catch (ArrauyOutOfBound e){
            System.out.println(e.getMessage());
        }
        try{
            getAccess(arr,-1);

        }catch (InvalidIndexException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            getAccess(arr,3);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
class InvalidIndexException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Invalid Index";
    }
}
class ArrauyOutOfBound extends Exception{
    public ArrauyOutOfBound(String s){
        super(s);
    }
}