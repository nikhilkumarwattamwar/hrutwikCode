package ConstructorChaining;

public class Book {
    String name;
    int pages;
    double price;

    Book(){
        this("HK");
    }
    Book(String name){
        this(name,45);
    }
    Book(String name,int pages){
        this(name,pages,0.0);
    }
    Book(String name,int pages,double price){
        this.name=name;
        this.pages=pages;
        this.price=price;
    }
    void show(){
        System.out.println("Name:"+name+" Pages:"+pages+" Price:"+price );
    }

    public static void main(String[] args) {
        Book b1=new Book();
        b1.show();
        Book b3=new Book("Wings Of Fire");
        b3.show();
        Book b2=new Book("JK",80);
        b2.show();
        Book b4= new Book("Yk",700,1552);
        b4.show();
        b1.show();
    }
}
