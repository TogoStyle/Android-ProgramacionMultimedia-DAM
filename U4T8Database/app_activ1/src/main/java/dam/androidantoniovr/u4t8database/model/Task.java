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
}
