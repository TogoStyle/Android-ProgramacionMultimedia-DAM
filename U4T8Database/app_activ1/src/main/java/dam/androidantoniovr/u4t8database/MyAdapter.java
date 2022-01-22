package dam.androidantoniovr.u4t8database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dam.androidantoniovr.u4t8database.data.TodoListDBManager;
import dam.androidantoniovr.u4t8database.model.Task;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private TodoListDBManager todoListDBManager;
    private ArrayList<Task> myTaskList;


    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvId;
        TextView tvTodo;
        TextView tvToAccomplish;
        TextView tvDescription;
        TextView idPriority;
        TextView idStatus;

        public MyViewHolder(View view){
            super(view);

            this.tvId = view.findViewById(R.id.tvId);
            this.tvTodo = view.findViewById(R.id.tvTodo);
            this.tvToAccomplish = view.findViewById(R.id.tvToAccomplish);
            this.tvDescription = view.findViewById(R.id.tvDescription);
            this.idPriority = view.findViewById(R.id.idPriority);
            this.idStatus = view.findViewById(R.id.idStatus);
        }

        public void bind (Task task){
            this.tvId.setText(String.valueOf(task.get_id()));
            this.tvTodo.setText(task.getTodo());
            this.tvToAccomplish.setText(task.getToAccomplish());
            this.tvDescription.setText(task.getDescription());
            this.idPriority.setText(task.getPriority());
            this.idStatus.setText(task.getStatus());
        }
    }

    public MyAdapter(TodoListDBManager todoListDBManager){
        this.todoListDBManager = todoListDBManager;
    }

    public void getData(){
        this.myTaskList = todoListDBManager.getTasks();
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
        holder.bind(myTaskList.get(position));
    }

    @Override
    public int getItemCount() {
        return myTaskList.size();
    }
}
