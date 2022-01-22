package dam.androidantoniovr.u4t8database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import dam.androidantoniovr.u4t8database.data.TodoListDBManager;

public class AddTaskActivity extends AppCompatActivity {

    private EditText etTodo;
    private EditText etToAccomplish;
    private EditText etDescription;
    private Spinner spPriority;
    private Spinner spStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        setUI();
    }

    //TODO - agregamos status y prioridad y gestionamos los spinners.
    private void setUI(){
        etTodo = findViewById(R.id.etTodo);
        etToAccomplish = findViewById(R.id.etToAccomplish);
        etDescription = findViewById(R.id.etDescription);

        spPriority = findViewById(R.id.spPriority);
        ArrayAdapter<CharSequence> spinnerPriority = ArrayAdapter.createFromResource(this, R.array.priority, android.R.layout.simple_spinner_item);
        spinnerPriority.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPriority.setAdapter(spinnerPriority);

        spStatus = findViewById(R.id.spStatus);
        ArrayAdapter<CharSequence> spinnerStatus = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        spinnerStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spStatus.setAdapter(spinnerStatus);


    }

    public void onClick(View view) {

        if (view.getId() == R.id.buttonOK){

            if (etTodo.getText().toString().length() >0){
                TodoListDBManager todoListDBManager = new TodoListDBManager(this);

                todoListDBManager.insert(etTodo.getText().toString(),
                        etToAccomplish.getText().toString(),
                        etDescription.getText().toString(),
                        spPriority.getSelectedItem().toString(),
                        spStatus.getSelectedItem().toString()
                        );
            }
            else{
                Toast.makeText(this, "Task name is empty", Toast.LENGTH_LONG).show();
            }
            finish();
        }
    }
}