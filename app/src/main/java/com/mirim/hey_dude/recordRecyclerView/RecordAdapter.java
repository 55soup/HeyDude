package com.mirim.hey_dude.recordRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hey_dude.R;

import java.util.ArrayList;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder>{

    private ArrayList<RecordItem> mRecordList = null;

    public RecordAdapter(ArrayList<RecordItem> mRecordList) {
        this.mRecordList = mRecordList;
    }

    // ---------------------click이벤트 구현을 위한 코드---------------------
    public interface OnItemClickListener {
        void OnItemClicked(int position, String data);
    }
    //OnItemClickListener 참조 변수 선언
    private OnItemClickListener itemClickListener;

    //OnItemClickListener 전달 메소드
    public void setOnItemClickListener(OnItemClickListener listener){
        itemClickListener = listener;
    }
    // ---------------------click이벤트 구현을 위한 코드---------------------

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView alarmTime;
        TextView friendUserName;
        TextView pushAlarmTime;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            alarmTime = (TextView) itemView.findViewById(R.id.txtAlarmTime);
            friendUserName = (TextView) itemView.findViewById(R.id.txtFriendUserName);
            pushAlarmTime = (TextView) itemView.findViewById(R.id.txtMessTime);
        }

        void onBind(RecordItem item) {
            alarmTime.setText(item.getAlarmTime());
            friendUserName.setText(item.getFriendUserName());
            pushAlarmTime.setText(item.getPushAlarmTime());
        }
    }

    @NonNull
    @Override
    public RecordAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_record_recyclerview, parent, false);
        RecordAdapter.ViewHolder vh = new RecordAdapter.ViewHolder(view);

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
        //--------------------- onclick event ---------------------
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.ViewHolder holder, int position) {
        holder.onBind(mRecordList.get(position));
    }

    public void setRecordList(ArrayList<RecordItem> list) {
        this.mRecordList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mRecordList.size();
    }
}
