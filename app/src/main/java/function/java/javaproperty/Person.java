package function.java.javaproperty;

import android.util.Log;

/**
 * Created by zhangguang on 18/7/4.
 */

public class Person {


    int age = 20;
    String name = "zhangg";


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void print() {
        Log.e("mmmm", "name:" + name + " ; age=" + age);
    }


}
