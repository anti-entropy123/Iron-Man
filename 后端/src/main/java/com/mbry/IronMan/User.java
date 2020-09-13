package com.mbry.IronMan;

public class User {

    private String name;
    private int old;
    private String sex;

    public User() {}
    public User(
        String name,
        int old,
        String sex) {
        this.setName(name);
        this.setOld(old);
        this.setSex(sex);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOlde() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }

    public String getSex() {
        return sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
}
