package dam.androidtvr.u3t2implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String IMPLICIT_INTENTS = "ImplicitIntents";
    private EditText etUri, etLocation, etText, etZoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //try {
            setUI();
        //} catch (Exception e) {
           // e.printStackTrace();
       // }
    }

    private void setUI()/* throws Exception*/ {
        Button btOpenUri, btOpenLocation, btShareText;

        etUri = findViewById(R.id.etUri);
        etLocation = findViewById(R.id.etLocation);
        etText = findViewById(R.id.etText);
        etZoom = findViewById(R.id.etZoom);

        btOpenUri = findViewById(R.id.btOpenUri);
        btOpenLocation = findViewById(R.id.btOpenLocation);
        btShareText = findViewById(R.id.btShareText);

        /*float zoom = Float.parseFloat(etZoom.getText().toString());

        if (zoom < 1 || zoom > 23 ){
            throw new Exception();
        }*/

        btOpenUri.setOnClickListener(this);
        btOpenLocation.setOnClickListener(this);
        btShareText.setOnClickListener(this);
    }

    private void openWebsite(String urlText){
        Uri webpage = Uri.parse(urlText);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        } else {
            Log.d(IMPLICIT_INTENTS, "openWebsite: Can't handle this intent");
        }
    }

    private void openLocation(String location, String zoom){
        Uri addresUri = Uri.parse("geo:0,0?q=" + location + "z="+ zoom);

        Intent intent = new Intent(Intent.ACTION_VIEW, addresUri);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        } else {
            Log.d(IMPLICIT_INTENTS, "openWebsite: Can't handle this intent");
        }
    }

    private void shareText(String text){
        new ShareCompat.IntentBuilder(this)
                .setType("text/plain")
                .setText(text)
                .startChooser();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btOpenUri:
                openWebsite(etUri.getText().toString());
                break;
            case R.id.btOpenLocation:
                openLocation(etLocation.getText().toString(), etZoom.getText().toString());
                break;
            case R.id.btShareText:
                shareText(etText.getText().toString());
                break;
        }
    }
}