package com.mirim.hey_dude.userRecyclerView;

import static com.google.firebase.database.ktx.DatabaseKt.getSnapshots;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hey_dude.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.mirim.hey_dude.Fragment3;
import com.mirim.hey_dude.RecordActivity;
import com.mirim.hey_dude.friendRecyclerView.MyRecyclerAdapter;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    OnItemClickListener listener;

    private ArrayList<UserItem> arrayList;
    private Context context;

    public UserAdapter(ArrayList<UserItem> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_users_recyclerview, parent, false);
        ViewHolder holder = new ViewHolder(view);
        UserAdapter.ViewHolder vh = new UserAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.profileImg);
        holder.userName.setText(arrayList.get(position).getNickname());
        holder.userMess.setText(arrayList.get(position).getMess());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImg;
        TextView userName;
        TextView userMess;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.profileImg = itemView.findViewById(R.id.profileImg);
            this.userName = itemView.findViewById(R.id.userName);
            this.userMess = itemView.findViewById(R.id.userMess);
            this.cardView = itemView.findViewById(R.id.cardView);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    if(position != RecyclerView.NO_POSITION && listener != null){
//                        Log.d("abc", "abc");
//                    }
//                }
//            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}
