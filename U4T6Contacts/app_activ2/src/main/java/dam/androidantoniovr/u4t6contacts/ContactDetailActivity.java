package dam.androidantoniovr.u4t6contacts;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.net.URI;

//TODO - Fragment
//CLASE ENCARGADA DEL FRAGMENT, TARJETA QUE APARECERÁ EN LA PARTE BASE DE LA MAIN ACTIVITY
public class ContactDetailActivity extends Fragment {

    //REDACTAMOS CLAVES PARA PODER REFERIRNOS A LOS DIFERENTES ATRIBUTOS DEL CONTACTO POSTERIORMENTE, CUANDO CREEMOS EL BUNDLE DONDE ALMACENAREMOS LA INFORMACIÓN.
    private static final String NAME = "name";
    private static final String NUMBER = "number";
    private static final String TYPE = "type";
    private static final String IMG = "img";
    private static final String ID = "";
    private static final String CONTACTID = "contactId";
    private static final String RAW = "raw";
    private static final String KEY = "lkey";

    //CREAMOS LOS ATRIBUTOS DEL CONTACTO (TODOS ELLOS) QUE VAMOS A RECOGER EN EL FRAGMENT
    private String name;
    private String number;
    private String phoneType;
    private URI img;
    private String id;
    private String contactId;
    private String raw;
    private String lookupKey;

    //CONSTRUCTOR DE LA CLASE
    public ContactDetailActivity(){

    }


    //METODO ENCARGADO DE INSTANCIAR DICHO FRAGMENT CON LA INFORMACIÓN DEL CONTACTO, RECIBIDA COMO PARÁMETROS DEL MÉTODO
    public static ContactDetailActivity newInstance(String name, String number, String phoneType, Uri img,String id, String contactId, String raw, String lookupKey) {

        //CREAMOS UN OBJETO DE LA PROPIA CLASE Y LO LLAMAMOS FRAGMENT
        ContactDetailActivity fragment = new ContactDetailActivity();
        //CREAMOS UN OBJETO BUNDLE PARA ALMACENAR LA INFORMACIÓN DEL CONTACTO SELECCIONADO
        Bundle data = new Bundle();

        //VAMOS AÑADIENDO AL BUNDLE, LA INFORMACIÓN RESPECTIVA A CADA ATRIBUTO, UNO A UNO, IDENTIFICANDO CADA VALOR CON SU RESPECTIVA CLAVE O IDENTIFICADOR
        data.putString(NAME, name);
        data.putString(NUMBER, number);
        data.putString(TYPE, phoneType);
        data.putString(IMG, String.valueOf(img));
        data.putString(ID, id);
        data.putString(CONTACTID, contactId);
        data.putString(RAW, raw);
        data.putString(KEY, lookupKey);
        //AGENCIAMOS AL FRAGMENT DICHA INFORMACIÓN
        fragment.setArguments(data);

        //DEVOLVEMOS EL OBJETO DEL CONTACTO
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //COMPROBAMOS QUE LOS ARGUMENTOS RECOGIDOS NO SEAN NULOS
        //SI ASI ES, DAMOS A CADA VARIABLE EL VALOR RECOGIDO DEL ARGUMENTO SEGÚN LA CLAVE PREVIAMENTE DECLARADA
        if (getArguments() != null) {
            name = getArguments().getString(NAME);
            number = getArguments().getString(NUMBER);
            phoneType = getArguments().getString(TYPE);
            img = URI.create(getArguments().getString(IMG));
            id = getArguments().getString(ID);
            contactId = getArguments().getString(CONTACTID);
            raw = getArguments().getString(RAW);
            lookupKey = getArguments().getString(KEY);
        }
    }

    //METODO PARA RELLENAR O INFLAR NUESTRO FRAGMENT DEL CONTENIDO DESEADO
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //RELLENAMOS NUESTRO CONTAINER DEL LAYOUT DE LA CLASE MAIN  CON LA INFORMACIÓN DEL CONTACTO
        return inflater.inflate(R.layout.bottom_activity_info, container, false);
    }

    //METODO PARA AGENCIAR A CADA ELEMENTO DEL LAYOUT LA INFORMACIÓN DEL CONTACTO
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //IDENTIFICADMOS EL NOMBRE DEL CONTACTO MEDIANTE UNA VARIABLE DE TIPO TEXTVIEW Y SU CORRESPECTIVO ID
        TextView contactName = view.findViewById(R.id.contactName);
        //AGENCIAMOS EL TEXTO CORRESPONDIENTE A DICHO TEXVIEW, EN ESTE CASO EL NOMBRE
        contactName.setText(name);

        //IDENTIFICAMOS EL NUMERO DEL CONTACTO MEDIANTE UNA VARIABLE DE TIPO TEXTVIEW, IGUAL QUE ANTERIORMENTE CON SU CORRESPONDIENTE ID
        TextView contactNumber = view.findViewById(R.id.contactNumber);
        //Y AGENCIAMOS DICHA VARIABLE.
        contactNumber.setText(number);

        //E IREMOS HACIENDO EXACTAMENTE EL MISMO PROCEDIMIENTO CON EL RESTO DE ELEMENTOS DE NUESTRO LAYOUT.
        //EL TIPO DE TELEFONO
        TextView type = view.findViewById(R.id.phoneType);
        type.setText(phoneType);

        //IMAGEN DEL CONTACTO (NO NECESARIA EN EL PDF)
        ImageView imgContact = view.findViewById(R.id.photo);
        imgContact.setImageURI(Uri.parse(String.valueOf(img)));

        //ID
        TextView idd = view.findViewById(R.id._ID);
        idd.setText(id);

        //ID DEL CONTACTO
        TextView contact = view.findViewById(R.id.contactID);
        contact.setText(contactId);

        //RAW
        TextView rawId = view.findViewById(R.id.rawContact);
        rawId.setText(raw);

        //Y FINALMENTE EL LOOKUPKEY
        TextView key = view.findViewById(R.id.lookupKey);
        key.setText(lookupKey);

    }
}
