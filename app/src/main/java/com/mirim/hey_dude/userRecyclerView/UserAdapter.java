package com.mirim.hey_dude.userRecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hey_dude.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.mirim.hey_dude.FCM.FcmNotificationsSender;

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
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                DialogShow(activity);
            }
        });

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

                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    // dialog출력
    void DialogShow(AppCompatActivity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setIcon(R.drawable.alarm_icon);
        builder.setTitle(" ");
        builder.setMessage("김하진" + "님에게"+"\n모닝콜을 부탁하시겠습니까?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FcmNotificationsSender notificationsSender = new FcmNotificationsSender(
                        "ffwJXRKXRXC-pxoD7OxEDs:APA91bFmu8-XZ-wPSZ0S1gTB9r2YuKZLm7qOs3myk5_0CaN6sxc217Md635oIOVJXsnhrpaqbow9qhPnrIe_VpFKliKWJ5eMc0GRcx4quTSxtqXO13xpbKOn-b9nBmSiu5DcwHDh1mC_",
                        "ㅎㅇ",
                        "ㅎㅇ",
                        activity.getApplicationContext(),
                        activity);
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }


}
