package com.example.martinruiz.snapnotes.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.martinruiz.snapnotes.DatabaseModel.Notes;
import com.example.martinruiz.snapnotes.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MartinRuiz on 11/20/2017.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private List<Notes> notes;
    private int layoutReference;
    private OnItemClickListener onItemClickListener;
    private Activity activity;


    public GalleryAdapter(List<Notes> notes, int layoutReference, Activity activity, OnItemClickListener onItemClickListener) {
        this.notes = notes;
        this.layoutReference = layoutReference;
        this.activity = activity;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(activity).inflate(layoutReference,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GalleryAdapter.ViewHolder holder, int position) {
        holder.bind(notes.get(position),  onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imageViewGalleryPhoto) ImageView imageViewGalleryPhoto;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(final Notes note, final OnItemClickListener onItemClickListener){
            Glide.with(activity).load(note.getUrl())
                    .into(imageViewGalleryPhoto);
            imageViewGalleryPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(note, getAdapterPosition());
                }
            });


        }

    }

    public interface OnItemClickListener {
        void onItemClick(Notes note, int position);
    }
}
