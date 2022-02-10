package dam.androidantoniovr.u5t9httpclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String URL_GEONAMES = "http://api.geonames.org/wikipediaSearchJSON";
    private final static String USER_NAME = "ToniVR";
    private final static int ROWS = 10;
    private final static String LANGUAGE = "ES";
    private final static String KEY = "eddfa74aba94b7c6008cce1b2dc6915e";
    private String latitud;
    private String longitud;
    private ProgressBar progressBar;

    private EditText etPlaceName;
    private Button btSearch;
    private ListView lvSearchResult;
    private ArrayList<String> listRearchResult;
    private ExecutorService executor;
    //NECESARIO INICIALIZAR EL ARRAYLIST PARA QUE NO DE ERROR DE VALOR NULO
    private ArrayList<GeonamesPlace> geonamesPlaces = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
    }

    private void setUI() {
        etPlaceName = findViewById(R.id.etPlaceName);
        btSearch = findViewById(R.id.btSearch);
        btSearch.setOnClickListener(this);
        progressBar = findViewById(R.id.progressBar);

        listRearchResult = new ArrayList<>();

        lvSearchResult = findViewById(R.id.lvSearchResult);

        lvSearchResult.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listRearchResult));

        //TODO - agregamos el itemClickListener a cada elemento del ListView para que detecte el click y cree la Uri seguido de la tarea en segundo plano.
        lvSearchResult.setOnItemClickListener((adapterView, view, position, l) -> {

            if (isNetworkAvailable()) {

                URL urlWeather;
                try {
                    Uri.Builder builder = new Uri.Builder();
                    builder.scheme("http")
                            .authority("api.openweathermap.org")
                            .appendPath("data")
                            .appendPath("2.5")
                            .appendPath("weather")
                            .appendQueryParameter("lat", latitud)
                            .appendQueryParameter("lon", longitud)
                            .appendQueryParameter("appid", KEY);

                    urlWeather = new URL(builder.build().toString());

                    startBackgroundTaskWeather(urlWeather);


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(MainActivity.this, "Write a place to search", Toast.LENGTH_LONG).show();
            }


        });
    }

    //TODO - creamos tarea en segundo plano en relación a la api de weather
    private void startBackgroundTaskWeather(URL url) {
        final int CONNECTION_TIMEOUT = 10000;
        final int READ_TIMEOUT = 7000;

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                final ArrayList<String> searchResult = new ArrayList<>();

                try {
                    urlConnection = (HttpURLConnection) url.openConnection();

                    urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
                    urlConnection.setReadTimeout(READ_TIMEOUT);

                    urlConnection.connect();

                    getDataWeather(urlConnection, searchResult);
                }catch (IOException e) {
                    Log.i("IOException", e.getMessage());
                }catch (JSONException e){
                    Log.i("JSONException", e.getMessage());
                }finally {
                    if (urlConnection != null)urlConnection.disconnect();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (searchResult.size() > 0){
                            //TODO - creamos Toast con la información que obtenemos del ataque a la api según la posición en la que se encuentren en el array.
                            Toast.makeText(MainActivity.this, "Weather Conditions for: " + latitud + " " + longitud + " "
                                    + "\nTEMP: " + searchResult.get(1) + "\nHUMIDITY: " + searchResult.get(2) + "\n" + searchResult.get(0) , Toast.LENGTH_LONG).show();
                        } else{
                            Toast.makeText(getApplicationContext(), "No possible to contact " + URL_GEONAMES, Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }

    //TODO - obtenemos la información de meteorología
    private void getDataWeather(HttpURLConnection urlConnection, ArrayList<String> searchResult) throws IOException, JSONException{

        if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){


            String resultStream = readStream(urlConnection.getInputStream());


            JSONObject json = new JSONObject(resultStream);
            //obtenemos el array de tipo json contenedor del objeto weather
            JSONArray jArray = json.getJSONArray("weather");
            //obtenemos el objeto tipo JSON main donde obtendremos la temperatura y humedad - captura de pantalla del ejemplo en el escritorio
            JSONObject currentWeatherObject = json.getJSONObject("main");

            if (jArray.length() > 0){

                    JSONObject item = jArray.getJSONObject(0);
                    //posicion 0
                    searchResult.add(item.getString("description"));
                    //posicion 1
                    searchResult.add(currentWeatherObject.getString("temp"));
                    //posicion 2
                    searchResult.add(currentWeatherObject.getString("humidity"));



            }else
                searchResult.add("No information found at geonames");
        }else{
            Log.i("URL", "ErrorCode" + urlConnection.getResponseCode());
        }
    }


    @Override
    public void onClick(View v) {


        progressBar.setVisibility(View.VISIBLE);

        if (isNetworkAvailable()) {
            //COMPROBAMOS QUE EL USUARIO HA ESCRITO ALGO.
            String place = etPlaceName.getText().toString();


            if (!place.isEmpty()){
                URL url;
                try {
                    //URL = NEW URL (URL_GEONAMES + "?q" + place + "&maxRows=" + ROWS + "&userName=" + USER_NAME)
                    //CREAMOS LA URI
                    Uri.Builder builder = new Uri.Builder();
                    builder.scheme("http")
                            .authority("api.geonames.org")
                            .appendPath("wikipediaSearchJSON")
                            .appendQueryParameter("q", place)
                            .appendQueryParameter("maxRows", String.valueOf(ROWS))
                            .appendQueryParameter("username", USER_NAME)
                            //TODO - Traducir a Español
                            .appendQueryParameter("lang", LANGUAGE);

                    url = new URL(builder.build().toString());


                    //INICIAMOS LA TAREA EN SEGUNDO PLANO
                    startBackgroundTask(url);


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }else
                Toast.makeText(this, "Write a place to search", Toast.LENGTH_LONG).show();
        }else
            Toast.makeText(this, "Sorry, network is not available", Toast.LENGTH_LONG).show();

        //TODO - Esconder el Teclado
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    //TAREA EN SEGUNDO PLANO
    private void startBackgroundTask(URL url) {
        final int CONNECTION_TIMEOUT = 10000;
        final int READ_TIMEOUT = 7000;

        executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                final ArrayList<String> searchResult = new ArrayList<>();

                try {
                    //CREAMOS EL OBJETO DE CONEXION INVOCANDO EL METODO DE OPENCONNECTION EN LA URL
                    urlConnection = (HttpURLConnection) url.openConnection();

                    //PARAMETROS DE CONEXION
                    urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
                    urlConnection.setReadTimeout(READ_TIMEOUT);


                    urlConnection.connect();

                    //LA CONEXION ES POSIBLE Y LOS CAMPOS O CONTENIDOS DEL OBJETO SON ACCESIBLES.
                    getData(urlConnection, searchResult);
                }catch (IOException e) {
                    Log.i("IOException", e.getMessage());
                }catch (JSONException e){
                    Log.i("JSONException", e.getMessage());
                }finally {
                    if (urlConnection != null)urlConnection.disconnect();
                }

                //ACTUALIZA EL CONTENIDO DE LA UI
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (searchResult.size() > 0){
                            //METEMOS LOS RESULTADOS EN EL LIST ADAPTER
                            ArrayAdapter<String> adapter = (ArrayAdapter<String>) lvSearchResult.getAdapter();
                            adapter.clear();
                            adapter.addAll(searchResult);
                            adapter.notifyDataSetChanged();
                        } else{
                            Toast.makeText(getApplicationContext(), "No possible to contact " + URL_GEONAMES, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


    }

    //OBTENEMOS LA INFORMACION DE LA API GEONAMES
    private void getData(HttpURLConnection urlConnection, ArrayList<String> searchResult) throws IOException, JSONException{

        //COMPROBAMOS QUE LA RESPUESTA DE LA CONEXIÓN SEA OK
        if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){

            //LEEMOS LA INFORMACION DESDE LA URL
            String resultStream = readStream(urlConnection.getInputStream());


            //Y CREAMOS UN OBJETO JSON COMO RESULTADO DE LO QUE OBTENEMOS
            JSONObject json = new JSONObject(resultStream);
            JSONArray jArray = json.getJSONArray("geonames");

            if (jArray.length() > 0){
                //RELLENAMOS LA LISTA CON INFORMACIÓN QUE OBTENEMOS DEL OBJETO JSON ITEM
                for (int i = 0; i < jArray.length() ; i++){
                    JSONObject item = jArray.getJSONObject(i);
                    //TODO - Agregar Longitud y Latitud
                    latitud = item.getString("lat");
                    longitud = item.getString("lng");
                    searchResult.add(item.getString("summary"));

                    searchResult.add(item.getString("summary") + "\nLAT: " +latitud + " LONG: " + longitud);

                    //TODO - Añadimos el lugar con los campos de resumen, latitud y longitud a nuestro ArrayList del tipo GeonamesPlace.
                   geonamesPlaces.add(new GeonamesPlace(Double.parseDouble(latitud), Double.parseDouble(longitud), item.getString("summary")));
                }
            }else
                searchResult.add("No information found at geonames");
        }else{
            Log.i("URL", "ErrorCode" + urlConnection.getResponseCode());
        }

        //TODO - Agregar ProgressBar (porción de código necesaria para evitar conflicto entre hilos)
        runOnUiThread(new Runnable() {

            @Override
            public void run() {

               progressBar.setVisibility(View.GONE);

            }
        });
    }

    private String readStream(InputStream inputStream) throws IOException {
    StringBuilder sb = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String nextLine = "";
        while ((nextLine = reader.readLine()) != null){
            sb.append(nextLine);
        }

    return sb.toString();
    }

    //METODO PARA COMPROBAR QUE TENEMOS CONEXION
    private Boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Network nw = connectivityManager.getActiveNetwork();
            if (nw == null){
                return false;
            }

            NetworkCapabilities actNw = connectivityManager.getNetworkCapabilities(nw);

            return actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)); 
        } else {
            NetworkInfo nwInfo = connectivityManager.getActiveNetworkInfo();
            return  nwInfo != null && nwInfo.isConnected();
        }

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        if (executor != null){
            executor.shutdownNow();
            Log.i("EXECUTOR", "ALL TASK CANCELLED!!!!");
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO - GUARDAR ESTADO DE LA INSTANCIA
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("Lista", listRearchResult);
    }

    /** PREGUNTAR COMO RECUPERAR EL ESTADO Y CONTENIDO DE LA LISTA  */

//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        listRearchResult = savedInstanceState.getStringArrayList("Lista");
//    }

}