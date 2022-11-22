package com.mirim.hey_dude.userRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hey_dude.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    OnItemClickListener listener;

    private ArrayList<UserItem> arrayList;
    private Context context;

    // ---------------------click이벤트 구현을 위한 코드---------------------
    public interface OnItemClickListener {
        void OnItemClicked(int position, String data);
    }
    //OnItemClickListener 참조 변수 선언
    private UserAdapter.OnItemClickListener itemClickListener;

    //OnItemClickListener 전달 메소드
    public void setOnItemClickListener(UserAdapter.OnItemClickListener listener){
        itemClickListener = listener;
    }
    // ---------------------click이벤트 구현을 위한 코드---------------------

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

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null){
                        String data = "";
                        data = "";
                        itemClickListener.OnItemClicked(position, data);
                    }
                }
            });
        }
    }

//    public interface OnItemClickListener{
//        void onItemClick(DocumentSnapshot documentSnapshot, int position);
//    }
//
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        this.listener = listener;
//    }


}
