package dam.androidtvr.u3t3listaactivities;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        public interface OnItemClickListener {
            void onItemCLick(String activityName);
        }

        private String[] myDataSet;
        private MyAdapter.OnItemClickListener listener;

        static class MyViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            public MyViewHolder(TextView textView) {
                super(textView);
                this.textView = textView;
            }

            public void bind(String activityName, OnItemClickListener listener) {
                this.textView.setText(activityName);
                this.textView.setOnClickListener(v -> listener.onItemCLick(textView.getText().toString()));
            }
        }

        MyAdapter(String[] myDataSet, OnItemClickListener listener) {
            this.myDataSet = myDataSet;
            this.listener = listener;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

            return new MyViewHolder(tv);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {
            viewHolder.bind(myDataSet[position], listener);
        }

        @Override
        public int getItemCount() {
            return myDataSet.length;
        }
    }


