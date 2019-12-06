package com.yy.app.data.bean;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Categoryes")
public class Category extends Model {
    @Column(name = "Name")
    public String name;

    @Column(name = "StuId")
    public Integer stuId;
}
