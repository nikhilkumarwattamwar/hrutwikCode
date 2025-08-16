public class CustomImmutable {
    private int i;
    public CustomImmutable(int i){
        this.i=i;
    }
    public CustomImmutable modify(int i){
        if(i==this.i){
            return this;
        }else {
            return new CustomImmutable(i);
        }
    }

    public static void main(String[] args) {
        CustomImmutable t1= new CustomImmutable(10);
        CustomImmutable t2= t1.modify(100);
        CustomImmutable t3=t1.modify(10);
        System.out.println(t1==t2);
        System.out.println(t1==t3);

    }
}
