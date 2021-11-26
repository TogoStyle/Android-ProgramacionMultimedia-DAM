package dam.androidtvr.u3t3listaactivities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dam.androidtvr.u3t3listaactivities.model.Item;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        public interface OnItemClickListener {
            void onItemCLick(Item item);
        }

        private List<Item> items;
        private MyAdapter.OnItemClickListener listener;

        static class MyViewHolder extends RecyclerView.ViewHolder {

            private ImageView img;
            private TextView txtVersion;
            private TextView txtApi;
            private TextView txtNombre;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                this.img = itemView.findViewById(R.id.imageViewLogo);
                this.txtVersion = itemView.findViewById(R.id.version);
                this.txtApi = itemView.findViewById(R.id.api);
                this.txtNombre = itemView.findViewById(R.id.nombre);
            }

            public void bind(Item item, OnItemClickListener listener) {

                this.img.setImageResource(item.getId());
                this.txtVersion.setText("Versión: " + item.getVersion());
                this.txtApi.setText("API: " + item.getNumeroAPI());
                this.txtNombre.setText(item.getNombreVersion());

                this.itemView.setOnClickListener(v -> listener.onItemCLick(item));
            }
        }

        public MyAdapter(List<Item> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
        }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View tv = LayoutInflater.from(parent.getContext()).inflate(R.layout.android_cards, parent, false);

            return new MyViewHolder(tv);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {
                viewHolder.bind(items.get(position), listener);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }


