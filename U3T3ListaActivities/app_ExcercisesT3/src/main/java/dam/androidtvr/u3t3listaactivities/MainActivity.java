package dam.androidtvr.u3t3listaactivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import dam.androidtvr.u3t3listaactivities.model.Item;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView icono;

    private ArrayList<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setUI();
    }


    private void setUI() {
        recyclerView = findViewById(R.id.recicleViewActivities);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        icono = findViewById(R.id.icono);
        icono.setVisibility(View.INVISIBLE);

        mAdapter = new MyAdapter(inicializarArray(), this);
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(mAdapter);
    }

    private ArrayList<Item> inicializarArray() {
        Item iceCream = new Item();
        iceCream.setId((R.drawable.ice_cream_android));
        iceCream.setNombreVersion("Ice Cream");
        iceCream.setNumeroAPI(15);
        iceCream.setYear(2011);
        iceCream.setURL("https://es.wikipedia.org/wiki/Android_Ice_Cream_Sandwich");
        iceCream.setVersion("4.0");
        items.add(iceCream);

        Item jellyBean = new Item();
        jellyBean.setId((R.drawable.android_jelly_bean_logo_svg));
        jellyBean.setNombreVersion("Jelly Bean");
        jellyBean.setNumeroAPI(16);
        jellyBean.setYear(2012);
        jellyBean.setURL("https://es.wikipedia.org/wiki/Android_Jelly_Bean");
        jellyBean.setVersion("4.1 4.2 4.3");
        items.add(jellyBean);

        Item kitKat = new Item();
        kitKat.setId((R.drawable.kitkat));
        kitKat.setNombreVersion("Kit Kat");
        kitKat.setNumeroAPI(19);
        kitKat.setYear(2013);
        kitKat.setURL("https://es.wikipedia.org/wiki/Android_KitKat");
        kitKat.setVersion("4.4");
        items.add(kitKat);

        Item lolliPop = new Item();
        lolliPop.setId((R.drawable.lollipop_android));
        lolliPop.setNombreVersion("LolliPop");
        lolliPop.setNumeroAPI(21);
        lolliPop.setYear(2014);
        lolliPop.setURL("https://es.wikipedia.org/wiki/Android_Lollipop");
        lolliPop.setVersion("5.0 5.1");
        items.add(lolliPop);

        Item marshmallow = new Item();
        marshmallow.setId((R.drawable.marshmallow_android));
        marshmallow.setNombreVersion("Marshmallow");
        marshmallow.setNumeroAPI(23);
        marshmallow.setYear(2015);
        marshmallow.setURL("https://es.wikipedia.org/wiki/Android_Marshmallow");
        marshmallow.setVersion("6.0");
        items.add(marshmallow);

        Item nougat = new Item();
        nougat.setId((R.drawable.nougat_android));
        nougat.setNombreVersion("Nougat");
        nougat.setNumeroAPI(24);
        nougat.setYear(2016);
        nougat.setURL("https://es.wikipedia.org/wiki/Android_Nougat#:~:text=Lanzado%20por%20primera%20vez%20como,tel%C3%A9fono%20inteligente%20lanzado%20con%20Nougat.");
        nougat.setVersion("7.0 7.1");
        items.add(nougat);

        Item oreo = new Item();
        oreo.setId((R.drawable.oreo_android));
        oreo.setNombreVersion("Oreo");
        oreo.setNumeroAPI(26);
        oreo.setYear(2017);
        oreo.setURL("https://es.wikipedia.org/wiki/Android_Oreo#:~:text=La%20versi%C3%B3n%20final%20de%20Android,aseguran%20la%20estabilidad%20del%20sistema.");
        oreo.setVersion("8.0 8.1");
        items.add(oreo);

        Item pie = new Item();
        pie.setId((R.drawable.pie_android));
        pie.setNombreVersion("Pie");
        pie.setNumeroAPI(28);
        pie.setYear(2018);
        pie.setURL("https://es.wikipedia.org/wiki/Android_Pie");
        pie.setVersion("9.0");
        items.add(pie);

        Item android10 = new Item();
        android10.setId((R.drawable._0_android));
        android10.setNombreVersion("Android 10");
        android10.setNumeroAPI(29);
        android10.setYear(2019);
        android10.setURL("https://es.wikipedia.org/wiki/Android_10");
        android10.setVersion("10");
        items.add(android10);

        Item android11 = new Item();
        android11.setId((R.drawable._1_android));
        android11.setNombreVersion("Android 11");
        android11.setNumeroAPI(30);
        android11.setYear(2020);
        android11.setURL("https://es.wikipedia.org/wiki/Android_11");
        android11.setVersion("11");
        items.add(android11);

        Item android12 = new Item();
        android12.setId((R.drawable._2_android));
        android12.setNombreVersion("Android 12");
        android12.setNumeroAPI(31);
        android12.setYear(2021);
        android12.setURL("https://es.wikipedia.org/wiki/Android_12");
        android12.setVersion("12");
        items.add(android12);


        return items;
    }

    @Override
    public void onItemCLick(Item item) {
        //Toast.makeText(this, activityName, Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, ItemDetailActivity.class).putExtra("item", item));

    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            items.remove(viewHolder.getAdapterPosition());
            mAdapter.notifyDataSetChanged();


            if (items.size() < 1) {
                icono.setVisibility(View.VISIBLE);
            } else {
                icono.setVisibility(View.INVISIBLE);
            }
        }
    };

    public void onRestore(View v) {

        if (items.size() == 11){
            Toast.makeText(this, "No hay nada que reestablecer", Toast.LENGTH_LONG).show();
        }

        onDeleteAll(v);
        setUI();
        icono.setVisibility(View.INVISIBLE);

    }

    public void onDeleteAll(View view) {

        items.clear();
        icono.setVisibility(View.VISIBLE);
        mAdapter.notifyDataSetChanged();

    }
}