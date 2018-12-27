package com.example.anhquoc.musicapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhquoc.musicapp.Activity.PlayNhacActivity;
import com.example.anhquoc.musicapp.Model.Baihat;
import com.example.anhquoc.musicapp.R;
import com.example.anhquoc.musicapp.Service.APIService;
import com.example.anhquoc.musicapp.Service.DataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder>{

    Context context;

    public DanhsachbaihatAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    ArrayList<Baihat> mangbaihat;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_bai_hat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Baihat baihat = mangbaihat.get(position);
        holder.txtcasi.setText(baihat.getCaSi());
        holder.txttenBaiHat.setText(baihat.getTenBaiHat());
        holder.txtindex.setText(position + 1 + "");
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtindex,txttenBaiHat,txtcasi;
        ImageView imgluotthich;
        public ViewHolder(View itemView) {
            super(itemView);
            txtcasi = itemView.findViewById(R.id.textviewtencasi);
            txtindex = itemView.findViewById(R.id.textviewdanhsachindex);
            txttenBaiHat = itemView.findViewById(R.id.textviewtenbaihat);
            imgluotthich = itemView.findViewById(R.id.imageviewluotthinhbaihat);
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService();
                    Call<String> callback = dataService.UpdateLuotThich("1",mangbaihat.get(getPosition()).getIdBaiHat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("OK")){

                                Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
                            }else{

                                Toast.makeText(context, "Bug!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgluotthich.setEnabled(false);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc",mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
