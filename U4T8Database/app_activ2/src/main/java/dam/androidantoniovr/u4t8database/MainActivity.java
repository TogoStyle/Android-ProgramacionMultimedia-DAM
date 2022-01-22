package dam.androidantoniovr.u4t8database;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dam.androidantoniovr.u4t8database.data.TodoListDBManager;
import dam.androidantoniovr.u4t8database.databinding.ActivityMainBinding;
import dam.androidantoniovr.u4t8database.model.Task;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements MyAdapter.OnTaskClickListener{

    private RecyclerView rvTodoList;
    private TodoListDBManager todoListDBManager;
    private MyAdapter myAdapter;
    private Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoListDBManager = new TodoListDBManager(this);
        myAdapter = new MyAdapter(todoListDBManager, this);

        setUI();

    }

    private void setUI(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener((view -> {
            startActivity(new Intent(getApplicationContext(), AddTaskActivity.class));
        }));

        rvTodoList = findViewById(R.id.rvTodoList);
        rvTodoList.setHasFixedSize(true);
        rvTodoList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvTodoList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvTodoList.setAdapter(myAdapter);
    }


    @Override
    public void onResume(){
        super.onResume();

        myAdapter.getData();
    }

    @Override
    public void onDestroy(){

        todoListDBManager.close();

        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTaskClick(Task task) {

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_edit_task);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button Save = dialog.findViewById(R.id.btSave);
        Button Delete = dialog.findViewById(R.id.btDelete);

        EditText taskName = findViewById(R.id.etTodoEdit);
        EditText taskWhen = findViewById(R.id.etToAccomplishEdit);
        Spinner taskPriority = findViewById(R.id.spPriorityEdit);
        Spinner taskStatus  = findViewById(R.id.spStatusEdit);
        EditText taskDescription = findViewById(R.id.etDescriptionEdit);

        taskName.setText(task.getTodo());
        taskWhen.setText(task.getToAccomplish());
        taskPriority.setSelection(Integer.parseInt(task.getPriority()));
        taskStatus.setSelection(Integer.parseInt(task.getStatus()));
        taskDescription.setText(task.getDescription());

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                task.setTodo(taskName.getText().toString());
                task.setToAccomplish(taskWhen.getText().toString());
                task.setPriority(taskPriority.getSelectedItem().toString());
                task.setStatus(taskStatus.getSelectedItem().toString());
                task.setDescription(taskDescription.getText().toString());
                myAdapter.notifyDataSetChanged();

                Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                todoListDBManager.deleteTask(task.get_id());
                myAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }


//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, /*R.id.nav_host_fragment_content_main*/);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
}