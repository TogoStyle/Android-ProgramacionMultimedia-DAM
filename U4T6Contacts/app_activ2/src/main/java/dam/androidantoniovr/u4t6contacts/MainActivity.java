package dam.androidantoniovr.u4t6contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnContactClickListener{
    //CREAMOS EL OBJETO DE CONTACTOS
    MyContacts myContacts;
    //OBJETO DEL RECYCLERVIEW
    RecyclerView recyclerView;
    //OBJETO DEL FRAGMENT QUE CUANDO HAGAMOS CLICK SOBRE EL CONTACTO, SE VA A MOSTRAR.
    FragmentContainerView cardBottom;

    //PERMISOS REQUERIDOS PARA OBTENER LOS CONTACTOS EN NUESTRA APP. SOLO NECESITAMOS LECTURA YA QUE CON EL INTENT MEDIANTE
    //EL CLICK MANTENIDO PODREMOS ACCEDER FÁCILMENTE A LA APP DE CONTACTOS.
    private static String[] PERMISSIONS_CONTACTS = {Manifest.permission.READ_CONTACTS};

    //ID PARA IDENTIFICAR LA SOLICITUD DE PERMISO DE ACCESO A LOS CONTACTOS
    private static final int REQUEST_CONTACTS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setUI();


    }


    //TODO - mantener contactos actualizados
    //COMPROBAMOS LOS PERMISOS EN EL MÉTODO ONRESUME PARA MANTENERLOS ACTUALIZADOS.
    @Override
    protected void onResume() {

        //COMPROBAMOS LOS PERMISOS PARA OBTENER LA INFORMACIÓN.
        super.onResume();
        if (checkPermissions())
            setListAdapter();
    }

    private void setUI(){
        //INICIALIZAMOS A NUESTRO RECYCLERVIEW AGENCIANDOLO POR ID SEGUN EL LAYOUT.
        recyclerView = findViewById(R.id.recyclerViewContacts);
        //FIJAMOS EL TAMAÑO DEL RECYCLERVIEW
        recyclerView.setHasFixedSize(true);
        //INICIALIZAMOS A NUESTRO LAYOUT DEL FRAGMENT AGENCIANDOLO POR ID SEGUN EL LAYOUT.
        cardBottom = findViewById(R.id.fragmentContainerView);
        //E IREMOS JUGANDO CON SU VISIBILIDAD SEGUN LA INTERACCION DEL USUARIO CON LA APLICACION.
        cardBottom.setVisibility(View.INVISIBLE);

        //TODO - Esconder el fragment
        //MEDIANTE LA PROPIEDAD ADDONSCROLLLISTENER DEL RECYCLER VIEW PODREMOS DETECTAR CUANDO EL USUARIO DESPLAZA EL RECYCLERVIEW
        //Y DE ESA MANERA ESCONDER NUESTRO FRAGMENT (INVISIBLE.)
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                cardBottom.setVisibility(View.INVISIBLE);
            }
        });

        //AGENCIAMOS A NUESTRO RECYCLERVIEW EL LAYOUTMANAGER
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    }

    private void setListAdapter(){

        //NUESTRO OBJETO DE CONTACTOS OBTIENE INFORMACIÓN DEL CONTACTPROVIDER
        myContacts = new MyContacts(this);

        //AGENCIAMOS EL ADAPTER A NUESTRO RECYCLER VIEW Y UTILIZAMOS EL LISTENER PARA QUE DETECTE EL CLICK E INTERACTUE CON DICHO CONTACTO
        recyclerView.setAdapter(new MyAdapter(myContacts, this));

        //COMPROBAMOS QUE NUESTRA LISTA DE CONTACTOS NO ESTÉ VACÍA Y DE ESTA MANERA PONEMOS INVISIBLE NUESTRO TEXTO DE "EMPTY LIST"
        if (myContacts.getCount() > 0)findViewById(R.id.tvEmptyList).setVisibility(View.INVISIBLE);
    }

    private Boolean checkPermissions(){

        //COMPROBAMOS QUE HEMOS OBTENIDO LOS PERMISOS DE ACCESO A LOS CONTACTOS
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            //DESPLIEGA LA SOLICITUD DE PERMISOS PARA QUE EL USUARIO LOS ACEPTE O NO.
            ActivityCompat.requestPermissions(this, MainActivity.PERMISSIONS_CONTACTS, MainActivity.REQUEST_CONTACTS);
            return false;
        }else
            return true;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){

        //COMPROBAMOS QUE EL IDENTIFICADOR QUE OBTENEMOS SEA EL MISMO QUE LE HEMOS AGENCIADO A LA SOLICITUD DE ACCESO A LOS CONTACTOS
        if (requestCode == REQUEST_CONTACTS){
            //SOLO NECESITAMOS COMPROBAR LA PRIMERA POSICION DEL ARRAY YA QUE SOLO TENEMOS UNA SOLICITUD DE PERMISOS DE LECTURA
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                //EN CASO DE QUE ASI SEA LLAMAREMOS AL METODO PARA OBTENER LA INFORMACION DE LOS CONTACTOS
                setListAdapter();
            else    //SI NO, MOSTRAREMOS UN TOAST QUE NOS AVISARA DE QUE NECESITAMOS ACEPTAR PARA QUE LA APLICACION PUEDA FUNCIONAR Y SEA UTIL.
                Toast.makeText(this, getString(R.string.contacts_read_right_required), Toast.LENGTH_LONG).show();
        } else
            super.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }




    @Override
    public void onContactClick(ContactItem contact) {

        //TODO - Generar el cardView del fragment
        //UNA VEZ CLICAMOS AL CONTACTO, LLEGAMOS A ESTE METODO CONDE CREAREMOS UN OBJETO FRAGMENT
        //DONDE GENERAREMOS UNA INSTANCIA DE NUESTRA CLASE "CONTACTDETAILACTIVITY", LA CUAL REPRESENTA A DICHO FRAGMENT
        //Y AL OBJETO QUE RECIBIMOS MEDIANTE EL CLICK LE AGENCIAREMOS LOS DATOS RECOGIDOS, COMO NOMBRE, NUMERO, ETC...
        Fragment contactDetail  = ContactDetailActivity.newInstance(contact.getName(), contact.getNumber(), contact.getPhoneType(), contact.getPhoto(),contact.getId(), contact.getContactId(), contact.getRaw(), contact.getLookup());

        //ESTE FRAGMENTO DE CODIGO LO UTILIZAMOS PARA GESTIONAR DICHO FRAGMENT
        getSupportFragmentManager().beginTransaction()
                //LO MOSTRAMOS
                .show(contactDetail)
                //AÑADIMOS UNA PEQUEÑA ANIMACION AL CONTENIDO INTERIOR
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                //PERMITIMOS OPTIMIZAR LAS OPERACIONES
                .setReorderingAllowed(true)
                //PERMITIMOS QUE EL USUARIO, MEDIANTE CLICK EN EL BOTON DE BACK, PUEDA VOLVER AL CONTENIDO QUE SE MOSTRABA EN EL FRAGMENT PREVIAMENTE.
                .addToBackStack(null)
                //REEMPLAZAMOS EL CONTENIDO DEL LAYOUT POR EL QUE OBTENEMOS DEL CONTACTO
                .replace(R.id.fragmentContainerView, contactDetail, null)
                //FINALIZAMOS LA GESTION DEL FRAGMENT
                .commit();

        //Y MOSTRAMOS DICHO FRAGMENT
        cardBottom.setVisibility(View.VISIBLE);

    }

    //TODO - Holdear contacto para verlo desde la app de contacts
    //GESTIONAMOS LA PULSACION MANTENIDA.
    @Override
    public boolean onContactHold(ContactItem contact) {
        //CREAMOS UN OBJETO INTENT QUE NOS LLEVARÁ A LA APLICACION DE CONTACTOS, Y DIRECTAMENTE AL CONTACTO QUE HEMOS SELECCIONADO GRACIAS A IDENTIFICARLO MEDIANTE SU ID Y SU LOOKUPKEY
        Intent intent = new Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.getLookupUri(Long.parseLong(contact.getId()), contact.getLookup()));
        this.startActivity(intent);
        return true;
    }
}