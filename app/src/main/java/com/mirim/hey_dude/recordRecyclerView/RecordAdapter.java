package com.mirim.hey_dude.recordRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hey_dude.R;

import java.util.ArrayList;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder>{

    private ArrayList<RecordItem> mRecordList = null;

    ///////////////////////////// onclick event
    public interface OnItemClickEventListener {
        void onItemClick(View a_view, int a_position);
    }

    private OnItemClickEventListener mItemClickListener;

    public void setOnItemClickListener(OnItemClickEventListener a_listener) {
        mItemClickListener = a_listener;
    }
    ///////////////////////////// onclick event

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

    public RecordAdapter(ArrayList<RecordItem> mRecordList) {
        this.mRecordList = mRecordList;
    }

    @NonNull
    @Override
    public RecordAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_record_recyclerview, parent, false);
        RecordAdapter.ViewHolder vh = new RecordAdapter.ViewHolder(view);
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
