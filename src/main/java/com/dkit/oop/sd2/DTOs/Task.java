package com.dkit.oop.sd2.DTOs;

/**                                                     OOP Feb 2022
 *  Data Transfer Object (DTO)
 *
 * This POJO (Plain Old Java Object) is called the Data Transfer Object (DTO).
 * (or, alternatively, the Model Object or the Value Object).
 * It is used to transfer data between the DAO and the Business Objects.
 * Here, it represents a row of data from the User database table.
 * The DAO creates and populates a User object (DTO) with data retrieved from
 * the resultSet and passes the User object to the Business Layer.
 *
 * Collections of DTOs( e.g. ArrayList<User> ) may also be passed
 * between the Data Access Layer (DAOs) and the Business Layer objects.
 */

public class Task
{
    private int taskid;
    private String title;
    private String tag;
    private String duedate;


    public Task(int taskId, String title, String tag, String duedate)
    {
        this.taskid = taskId;
        this.title = title;
        this.tag = tag;
        this.duedate = duedate;
    }

    public Task()
    {
    }

    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
        this.taskid = taskid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String task) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskid=" + taskid +
                ", title='" + title + '\'' +
                ", tag='" + tag + '\'' +
                ", duedate='" + duedate + '\'' +
                '}';
    }
}
