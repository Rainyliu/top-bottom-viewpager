package com.rainy.topbottomviewpager.timeline;

/**
 * Author: Rainy <br>
 * Description: top-bottom-viewpager <br>
 * Since: 2016/12/22 0022 下午 4:16 <br>
 */

public class TimeLineModel {
    private String name;
    private int age;

    public TimeLineModel() {
    }

    public TimeLineModel(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
