package com.example.practica4_antoniovr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = "LogsAndroid-1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();

        Log.i(DEBUG_TAG,  " onCreate");
    }

    private void setUI(){
        EditText etPulgada = findViewById(R.id.et_Pulgada);
        EditText etResultado = findViewById(R.id.et_Resultado);
        Button buttonConvertir = findViewById(R.id.buttonConvertir);
        TextView textViewError = findViewById(R.id.textViewError);
        textViewError.setVisibility(View.INVISIBLE);

        buttonConvertir.setOnClickListener(view -> {
                    Log.i(DEBUG_TAG, buttonConvertir.getId() + " onButton Convertir");
                    try {
                        textViewError.setVisibility(View.INVISIBLE);
                        etResultado.setText(convertirpulgada(etPulgada.getText().toString()));
                    }catch (Exception e){
                        try {
                            etPulgada.setText(convertircm(etResultado.getText().toString()));
                        } catch (Exception exception) {
                           textViewError.setVisibility(View.VISIBLE);
                        }
                    }
                }
        );

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(DEBUG_TAG,  " onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        TextView textViewError = findViewById(R.id.textViewError);
        outState.putString("error", textViewError.getText().toString());
        Log.i(DEBUG_TAG, " onSaveInstanceState");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(DEBUG_TAG, " onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(DEBUG_TAG,  " onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(DEBUG_TAG,  " onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(DEBUG_TAG, " onRestart");
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        Log.i(DEBUG_TAG, " onDestroy");
        if (isFinishing()) {
            Log.i(DEBUG_TAG,  " finishedByUser");
        } else Log.i(DEBUG_TAG, " finishedBySystem");
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TextView textViewError = findViewById(R.id.textViewError);
        textViewError.setText(savedInstanceState.getString("error"));
        textViewError.setVisibility(View.VISIBLE);
        Log.i(DEBUG_TAG, " onRestoreInstaceState");
    }


    private String convertirpulgada(String pulgadaText) throws Exception{
        double pulgadaValue = Double.parseDouble(pulgadaText);
        if (pulgadaValue >= 1){
            pulgadaValue = pulgadaValue * 2.54;
            return String.valueOf(String.format("%.2f", pulgadaValue));
        } else throw new Exception("Sólo números >=1");
    }

    private String convertircm(String cmText) throws Exception{
        double cmValue = Double.parseDouble(cmText);
        if (cmValue >= 1){
            cmValue = cmValue / 2.54;
            return String.valueOf(String.format("%.2f", cmValue));
        } else throw new Exception("Sólo números >=1");
    }
}