package dam.androidantoniovr.u4t6contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnContactClickListener{
    MyContacts myContacts;
    RecyclerView recyclerView;
    FragmentContainerView cardBottom;

    private static String[] PERMISSIONS_CONTACTS = {Manifest.permission.READ_CONTACTS};

    private static final int REQUEST_CONTACTS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setUI();

        if (checkPermissions())
        setListAdapter();
    }

    private void setUI(){
        recyclerView = findViewById(R.id.recyclerViewContacts);
        recyclerView.setHasFixedSize(true);
        cardBottom = findViewById(R.id.fragmentContainerView);
        cardBottom.setVisibility(View.INVISIBLE);

        //TODO - Esconder el fragment
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                cardBottom.setVisibility(View.INVISIBLE);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    }

    private void setListAdapter(){

        myContacts = new MyContacts(this);

        recyclerView.setAdapter(new MyAdapter(myContacts, this));

        if (myContacts.getCount() > 0)findViewById(R.id.tvEmptyList).setVisibility(View.INVISIBLE);
    }

    private Boolean checkPermissions(){

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, MainActivity.PERMISSIONS_CONTACTS, MainActivity.REQUEST_CONTACTS);
            return false;
        }else
            return true;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){

        if (requestCode == REQUEST_CONTACTS){

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                setListAdapter();
            else
                Toast.makeText(this, getString(R.string.contacts_read_right_required), Toast.LENGTH_LONG).show();
        } else
            super.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }




    @Override
    public void onContactClick(ContactItem contact) {

        //TODO - Generar el cardView del fragment
        Fragment contactDetail  = ContactDetailActivity.newInstance(contact.getName(), contact.getNumber(), contact.getPhoneType(), contact.getPhoto(),contact.getId(), contact.getContactId(), contact.getRaw(), contact.getLookup());

        getSupportFragmentManager().beginTransaction()
                .show(contactDetail)
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .replace(R.id.fragmentContainerView, contactDetail, null)
                .commit();

        cardBottom.setVisibility(View.VISIBLE);

    }

}