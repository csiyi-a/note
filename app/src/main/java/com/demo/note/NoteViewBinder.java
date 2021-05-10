package com.demo.note;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import me.drakeet.multitype.ItemViewBinder;

public class NoteViewBinder extends ItemViewBinder<Note, NoteViewBinder.UIViewHolder> {

    private OnItemClick onItemClick;
    public NoteViewBinder(OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    protected UIViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_note, parent, false);
        return new UIViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull UIViewHolder holder, @NonNull Note item) {
        holder.tv_note.setText(item.getContent());
        holder.itemView.setOnClickListener(v -> onItemClick.onItem(item));
    }


    public static class UIViewHolder extends RecyclerView.ViewHolder {

        TextView tv_note;

        UIViewHolder(@NonNull View view) {
            super(view);
            tv_note = view.findViewById(R.id.tv_note);
        }
    }


    public interface OnItemClick{
        void onItem(Note note);
    }
}
