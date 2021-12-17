package dam.androidantoniovr.u4t6contacts;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private MyContacts myContacts;

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public MyViewHolder(TextView view){
            super(view);
            this.textView = view;
        }

        public void bind(String contactData){
            this.textView.setText(contactData);
        }
    }

    MyAdapter(MyContacts myContacts){
        this.myContacts = myContacts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        TextView tv = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_cards, parent, false);

        return new MyViewHolder(tv);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position){
        viewHolder.bind(myContacts.getContactData(position));
    }

    @Override
    public int getItemCount(){
        return myContacts.getCount();
    }
}
