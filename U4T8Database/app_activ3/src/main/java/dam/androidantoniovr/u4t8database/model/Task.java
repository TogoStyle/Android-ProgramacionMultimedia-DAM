package dam.androidantoniovr.u4t8database.model;

public class Task {
    private int _id;
    private String todo;
    private String toAccomplish;
    private String description;
    private String priority;
    private String status;

    public Task(int _id, String todo, String toAccomplish, String description, String priority, String status) {
        this._id = _id;
        this.todo = todo;
        this.toAccomplish = toAccomplish;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

    public int get_id() {
        return _id;
    }

    public String getTodo() {
        return todo;
    }

    public String getToAccomplish() {
        return toAccomplish;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public void setToAccomplish(String toAccomplish) {
        this.toAccomplish = toAccomplish;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
