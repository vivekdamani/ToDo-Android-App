package com.example.to_dolist;

import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_dolist.Adapter.ToDoAdapter;


public class RecyclerViewTouchHelper extends ItemTouchHelper.SimpleCallback {

    private ToDoAdapter adapter;
    public RecyclerViewTouchHelper(ToDoAdapter adapter) {

        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter= adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();
        if(direction==ItemTouchHelper.RIGHT)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(adapter.getContext());
            builder.setTitle("Delete Task");
            builder.setMessage("Are you sure?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.deleteTask(position);

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adapter.notifyItemChanged(position);
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

        }
        else{
            adapter.editItem(position);



        }

    }
}
