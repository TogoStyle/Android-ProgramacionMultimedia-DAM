package dam.androidantoniovr.u4t6contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private MyContacts myContacts;

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView id, name, number;
        ImageView img;

        public MyViewHolder(View view){
            super(view);
            this.id = view.findViewById(R.id.userId);
            this.name = view.findViewById(R.id.userName);
            this.number = view.findViewById(R.id.userNumber);
            this.img = view.findViewById(R.id.userPhoto);
        }
    }

    MyAdapter(MyContacts myContacts){
        this.myContacts = myContacts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View tv = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_cards, parent, false);

        return new MyViewHolder(tv);
    }

    //TODO - Código necesario para mostar en cada CardView la información necesaria de cada contacto
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position){

        ContactItem contact = myContacts.getContactData(position);
        viewHolder.id.setText(contact.getId());
        viewHolder.name.setText(contact.getName());
        viewHolder.number.setText(contact.getNumber());

        if (contact.getPhoto() != null) {
            viewHolder.img.setImageURI(contact.getPhoto());
        } else viewHolder.img.setImageResource(R.mipmap.ic_launcher_round);

    }

    @Override
    public int getItemCount(){
        return myContacts.getCount();
    }
}
