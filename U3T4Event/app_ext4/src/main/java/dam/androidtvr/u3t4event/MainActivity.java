package dam.androidtvr.u3t4event;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etEventName;
    private TextView tvCurrentData;

    private ActivityResultLauncher<Intent> eventActivityResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();

        registerForEventResult();
    }

    private void setUI() {
        etEventName = (EditText) findViewById(R.id.etEventName);
        tvCurrentData = (TextView) findViewById(R.id.tvCurrentData);

        tvCurrentData.setText("");
    }

    private void registerForEventResult() {
        eventActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK){
                            Intent data = result.getData();
                            if (data != null){
                                tvCurrentData.setText(data.getStringExtra("EventData"));
                            }
                        }
                    }

                });
    }

    public void editEventData(View v){
        Intent intent = new Intent(this, EventDataActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString("EventName", etEventName.getText().toString());

        intent.putExtras(bundle);

        eventActivityResult.launch(intent);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("data", String.valueOf(tvCurrentData.getText()));
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tvCurrentData.setText(savedInstanceState.getString("data"));
    }
}