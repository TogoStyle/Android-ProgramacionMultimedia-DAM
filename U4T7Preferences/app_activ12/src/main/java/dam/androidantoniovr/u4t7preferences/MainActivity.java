package dam.androidantoniovr.u4t7preferences;

import static android.preference.PreferenceManager.getDefaultSharedPreferencesName;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    //DECLARAMOS LOS ATRIBUTOS RESPECTIVOS A LOS ELEMENTOS QUE UTILIZAMOS EN EL LAYOUT
    private final String MYPREFS = "MyPrefs";
    private EditText etPlayerName;
    private Spinner spinnerLevel;
    private EditText etScore;
    private Button btQuit;
    private CheckBox cksound;
    private Spinner spinnerBackground;
    private RadioGroup rgDifficulty;
    private View layout;
    private RadioGroup rgShared;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
    }

    private void setUI(){
        //EDIT TEXT PARA INTRODUCIR EL NOMBRE DEL JUGADOR
        etPlayerName = findViewById(R.id.playernameinput);

        //SPINNER ENCARGADO DE GESTIONAR EL NIVEL
        spinnerLevel = findViewById(R.id.levelinput);
        //ADAPTAMOS EL SPINNER A LA INFORMACIÓN DEL ARRAY LEVELS
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.levels, android.R.layout.simple_spinner_item);

        //DESPLEGAMOS HACIA ABAJO LA INFORMACIÓN DEL SPINNER
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(spinnerAdapter);

        //EDIT TEXT PARA INTRODUCIR LA PUNTUACION DEL JUGADOR
        etScore = findViewById(R.id.scoreinput);

        //TODO - BACKGROUND COLOR
        //SPINNER ENCARGADO DE GESTIONAR EL COLOR DEL BACKGORUND
        spinnerBackground = findViewById(R.id.backgroundcolorinput);
        //ADAPTAMOS EL SPINNER A LA INFORMACIÓN DEL ARRAY BACKGROUNDCOLORS
        ArrayAdapter<CharSequence> spinnerAdapterBackground = ArrayAdapter.createFromResource(this, R.array.backgroundcolors, android.R.layout.simple_spinner_item);

        //DESPLEGAMOS HACIA ABAJO LA INFORMACIÓN DEL SPINNER
        spinnerAdapterBackground.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBackground.setAdapter(spinnerAdapterBackground);

        //IDENTIFICAMOS EL LAYOUT PARA PODER CAMBIARLE EL BACKGROUND COLOR
        layout = findViewById(R.id.backgroundlayout);

        //TODO - AGREGAR EL CHECKBOX DE SOUND ACTIVE
        //IDENTIFICAMOS EL CHECKBOX PARA ACTIVAR SONIDO O NO
        cksound = findViewById(R.id.soundactiveinput);

        //TODO - AGREGAR EL RADIOGROUP PARA GESTIONAR LA DIFICULTAD
        //IDENTIFICAMOS EL RADIOGROUP DE LOS RADIOBUTTONS
        rgDifficulty = findViewById(R.id.radiogroup);
        //SELECCIONAMOS POR DEFECTO NIVEL EASY
        rgDifficulty.check(R.id.easybutton);

        //TODO - CAMBIAR EL BACKGROUND DE LA ACTIVITY SEGUN EL COLOR ESCOGIDO
        //AÑADIMOS UN ITEMSELECTEDLISTENER PARA QUE CADA VEZ QUE CAMBIEMOS LA SELECCION DE NUESTRO SPINNERBACKGROUND CAMBIE
        //EL BACKGROUND COLOR AL INSTANTE
        spinnerBackground.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            //HACEMOS OVERRIDE DE LOS MÉTODOS DE LA FUNCIÓN
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Y CUANDO SELECCIONAMOS EL ITEM CORRESPONDIENTE LLAMAMOS AL MÉTODO ENCARGADO DE CAMBIAR EL COLOR
                setColor();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        //IDENTIFICAMOS EL RADIO GROUP PARA ACCEDER A LA OTRA ACTIVITY DONDE OBSERVAREMOS LAS SHARED PREFERENCES
        rgShared = findViewById(R.id.radiogrouppreferences);
        rgShared.check(R.id.radioButtonSharedPreferences);



        //IDENTIFICAMOS EL BOTÓN QUIT PARA SALIR DE LA APLICACIÓN
        btQuit = findViewById(R.id.quitbutton);
        //AGREGAMOS UN LISTENER COMO A TODOS LOS BOTONES
        btQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //METODO ENCARGADO DE CAMBIAR EL COLOR DEL BACKGORUND SEGÚN LA POSICION DEL ITEM ESCOGIDO EN EL SPINNER
    private void setColor(){
        switch (spinnerBackground.getSelectedItemPosition()){
            case 0:
                layout.setBackgroundColor(Color.RED);
                break;
            case 1:
                layout.setBackgroundColor(Color.BLUE);
                break;
            case 2:
                layout.setBackgroundColor(Color.GREEN);
                break;
            case 3:
                layout.setBackgroundColor(Color.YELLOW);
                break;
            case 4:
                layout.setBackgroundColor(Color.WHITE);
                break;
            default:
                layout.setBackgroundColor(Color.WHITE);
                break;
        }
    }

    //TODO - GUARDAR LA INFORMACION EN EL ARCHIVO DE PREFERENCIAS.
    //UTILIZAMOS EL METODO ONPAUSE PARA GUARDAR LA INFORMACIÓN DE LA ACTIVITY EN EL ARCHIVO DE PREFERENCIAS
    @Override
    protected void onPause() {
        super.onPause();

        //OBTENEMOS EL OBJETO DE PREFERENCIAS COMPARTIDAS CON NUESTRA CLAVE PREVIAMENTE DECLADA Y PRIVADO.
        SharedPreferences myPreferences = getSharedPreferences(MYPREFS, Context.MODE_PRIVATE);

        //CREAMOS UN EDITOR PARA GUARDAR LA INFORMACIÓN DE LA USER INTERFACE EN EL ARCHIVO
        SharedPreferences.Editor editor = myPreferences.edit();

        //ESCRIBIMOS EL ARCHIVO DE PREFERENCIAS CON LA CLAVE IDENTIFICATIVA DE CADA CAMPO Y LA INFORMACIÓN RECOGIDA EN LA ACTIVITY
        editor.putString("PlayerName" , etPlayerName.getText().toString());
        editor.putInt("Level", spinnerLevel.getSelectedItemPosition());
        editor.putInt("Score", Integer.parseInt(etScore.getText().toString()));
        editor.putInt("BackgroundColor", spinnerBackground.getSelectedItemPosition());
        editor.putBoolean("Sound", cksound.isChecked());
        editor.putInt("Difficulty", rgDifficulty.getCheckedRadioButtonId());
        editor.putInt("Shared", rgShared.getCheckedRadioButtonId());

        //PRESERVAMOS LOS CAMBIOS
        editor.commit();

        SharedPreferences myPrefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor2 = myPrefs.edit();

        //ESCRIBIMOS EL ARCHIVO DE PREFERENCIAS CON LA CLAVE IDENTIFICATIVA DE CADA CAMPO Y LA INFORMACIÓN RECOGIDA EN LA ACTIVITY
        editor2.putString("PlayerName" , etPlayerName.getText().toString());
        editor2.putInt("Level", spinnerLevel.getSelectedItemPosition());
        editor2.putInt("Score", Integer.parseInt(etScore.getText().toString()));
        editor2.putInt("BackgroundColor", spinnerBackground.getSelectedItemPosition());
        editor2.putBoolean("Sound", cksound.isChecked());
        editor2.putInt("Difficulty", rgDifficulty.getCheckedRadioButtonId());
        editor2.putInt("Shared", rgShared.getCheckedRadioButtonId());

        editor2.commit();

        SharedPreferences myDefaultprefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor3 = myDefaultprefs.edit();

        //ESCRIBIMOS EL ARCHIVO DE PREFERENCIAS CON LA CLAVE IDENTIFICATIVA DE CADA CAMPO Y LA INFORMACIÓN RECOGIDA EN LA ACTIVITY
        editor3.putString("PlayerName" , etPlayerName.getText().toString());
        editor3.putInt("Level", spinnerLevel.getSelectedItemPosition());
        editor3.putInt("Score", Integer.parseInt(etScore.getText().toString()));
        editor3.putInt("BackgroundColor", spinnerBackground.getSelectedItemPosition());
        editor3.putBoolean("Sound", cksound.isChecked());
        editor3.putInt("Difficulty", rgDifficulty.getCheckedRadioButtonId());
        editor3.putInt("Shared", rgShared.getCheckedRadioButtonId());

        editor3.commit();
    }

    //TODO - RECUPERAR INFORMACIÓN DEL ARCHIVO DE PREFERENCIAS.
    @Override
    protected void onResume() {
        super.onResume();



        //OBTENEMOS EL ARCHIVO DE PREFERENCIAS.
        SharedPreferences myPreferences = getSharedPreferences(MYPREFS, Context.MODE_PRIVATE);

        //RECUPERAMOS LOS DATOS DEL ARCHIVO DE PREFERENCIAS CON EL VALOR IDENTIFICATIVO DE CADA CAMPO, Y POR DEFECTO AGENCIAMOS OTRO.
        etPlayerName.setText(myPreferences.getString("PlayerName", "unknown"));
        spinnerLevel.setSelection(myPreferences.getInt("Level", 0));
        etScore.setText(String.valueOf(myPreferences.getInt("Score", 0)));
        spinnerBackground.setSelection(myPreferences.getInt("BackgroundColor", 0));
        cksound.setChecked(myPreferences.getBoolean("Sound", false));
        rgDifficulty.check(myPreferences.getInt("Difficulty", R.id.easybutton));
        rgShared.check(myPreferences.getInt("Shared", R.id.radioButtonSharedPreferences));
    }

    public void goToSharedPreferencesActivity(View view) {
        //TODO - CREAMOS INTENT PARA IR A LA NUEVA ACTIVITY Y AÑADIMOS LA INFORMACIÓN DEL RADIOBUTTON SELECCIONADO
        startActivity(new Intent(this, ShowPreferences.class).putExtra("button", rgShared.getCheckedRadioButtonId()));
        //TODO - COMPROBAR BOTON DE SONIDO Y REPRODUCIR MP3
        if (cksound.isChecked()) {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.notification212);
            mp.start();
        }


    }


    //PREGUNTAS TEORICAS
    //TODO - ¿Cuál es el nombre del fichero de preferencias que se guarda?
    /**SharedPreferences myPreferences = getPreferences(MODE_PRIVATE);*/
    //EL MÉTODO DE GETPREFERENCES() GUARDA EL FICHERO DE PEREFERENCIAS CON EL NOMBRE DE LA ACTIVITY DONDE SE RECOJE LA INFORMACIÓN.
    /**SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(this);*/
    //GETDEFAULTSHAREDPREFERENCES() GUARDA EL FICHERO DE PREFERENCIAS CON UN NOMBRE POR DEFECTO DEPENDIENDO EL PROYECTO DEL PROGRAMADOR.

    //TODO - ¿Qué uso tiene (qué representa) ese fichero de preferencias?
    /**SharedPreferences myPreferences = getPreferences(MODE_PRIVATE);*/
    //PERMITE EL ACCESO A LAS PREFERENCIAS ESPECÍFICAS DE LA ACTIVIDAD DONDE NOS ENCONTREMOS
    /**SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(this);*/
    //ES EL FICHERO DE PREFERENCIAS POR DEFECTO DE NUESTRA APLICACIÓN, GUARDA LOS AJUSTES GENERALES.

}