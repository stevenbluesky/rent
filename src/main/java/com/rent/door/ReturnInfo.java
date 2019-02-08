package com.rent.door;

/**
 * Created by xiaoding on 2017/8/15.
 */
public class ReturnInfo {
    private String success;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getTask__id() {
        return task__id;
    }

    public void setTask__id(String task__id) {
        this.task__id = task__id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String task__id;
    private String message;

}
