package com.example.smson.hello.database.dbtest1;

/**
 * Created by sangmun on 2015-04-08.
 */
public class InfoClass {
    public int _id;
    public String name;
    public String contact;
    public String email;

    public InfoClass(){}

    public InfoClass(int _id , String name , String contact , String email){
        this._id = _id;
        this.name = name;
        this.contact = contact;
        this.email = email;
    }
}
