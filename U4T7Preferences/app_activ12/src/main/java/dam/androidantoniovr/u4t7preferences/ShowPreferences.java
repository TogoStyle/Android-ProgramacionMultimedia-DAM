package dam.androidantoniovr.u4t7preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.prefs.Preferences;

public class ShowPreferences extends AppCompatActivity {

    //DECLARAMOS LOS ATRIBUTOS RESPECTIVOS A LOS ELEMENTOS QUE UTILIZAMOS EN EL LAYOUT Y QUE RECOJEMOS DE LA MAINACTIVTY
    private TextView playerName;
    private TextView playerScore;
    private TextView playerLevel;
    private TextView playerDifficulty;
    private TextView playerSound;
    private TextView bgColor;
    private TextView fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_preferences);

        setUI();
    }
    private void setUI(){
        //TODO - RECIBIMOS EL ID DEL RADIO BUTTON SELECCIONADO
        //INICIALIZAMOS UNA VARIABLE QUE RECUPERA EL RADIOBUTTON SELECCIONADO POR EL USUARIO
        int radioButtonSelected = getIntent().getExtras().getInt("button");

        //IDENTIFICAMOS EL NOMBRE DEL JUGADOR
        playerName = findViewById(R.id.playerName);

        //IDENTIFICAMOS LA PUNTUACION DEL JUGADOR
        playerScore = findViewById(R.id.playerScore);

        //IDENTIFICAMOS EL NIVEL DEL JUGADOR
        playerLevel = findViewById(R.id.playerLevel);

        //IDENTIFICAMOS LA DIFICULTAD
        playerDifficulty = findViewById(R.id.playerDifficulty);

        //CHECKEAMOS SI SE HA SELECCIONADO EL SONIDO
        playerSound = findViewById(R.id.playerSound);

        //IDENTIFICAMOS EL COLOR DEL BACKGROUND
        bgColor = findViewById(R.id.bgColor);

        //IDENTIFICAMOS LA VARIABLE DONDE LOCALIZAREMOS EL NOMBRE DEL ARCHIVO.
        fileName = findViewById(R.id.fileName);



        //CREAMOS 3 CONDICIONES PARA CADA UNO DE LOS RADIOBUTTONS Y UTILIZAMOS LA LLAMADA ADECUADA PARA RECIBIR EL DICHERO DE PREFERENCIAS SEGUN LA OPCION
        //COMPARAMOS CADA UNO DE LOS IDs DE LOS RADIOBUTTONS
        if (radioButtonSelected == 2131296749){
            fileName.setText("MyPrefs");
            //LLAMADA ADECUADA DE GETSHAREDPREFERENCES
            SharedPreferences myPrefsShared = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            playerName.setText(myPrefsShared.getString("PlayerName", "unknown"));
            playerScore.setText(String.valueOf(myPrefsShared.getInt("Score", 0)));
            playerLevel.setText(String.valueOf(myPrefsShared.getInt("Level", 0)));
            playerDifficulty.setText(String.valueOf(myPrefsShared.getInt("Difficulty", R.id.easybutton)));
            playerSound.setText(String.valueOf(myPrefsShared.getBoolean("Sound", false)));
            bgColor.setText(String.valueOf(myPrefsShared.getInt("BackgroundColor", 0)));


        }else if (radioButtonSelected == 2131296748){

            //LLAMADA ADECUADA DE GERREFERENCES
           SharedPreferences prefs = getSharedPreferences( "MainActivity", Context.MODE_PRIVATE);

            String context = "MainActivity";
            fileName.setText(context);
            playerName.setText(prefs.getString("PlayerName", "unknown"));
            playerScore.setText(String.valueOf(prefs.getInt("Score", 0)));
            playerLevel.setText(String.valueOf(prefs.getInt("Level", 0)));
            playerDifficulty.setText(String.valueOf(prefs.getInt("Difficulty", R.id.easybutton)));
            playerSound.setText(String.valueOf(prefs.getBoolean("Sound", false)));
            bgColor.setText(String.valueOf(prefs.getInt("BackgroundColor", 0)));

        } else if (radioButtonSelected == 2131296747){
            //LLAMADA ADECUADA DE GETDEFAULTSHAREDPREFERENCES
            SharedPreferences myDefaultprefs = PreferenceManager.getDefaultSharedPreferences(this);
            String context = getPackageName() + Context.class;
            fileName.setText(context + "_preferences");

            fileName.setText(context);
            playerName.setText(myDefaultprefs.getString("PlayerName", "unknown"));
            playerScore.setText(String.valueOf(myDefaultprefs.getInt("Score", 0)));
            playerLevel.setText(String.valueOf(myDefaultprefs.getInt("Level", 0)));
            playerDifficulty.setText(String.valueOf(myDefaultprefs.getInt("Difficulty", R.id.easybutton)));
            playerSound.setText(String.valueOf(myDefaultprefs.getBoolean("Sound", false)));
            bgColor.setText(String.valueOf(myDefaultprefs.getInt("BackgroundColor", 0)));
        }
    }
}