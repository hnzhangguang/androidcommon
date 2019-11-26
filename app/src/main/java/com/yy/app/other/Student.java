package com.yy.app.other;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * 实现 Parcelable 接口的实体类
 */
public class Student implements Parcelable {
    private int id;
    private String name;

    public Student() {
        super();
    }

    public Student(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // 序列化过程：必须按成员变量声明的顺序进行封装  
        dest.writeInt(id);
        dest.writeString(name);
    }

    // 反序列过程：必须实现Parcelable.Creator接口，并且对象名必须为CREATOR  
    // 读取Parcel里面数据时必须按照成员变量声明的顺序，Parcel数据来源上面writeToParcel方法，读出来的数据供逻辑层使用  
    public static final Parcelable.Creator<Student> CREATOR = new Creator<Student>() {

        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source.readInt(), source.readString());
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

}  