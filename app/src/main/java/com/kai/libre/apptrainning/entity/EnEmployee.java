package com.kai.libre.apptrainning.entity;

/**
 * Created by Kai on 1/11/2017.
 */

public class EnEmployee {
    private String nameEmployee;
    private String imageAva;
    private int noti1;
    private int noti2;

    public EnEmployee(String nameEmployee, String imageAva, int noti1, int noti2) {
        this.nameEmployee = nameEmployee;
        this.imageAva = imageAva;
        this.noti1 = noti1;
        this.noti2 = noti2;
    }

    public EnEmployee() {
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getImageAva() {
        return imageAva;
    }

    public void setImageAva(String imageAva) {
        this.imageAva = imageAva;
    }

    public int getNoti1() {
        return noti1;
    }

    public void setNoti1(int noti1) {
        this.noti1 = noti1;
    }

    public int getNoti2() {
        return noti2;
    }

    public void setNoti2(int noti2) {
        this.noti2 = noti2;
    }
}
