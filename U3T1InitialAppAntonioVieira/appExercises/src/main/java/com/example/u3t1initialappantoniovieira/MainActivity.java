package com.example.u3t1initialappantoniovieira;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private  int count;
    private TextView tvDisplay;
    private Button buttonIncrease, buttonDecrease, buttonReset, buttonIncrease2, buttonDecrease2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
    }

    private void setUI(){
        tvDisplay = findViewById(R.id.tvDisplay);
        buttonIncrease = findViewById(R.id.buttonIncrease);
        buttonDecrease = findViewById(R.id.buttonDecrease);
        buttonReset = findViewById(R.id.buttonReset);
        buttonIncrease2 = findViewById(R.id.buttonIncrease2);
        buttonDecrease2 = findViewById(R.id.buttonDecrease2);

        buttonIncrease.setOnClickListener(this);
        buttonDecrease.setOnClickListener(this);
        buttonIncrease2.setOnClickListener(this);
        buttonDecrease2.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonIncrease: count++; break;
            case R.id.buttonDecrease: count--; break;
            case R.id.buttonIncrease2: count = count + 2; break;
            case R.id.buttonDecrease2: count = count - 2; break;
            case R.id.buttonReset: count = 0; break;
        }
        tvDisplay.setText(getString(R.string.number_of_elements) + ": " + count);
    }

    @Override
    protected void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        TextView textViewNumber = findViewById(R.id.tvDisplay);
        outstate.putString("tvDisplay", textViewNumber.getText().toString());
        outstate.putInt("contador", count);

        Log.i("clave", "Guardando activity y contador" + textViewNumber.getText().toString() + " count = " + count);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TextView textViewNumber = findViewById(R.id.tvDisplay);
        String tvDisplay = savedInstanceState.getString("tvDisplay", "valor por defecto");
        int contador = savedInstanceState.getInt("contador", 0);
        textViewNumber.setText(tvDisplay + contador);

        Log.i("clave", "Lo guardado: " + tvDisplay + " ------- " + contador);
    }
}