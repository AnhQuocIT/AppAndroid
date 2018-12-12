package com.example.anhquoc.musicapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anhquoc.musicapp.Model.Playlist;
import com.example.anhquoc.musicapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Converter;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {


        super(context, resource, objects);
    }

    class ViewHolder{
        TextView txtnameplaylist;
        ImageView imgbackground, imgplaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.line_playlist,null);
            viewHolder = new ViewHolder();
            viewHolder.txtnameplaylist = convertView.findViewById(R.id.textviewnameplaylist);
            viewHolder.imgplaylist = convertView.findViewById(R.id.imageviewplaylist);
            viewHolder.imgbackground = convertView.findViewById(R.id.imageviewbackgroundplaylist);
            convertView.setTag(viewHolder);
        }else {

            viewHolder = (ViewHolder) convertView.getTag();
        }
        Playlist playlist = getItem(position);

        Picasso.with(getContext()).load(playlist.getHinhNen()).into(viewHolder.imgbackground);
        Picasso.with(getContext()).load(playlist.getHinhIcon()).into(viewHolder.imgplaylist);
        viewHolder.txtnameplaylist.setText(playlist.getTen());
        return convertView;
    }
}
