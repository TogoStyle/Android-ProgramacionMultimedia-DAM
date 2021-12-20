package dam.androidantoniovr.u4t6contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public interface OnContactClickListener {
        void onContactClick(ContactItem contact);
        boolean onContactHold(ContactItem contact);
        
    }


    private MyContacts myContacts;
    private MyAdapter.OnContactClickListener listener;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, number;
        View view;
        ImageView img;

        public MyViewHolder(View view) {
            super(view);
            this.id = view.findViewById(R.id.userId);
            this.name = view.findViewById(R.id.userName);
            this.number = view.findViewById(R.id.userNumber);
            this.img = view.findViewById(R.id.userPhoto);

            this.view = view;
        }

        public void bind(ContactItem item, OnContactClickListener listener) {

            this.id.setText(item.getId());
            this.name.setText(item.getName());
            this.number.setText(item.getNumber());

            if (item.getPhoto() != null) {
                this.img.setImageURI(item.getPhoto());
            } else {
                this.img.setImageResource(R.mipmap.ic_launcher_round);
            }

            this.view.setOnClickListener(v -> listener.onContactClick(item));
            this.view.setOnLongClickListener(v -> listener.onContactHold(item));
        }
    }

    MyAdapter(MyContacts myContacts, OnContactClickListener listener ) {
        this.myContacts = myContacts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tv = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_cards, parent, false);

        return new MyViewHolder(tv);
    }

    //TODO - Código necesario para mostar en cada CardView la información necesaria de cada contacto
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {

        viewHolder.bind(myContacts.getContactData(position), listener);


    }

    @Override
    public int getItemCount() {
        return myContacts.getCount();
    }
}
