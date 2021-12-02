package dam.androidtvr.u3t4event;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EventDataActivity extends AppCompatActivity  implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private String priority = "Normal";
    private TextView tvEventName;
    private RadioGroup rgPriority;
    private DatePicker dpDate;
    private TimePicker tpTime;
    private Button btAccept;
    private Button btCancel;
    private EditText place;

    String[] months;

    private Button date_in;
    private Button time_in;

    private TextView fechain;
    private TextView timein;

    Calendar calendarTime = Calendar.getInstance();
    Calendar calendarDate = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_data);

        setUI();

        Bundle inputData = getIntent().getExtras();

        tvEventName.setText(inputData.getString("EventName"));

        months = getResources().getStringArray(R.array.months);

        date_in = findViewById(R.id.buttonFecha);
        time_in = findViewById(R.id.buttonHora);
        fechain = findViewById(R.id.fecha);
        timein = findViewById(R.id.hora);




        date_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(date_in);
            }
        });

        time_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeDialog(time_in);
            }
        });
    }

    private void showTimeDialog(TextView time_in) {


        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                calendarTime.set(Calendar.HOUR_OF_DAY, hour);
                calendarTime.set(Calendar.MINUTE, minute);
                SimpleDateFormat sim = new SimpleDateFormat("HH:mm");
                timein.setText(sim.format(calendarTime.getTime()));
            }
        };

        new TimePickerDialog(EventDataActivity.this, timeSetListener, calendarTime.get(Calendar.HOUR_OF_DAY), calendarTime.get(Calendar.MINUTE), false).show();
    }

    private void showDateDialog(TextView date_in) {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayofmonth) {
                calendarDate.set(Calendar.YEAR, year);
                calendarDate.set(Calendar.MONTH, month);
                calendarDate.set(Calendar.DAY_OF_MONTH, dayofmonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");

                fechain.setText(simpleDateFormat.format(calendarDate.getTime()));
            }
        };
        new DatePickerDialog(EventDataActivity.this, dateSetListener, calendarDate.get(Calendar.YEAR), calendarDate.get(Calendar.MONTH), calendarDate.get(Calendar.DAY_OF_MONTH)).show();
    }



    private void setUI(){
        tvEventName = (TextView) findViewById(R.id.tvEventName);
        rgPriority = (RadioGroup) findViewById(R.id.rgPriority);
        rgPriority.check(R.id.rbNormal);
       /* dpDate = (DatePicker) findViewById(R.id.dpDate);
        tpTime = (TimePicker) findViewById(R.id.tpTime);*/
        btAccept = (Button) findViewById(R.id.btAccept);
        btCancel = (Button) findViewById(R.id.btCancel);
        place = (EditText) findViewById(R.id.editTextPlace);

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
            eventData.putString("EventData", "Place-> ".toUpperCase() + place.getText().toString() + "\n" + "Priority-> ".toUpperCase() + priority + "\n" +
                    "Month-> ".toUpperCase() + months[calendarDate.get(Calendar.MONTH)] + "\n" +
                    "Day-> ".toUpperCase() +  calendarDate.get(Calendar.DAY_OF_MONTH) + "\n" +
                    "Year-> ".toUpperCase() + calendarDate.get(Calendar.YEAR)+ "\n" +
                    "Hour-> ".toUpperCase() + calendarTime.get(Calendar.HOUR_OF_DAY) + ":" + calendarTime.get(Calendar.MINUTE));
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