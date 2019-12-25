package function.java.javaproperty;

import android.util.Log;

/**
 * Created by zhangguang on 18/7/4.
 */

public class CellegeStudent extends Person {


    int age = 22;
    String sex;


    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public void print() {
        Log.e("mmmm", "name:" + name + " ; age=" + age + " sex=" + sex);
    }


    /**
     * 重写equil()  , 没有重写hashCode(), 两个内容相同的对象是可以放到同一个set里面的!!!!!!
     *
     * @param o
     * @return
     */
    public boolean equals(CellegeStudent o) {
        super.equals(o);
        return this.age == o.age && this.name.equals(o.name);
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }


}
