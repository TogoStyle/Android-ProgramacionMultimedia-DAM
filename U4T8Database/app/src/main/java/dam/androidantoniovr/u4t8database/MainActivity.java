package dam.androidantoniovr.u4t8database;

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

import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private RecyclerView rvTodoList;
    private TodoListDBManager todoListDBManager;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoListDBManager = new TodoListDBManager(this);
        myAdapter = new MyAdapter(todoListDBManager);

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



//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, /*R.id.nav_host_fragment_content_main*/);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
}