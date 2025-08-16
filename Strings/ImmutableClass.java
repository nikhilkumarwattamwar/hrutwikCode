public class ImmutableClass {
    private String name;
    private int id;

    public ImmutableClass(String name, int id){
        this.name= name;
        this.id=id;
    }

    public ImmutableClass change(String name, int id){
        if(this.name.equals(name) && this.id ==id){
            return this;
        }else{
            return new ImmutableClass( name,id);
        }
    }

    public static void main(String[] args) {
        ImmutableClass obj1=new ImmutableClass("ram",56);
        ImmutableClass obj2=obj1.change("ram",56);
        ImmutableClass obj3=obj1.change("sam",75);
        System.out.println(obj2==obj1);
        System.out.println(obj3==obj1);

    }

}
