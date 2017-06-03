package com.merpyzf.reviewandroid.domian;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 春水碧于天 on 2017/5/22.
 */


@DatabaseTable(tableName = "tb_user") //数据库的表名
public class User {

    @DatabaseField(generatedId = true) //设置自增
    private int id;
    @DatabaseField(columnName = "name") //设置字段名
    private String name;
    @DatabaseField(columnName = "desc")
    private String desc;

    public User(){


    }

    public User(String name, String desc) {
        this.name = name;
        this.desc = desc;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
