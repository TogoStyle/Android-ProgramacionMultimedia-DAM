package dam.androidantoniovr.u4t7preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private final String MYPREFS = "MyPrefs";
    private EditText etPlayerName;
    private Spinner spinnerLevel;
    private EditText etScore;
    private Button btQuit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
    }

    private void setUI(){
        etPlayerName = findViewById(R.id.playernameinput);

        spinnerLevel = findViewById(R.id.levelinput);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.levels, android.R.layout.simple_spinner_item);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(spinnerAdapter);

        etScore = findViewById(R.id.scoreinput);

        btQuit = findViewById(R.id.quitbutton);
        btQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences myPreferences = getSharedPreferences(MYPREFS, MODE_PRIVATE);

        SharedPreferences.Editor editor = myPreferences.edit();

        editor.putString("PlayerName" , etPlayerName.getText().toString());
        editor.putInt("Level", spinnerLevel.getSelectedItemPosition());
        editor.putInt("Score", Integer.parseInt(etScore.getText().toString()));

        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences myPreferences = getSharedPreferences(MYPREFS, MODE_PRIVATE);

        etPlayerName.setText(myPreferences.getString("PlayerName", "unknown"));
        spinnerLevel.setSelection(myPreferences.getInt("Level", 0));
        etScore.setText(String.valueOf(myPreferences.getInt("Score", 0)));
    }
}