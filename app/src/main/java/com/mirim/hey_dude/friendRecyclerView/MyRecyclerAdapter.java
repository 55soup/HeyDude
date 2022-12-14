package com.mirim.hey_dude.friendRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hey_dude.R;
import com.mirim.hey_dude.recordRecyclerView.RecordAdapter;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> implements Filterable {

    private ArrayList<FriendItem> mFriendList = null;

    // ---------------------click이벤트 구현을 위한 코드---------------------
    public interface OnItemClickListener {
        void OnItemClicked(int position, String data);
    }
    //OnItemClickListener 참조 변수 선언
    private MyRecyclerAdapter.OnItemClickListener itemClickListener;

    //OnItemClickListener 전달 메소드
    public void setOnItemClickListener(MyRecyclerAdapter.OnItemClickListener listener){
        itemClickListener = listener;
    }
    // ---------------------click이벤트 구현을 위한 코드---------------------

    @Override
    public Filter getFilter() {
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile;
        TextView name;
        TextView today;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profile = (ImageView) itemView.findViewById(R.id.profile);
            name = (TextView) itemView.findViewById(R.id.name);
            today = (TextView) itemView.findViewById(R.id.today);
        }

        void onBind(FriendItem item) {
            profile.setImageResource(R.drawable.ic_baseline_person_add_alt_1_24);
            name.setText(item.getName());
            today.setText(item.getToday());
        }
    }



    public MyRecyclerAdapter(ArrayList<FriendItem> mFriendList) {
        this.mFriendList = mFriendList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_friends_recyclerview, parent, false);
        MyRecyclerAdapter.ViewHolder vh = new MyRecyclerAdapter.ViewHolder(view);
        //---------------------onclick event ---------------------
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "";
                int position = vh.getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    data = vh.getItemId()+"";
                }
                itemClickListener.OnItemClicked(position, data);
            }
        });
        //--------------------- onclick event ---------------------ㄴ
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerAdapter.ViewHolder holder, int position) {
        holder.onBind(mFriendList.get(position));
    }

    public void setFriendList(ArrayList<FriendItem> list) {
        this.mFriendList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mFriendList.size();
    }

    public void filterList(ArrayList<FriendItem> filteredList){
        mFriendList = filteredList;
        notifyDataSetChanged();
    }
}
