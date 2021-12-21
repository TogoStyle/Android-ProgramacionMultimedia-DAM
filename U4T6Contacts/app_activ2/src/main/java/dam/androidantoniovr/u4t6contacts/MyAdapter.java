package dam.androidantoniovr.u4t6contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    //INTERFAZ PARA UTILIZAR EL CLICK O EL CLICK MANTENIDO
    public interface OnContactClickListener {
        //METODO PARA UTILIZAR EL CLICK SEGUN EL CONTACTO EN EL RECYCLERVIEW
        void onContactClick(ContactItem contact);
        //METODO PARA UTILIZAR EL CLICK MANTENIDO SEGUN EL CONTACTO
        boolean onContactHold(ContactItem contact);
        
    }

    private MyContacts myContacts;
    private MyAdapter.OnContactClickListener listener;

    //CLASE PARA CADA ITEM/CONTACTO
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, number;
        View view;
        ImageView img;

        //CONSTRUCTOS DE LA CLASE JUNTO AL VIEW QUE RECIBE COMO PARÁMETRO
        public MyViewHolder(View view) {
            super(view);
            this.id = view.findViewById(R.id.userId);
            this.name = view.findViewById(R.id.userName);
            this.number = view.findViewById(R.id.userNumber);
            this.img = view.findViewById(R.id.userPhoto);

            this.view = view;
        }

        //LE DA A LA VISTA O ITEM SELECCIONADO LOS DATOS/ATRIBUTOS CORRESPONDIENTES
        public void bind(ContactItem item, OnContactClickListener listener) {

            this.id.setText(item.getId());
            this.name.setText(item.getName());
            this.number.setText(item.getNumber());

            //COMPROBAMOS SI EL CONTACTO TIENE IMAGEN O NO, EN CASO DE QUE NO
            //UTILIZAMOS UNA IMAGEN POR DEFECTO
            if (item.getPhoto() != null) {
                this.img.setImageURI(item.getPhoto());
            } else {
                this.img.setImageResource(R.mipmap.ic_launcher_round);
            }

            //GESTIONAMOS EL LISTENER DEPENDIENDO DE SI EL USUARIO SOLO HACE CLICK (DESPLEGAR EL FRAGMENT)
            this.view.setOnClickListener(v -> listener.onContactClick(item));
            //O HACE UN CLICK MANTENIDO, DE ESTA MANERA SE RECURRE A DIFERENTES METODOS (REALIZAR EL INTENT DE DICHO CONTACTO A LA APP DE CONTACTOS)
            this.view.setOnLongClickListener(v -> listener.onContactHold(item));
        }
    }

    //CONSTRUCTOR DE LA CLASE
    MyAdapter(MyContacts myContacts, OnContactClickListener listener ) {
        this.myContacts = myContacts;
        this.listener = listener;
    }

    //CREAMOS EL NUEVO ITEM (VIEW) ESTE MÉTODO ES RECURRIDO POR EL LAYOUT MANAGER
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

    //CONTAMOS LOS CONTACTOS QUE TENEMOS EN LA LISTA.
    @Override
    public int getItemCount() {
        return myContacts.getCount();
    }
}
