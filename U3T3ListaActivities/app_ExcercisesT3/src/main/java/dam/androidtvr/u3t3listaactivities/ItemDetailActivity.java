package dam.androidtvr.u3t3listaactivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import dam.androidtvr.u3t3listaactivities.model.Item;

public class ItemDetailActivity extends AppCompatActivity {

    private Item item;
    private ImageView img;
    private TextView version;
    private TextView api;
    private TextView nombre;
    private TextView year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_item_detail);

        setUI();
        obtenerData();
        setData();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setUI(){
        this.img = findViewById(R.id.imageViewVersion);
        this.version = findViewById(R.id.Version);
        this.api = findViewById(R.id.Api);
        this.nombre = findViewById(R.id.Nombre);
        this.year = findViewById(R.id.Year);

        this.img.setOnClickListener(v -> wikipedia());
    }

    private void obtenerData(){
        this.item = (Item) getIntent().getSerializableExtra("item");
    }

    public void setData(){
        this.img.setImageResource(item.getId());
        this.version.setText(item.getNombreVersion());
        this.api.setText(String.valueOf(item.getNumeroAPI()));
        this.nombre.setText(item.getNombreVersion());
        this.year.setText(String.valueOf(item.getYear()));

    }

    public void wikipedia(){
        Uri web = Uri.parse(item.getURL());
        Intent intent = new Intent(Intent.ACTION_VIEW, web);

        if (intent.resolveActivity(getPackageManager()) != null) {
        startActivity(intent);
        }else {
            Log.d("ERROR WIKIPEDIA", "El enlace en wikipedia no ha sido posible cargarlo: " + item.getURL());
        }
    }
}