package dam.androidantoniovr.u4t8database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import dam.androidantoniovr.u4t8database.model.Task;

public class EditTaskActivity extends AppCompatActivity {

    private Task task;
    private EditText taskName;
    private EditText taskWhen;
    private Spinner taskPriority;
    private Spinner taskStatus;
    private EditText taskDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);


        setUI();
        obtenerData();
        setData();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem task) {
        switch (task.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(task);
    }

    public void setUI(){
        this.taskName = findViewById(R.id.etTodoEdit);
        this.taskWhen = findViewById(R.id.etToAccomplishEdit);
        this.taskPriority = findViewById(R.id.spPriorityEdit);
        this.taskStatus = findViewById(R.id.spStatusEdit);
        this.taskDescription = findViewById(R.id.etDescriptionEdit);
    }

    private void obtenerData(){
        this.task = (Task) getIntent().getSerializableExtra("task");
    }

    public void setData(){
        this.taskName.setText(task.getTodo());
        this.taskWhen.setText(task.getToAccomplish());
        this.taskPriority.setSelection(Integer.parseInt(task.getPriority()));
        this.taskStatus.setSelection(Integer.parseInt(task.getStatus()));
        this.taskDescription.setText(task.getDescription());
    }

//    public void onDelete(View view) {
//    }
//
//    public void onSave(View view) {
//    }
}