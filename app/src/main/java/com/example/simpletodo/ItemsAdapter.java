package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    /* Interface to MainActivity*/
    public interface OnLongClickListener {
        void onItemLongClicked(int position);
    }
    public interface OnClickListener {
        void onItemClicked(int position);
    }

    /*Class Variables*/
    List<String> items;
    OnLongClickListener longClickListener;
    OnClickListener clickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener clickListener) {
        /*
            Construct the ItemsAdapter class and initialize the variable and interface from MainActivity.java
         */
        this.items = items;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
            Use layout inflator to inflate a view.
            Attach the view to a view holder and return it.
         */
        // attachToRoot: false makes it such that the view is separate and we can easily attach it to a viewholder
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent,  false);
        // wrap inside a view holder and return it
        return new ViewHolder(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull  ItemsAdapter.ViewHolder holder, int position) {
        /*
           Bind data to a particular view holder by getting the item from it's position and binding it to the view using the bind function in the ViewHolder class
         */
        // Grab item from it's position
        String item = items.get(position);
        // Bind the item into specified view holder
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        /* Returns the size of the items*/
        return items.size();
    }

    /* View Holder container:
        Provides easy access to views that represent each row of the list
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        // Update the view inside of the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
            // Long click to delete
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // Notify the on listener which position was long pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return false;
                }
            });

            // Click to edit the text
            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked(getAdapterPosition());
                    return;
                }
            });
        }
    }


}
