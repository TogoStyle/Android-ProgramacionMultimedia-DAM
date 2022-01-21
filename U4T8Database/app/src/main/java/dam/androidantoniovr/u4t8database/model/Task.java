package dam.androidantoniovr.u4t8database.model;

public class Task {
    private int _id;
    private String todo;
    private String toAccomplish;
    private String description;

    public Task(int _id, String todo, String toAccomplish, String description) {
        this._id = _id;
        this.todo = todo;
        this.toAccomplish = toAccomplish;
        this.description = description;
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

}
