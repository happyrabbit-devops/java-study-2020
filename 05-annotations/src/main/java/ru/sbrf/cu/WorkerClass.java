package ru.sbrf.cu;

public class WorkerClass {
    private String name = "job";

    public void makeWork1(){
        System.out.println("Method 1 does '" + name + "'");
    }

    public void makeWork2(){
        System.out.println("Method 2 does '" + name + "'");
    }

    public void makeWork3(){
        System.out.println("Method 3 does '" + name + "'");
    }

    public void setName(String name) {
        this.name = name;
    }
}
