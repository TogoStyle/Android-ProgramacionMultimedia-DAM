package dam.androidtvr.u3t4event;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class EventDataActivity extends AppCompatActivity  implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private String priority = "Normal";
    private TextView tvEventName;
    private RadioGroup rgPriority;
    private DatePicker dpDate;
    private TimePicker tpTime;
    private Button btAccept;
    private Button btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_data);

        setUI();

        Bundle inputData = getIntent().getExtras();

        tvEventName.setText(inputData.getString("EventName"));
    }

    private void setUI(){
        tvEventName = (TextView) findViewById(R.id.tvEventName);
        rgPriority = (RadioGroup) findViewById(R.id.rgPriority);
        rgPriority.check(R.id.rbNormal);
        dpDate = (DatePicker) findViewById(R.id.dpDate);
        tpTime = (TimePicker) findViewById(R.id.tpTime);
        btAccept = (Button) findViewById(R.id.btAccept);
        btCancel = (Button) findViewById(R.id.btCancel);

        btAccept.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        rgPriority.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view){
        Intent activityResult = new Intent();
        Bundle eventData = new Bundle();

        switch (view.getId()){
            case R.id.btAccept:
            String[] month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
            eventData.putString("EventData", "Priority: " + priority + "\n" +
                    "Month: " + month[dpDate.getMonth()] + "\n" +
                    "Day: " + dpDate.getDayOfMonth() + "\n" +
                    "Year: " + dpDate.getYear());
                break;
            case R.id.btCancel:
                eventData.putString("EventData", "");
                break;
        }
        activityResult.putExtras(eventData);
        setResult(RESULT_OK, activityResult);

        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i){
        switch (i) {
            case R.id.rbLow:
                priority = "Low";
                break;
            case R.id.rbNormal:
                priority = "Normal";
                break;
            case R.id.rbHigt:
                priority = "High";
                break;
        }
    }
}