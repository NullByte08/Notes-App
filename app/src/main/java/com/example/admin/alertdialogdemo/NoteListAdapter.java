package com.example.admin.alertdialogdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NoteListAdapter extends GenericRecycleViewAdapter<Note> {



    private Context context;
    //    private OnCardClickListner onCardClickListner;
    private ArrayList<Note> arrNotes;

    public NoteListAdapter(Context context, ArrayList<Note> items) {
        super(context, items);
        this.arrNotes = items;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(setItemView(R.layout.item_notes, parent));
    }

    @Override
    public void onBindData(RecyclerView.ViewHolder viewHolder, Note model, final int position) {


        ViewHolder holder = (ViewHolder) viewHolder;
        bindDataToFileds(model, holder);

//        holder.cardview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onCardClickListner != null) {
//                    onCardClickListner.OnCardClicked(v, position);
//                }
//            }
//        });


    }

    private void bindDataToFileds(Note model, ViewHolder holder) {

        holder.txtNoteName.setText(model.getTitle());
holder.txtNoteDate.setText(model.getDateTime());


    }


//    public void setOnCardClickListner(OnCardClickListner onCardClickListner) {
//        this.onCardClickListner = onCardClickListner;
//    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtNoteName)
        TextView txtNoteName;
        @BindView(R.id.txtNoteDate)
        TextView txtNoteDate;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}
