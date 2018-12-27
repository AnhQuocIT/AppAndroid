package com.example.anhquoc.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anhquoc.musicapp.Activity.DanhsachbaihatActivity;
import com.example.anhquoc.musicapp.Model.TatcaAlbum;
import com.example.anhquoc.musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllAlbumAdapter extends RecyclerView.Adapter<AllAlbumAdapter.ViewHolder>{
    Context context;
    ArrayList<TatcaAlbum> albumArrayList;

    public AllAlbumAdapter(Context context, ArrayList<TatcaAlbum> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line_all_album, viewGroup, false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        TatcaAlbum tatcaAlbum = albumArrayList.get(position);
        Picasso.with(context).load(tatcaAlbum.getHinhAlbum()).into(viewHolder.imgAllAlbum);
        viewHolder.txtnameallalbum.setText(tatcaAlbum.getTenAlbum());
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgAllAlbum;
        TextView txtnameallalbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAllAlbum = itemView.findViewById(R.id.imageviewallalbum);
            txtnameallalbum = itemView.findViewById(R.id.textviewtenallalbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("album", albumArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
