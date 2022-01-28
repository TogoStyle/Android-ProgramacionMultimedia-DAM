package dam.androidantoniovr.u4t8database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dam.androidantoniovr.u4t8database.data.TodoListDBManager;
import dam.androidantoniovr.u4t8database.model.Task;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    //TODO - Interfaz para acceder al onClick e identificar a la Task correspondiente en el recyclerView.
    public interface OnTaskClickListener{
        void onTaskClick(Task task);
    }

    private TodoListDBManager todoListDBManager;
    private ArrayList<Task> myTaskList;
    private MyAdapter.OnTaskClickListener listener;


    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvId;
        TextView tvTodo;
        TextView tvToAccomplish;
        TextView tvDescription;
        TextView idPriority;
        TextView idStatus;
        View view;

        public MyViewHolder(View view){
            super(view);

            this.tvId = view.findViewById(R.id.tvId);
            this.tvTodo = view.findViewById(R.id.tvTodo);
            this.tvToAccomplish = view.findViewById(R.id.tvToAccomplish);
            this.tvDescription = view.findViewById(R.id.tvDescription);
            this.idPriority = view.findViewById(R.id.idPriority);
            this.idStatus = view.findViewById(R.id.idStatus);

            this.view = view;
        }

        public void bind (Task task, OnTaskClickListener listener){
            this.tvId.setText(String.valueOf(task.get_id()));
            this.tvTodo.setText(task.getTodo());
            this.tvToAccomplish.setText(task.getToAccomplish());
            this.tvDescription.setText(task.getDescription());
            this.idPriority.setText(task.getPriority());
            this.idStatus.setText(task.getStatus());

            this.view.setOnClickListener(v -> listener.onTaskClick(task));
        }
    }

    public MyAdapter(TodoListDBManager todoListDBManager, OnTaskClickListener listener){
        this.todoListDBManager = todoListDBManager;
        this.listener = listener;
    }

    //TODO - métodos encargados de mostrar desde el adapter la opcion de filtrado seleccionada.
    public void getData(){
        this.myTaskList = todoListDBManager.getTasks();
        notifyDataSetChanged();
    }

    public void notStarted(){
       this.myTaskList = todoListDBManager.notStarted();
       notifyDataSetChanged();
    }
    public void completed(){
        this.myTaskList = todoListDBManager.completed();
        notifyDataSetChanged();
    }

    public void inProgress(){
        this.myTaskList = todoListDBManager.inProgress();
        notifyDataSetChanged();
    }
    public void deleteCompleted(){
        todoListDBManager.deleteCompleted();
        getData();
        notifyDataSetChanged();
    }

    public void deleteAll(){
        todoListDBManager.dropTable();
        getData();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(itemLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(myTaskList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return myTaskList.size();
    }

}
