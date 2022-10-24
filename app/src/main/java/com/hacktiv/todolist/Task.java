package com.hacktiv.todolist;

public class Task {
    private int id;
    private String taskName;

    public Task(){
        super();
    }

    public Task(String taskName){
        this.taskName = taskName;
    }

    public Task(int id, String taskName){
        this.id = id;
        this.taskName = taskName;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTaskName(){
        return taskName;
    }

    public void setTaskName(String taskName){
        this.taskName = taskName;
    }
}
