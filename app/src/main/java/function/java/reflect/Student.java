package function.java.reflect;

public class Student {


    public String name;
    int age;
    boolean n;

    //---------------构造方法-------------------  
    //（默认的构造方法）  
    Student(String str) {
        System.out.println("(默认)的构造方法 s = " + str);
        this.name = str;
    }

    //无参构造方法  
    public Student() {
        System.out.println("调用了公有、无参构造方法执行了。。。");
        name = "";
        age = 0;
    }

    //有一个参数的构造方法  
    public Student(char name) {
        System.out.println("姓名：" + name);
        this.name = name + "";
    }

    //有多个参数的构造方法  
    public Student(String name, int age) {
        System.out.println("姓名：" + name + "年龄：" + age);//这的执行效率有问题，以后解决。
        this.name = name;
        this.age = age;
    }

    //受保护的构造方法  
    protected Student(boolean n) {
        System.out.println("受保护的构造方法 n = " + n);
        this.n = n;
    }

    //私有构造方法  
    private Student(int age) {
        System.out.println("私有的构造方法   年龄：" + age);
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", n=" + n +
                '}';
    }


    //**************成员方法***************//
    public String show1(String s) {
        System.out.println("调用了：公有的，String参数的show1(): s = " + s);
        return s;
    }

    protected void show2() {
        System.out.println("调用了：受保护的，无参的show2()");
    }

    void show3() {
        System.out.println("调用了：默认的，无参的show3()");
    }

    private String show4(int age) {
        System.out.println("调用了，私有的，并且有返回值的，int参数的show4(): age = " + age);
        return "abcd:" + age;
    }

    private String show5() {
        return "2222";
    }


    public static String main(String[] args) {
        System.out.println("main方法执行了。。。");
        StringBuilder builder = new StringBuilder();
        for (String arg : args) {
            builder.append(arg + "_");
        }
        return builder.toString();

    }


}