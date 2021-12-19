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

public class ContactDetailActivity extends Fragment {


    private static final String NAME = "name";
    private static final String NUMBER = "number";
    private static final String TYPE = "type";
    private static final String IMG = "img";
    private static final String ID = "";
    private static final String CONTACTID = "contactId";
    private static final String RAW = "raw";
    private static final String KEY = "lkey";

    private String name;
    private String number;
    private String phoneType;
    private URI img;
    private String id;
    private String contactId;
    private String raw;
    private String lookupKey;

    public ContactDetailActivity(){

    }


    public static ContactDetailActivity newInstance(String name, String number, String phoneType, Uri img,String id, String contactId, String raw, String lookupKey) {

        ContactDetailActivity fragment = new ContactDetailActivity();
        Bundle data = new Bundle();

        data.putString(NAME, name);
        data.putString(NUMBER, number);
        data.putString(TYPE, phoneType);
        data.putString(IMG, String.valueOf(img));
        data.putString(ID, id);
        data.putString(CONTACTID, contactId);
        data.putString(RAW, raw);
        data.putString(KEY, lookupKey);
        fragment.setArguments(data);

        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.bottom_activity_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView contactName = view.findViewById(R.id.contactName);
        contactName.setText(name);

        TextView contactNumber = view.findViewById(R.id.contactNumber);
        contactNumber.setText(number);

        TextView type = view.findViewById(R.id.phoneType);
        type.setText(phoneType);

        ImageView imgContact = view.findViewById(R.id.photo);
        imgContact.setImageURI(Uri.parse(String.valueOf(img)));

        TextView idd = view.findViewById(R.id._ID);
        idd.setText(id);

        TextView contact = view.findViewById(R.id.contactID);
        contact.setText(contactId);

        TextView rawId = view.findViewById(R.id.rawContact);
        rawId.setText(raw);

        TextView key = view.findViewById(R.id.lookupKey);
        key.setText(lookupKey);


    }

}
