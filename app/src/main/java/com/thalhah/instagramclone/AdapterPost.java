package com.thalhah.instagramclone;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

class AdapterPost extends RecyclerView.Adapter<AdapterPost.PostHolder> implements View.OnClickListener {

    // deklarasi variable data dari home fragment
    private ArrayList<HashMap<String, String>> listData;
    private Context context;

    // penangkap data dari home fragment
    AdapterPost(FragmentActivity activity, ArrayList<HashMap<String, String>> listPost) {
        // data dari home fragment di masukkan ke dalam variable adapter
        listData = listPost;
        context = activity;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // memasang layout item ke dalam adapter
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, viewGroup, false);
        // memasukkan  layout ke dalam viewhlder
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder postHolder, int i) {
        // menampilkan data ke masing2 komponen layout
        postHolder.txtUserName.setText(listData.get(i).get("username"));
        postHolder.txtUserNameCap.setText(listData.get(i).get("username"));
        postHolder.txtCaption.setText(listData.get(i).get("caption"));
        String URL_GAMBAR = "https://ajisetyaserver.000webhostapp.com/SMPIDN/webdatabase/img/";
        Glide.with(context)
                .load(URL_GAMBAR + listData.get(i).get("gambar"))
                .into(postHolder.imgPost);
        Glide.with(context)
                .load(URL_GAMBAR + listData.get(i).get("p_image"))
                .into(postHolder.imgPhotoProfile);
        postHolder.btnLike.setOnClickListener(this);
        postHolder.btnShare.setOnClickListener(this);
        postHolder.btnMore.setOnClickListener(this);
        postHolder.btnFav.setOnClickListener(this);
        postHolder.btnComment.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // aksi klik pada tombol
        Toast.makeText(context, "Kamu menekan tombol", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return listData.size();
        // menentukan jumlah data yang ingin ditampilkan
    }

    class PostHolder extends RecyclerView.ViewHolder {
        // deklarasi variable utk komponen
        TextView txtUserName, txtUserNameCap, txtCaption;
        ImageView imgPhotoProfile, imgPost, btnMore, btnLike, btnComment, btnShare, btnFav;

        PostHolder(@NonNull View itemView) {
            super(itemView);

            // inisialisasi komponen layout
            txtUserName = itemView.findViewById(R.id.txt_username);
            txtUserNameCap = itemView.findViewById(R.id.txt_usernamecap);
            txtCaption = itemView.findViewById(R.id.txt_caption);
            imgPhotoProfile = itemView.findViewById(R.id.img_pp);
            imgPost = itemView.findViewById(R.id.img_post);
            btnLike = itemView.findViewById(R.id.btn_like);
            btnComment = itemView.findViewById(R.id.btn_comment);
            btnFav = itemView.findViewById(R.id.btn_fav);
            btnMore = itemView.findViewById(R.id.btn_more);
            btnShare = itemView.findViewById(R.id.btn_share);
        }
    }
}
