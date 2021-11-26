package dam.androidtvr.u3t3listaactivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements MyAdapter.OnItemClickListener{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private String[] myDataSet = {"Activity1", "Activity2", "Activity3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
    }

    private void setUI(){
        recyclerView = findViewById(R.id.recicleViewActivities);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(myDataSet, this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemCLick(String activityName) {
        Toast.makeText(this, activityName, Toast.LENGTH_LONG).show();

        try {
            startActivity(new Intent(this, Class.forName(getPackageName() + "." + activityName)));
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }


}