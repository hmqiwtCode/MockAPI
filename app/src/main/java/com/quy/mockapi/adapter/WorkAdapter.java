package com.quy.mockapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quy.mockapi.R;
import com.quy.mockapi.entity.Work;

import java.util.List;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.WorkItem> {

    private List<Work> arrWork;
    private Context context;


    public WorkAdapter(List<Work> arrWork, Context context) {
        this.arrWork = arrWork;
        this.context = context;
    }

    @NonNull
    @Override
    public WorkItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WorkItem(LayoutInflater.from(context).inflate(R.layout.work_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull WorkItem holder, int position) {
        Work work = arrWork.get(position);
        holder.lblHeader.setText(work.getName());
        holder.lblDateCreate.setText(work.getCreate()+"");
        holder.switch1.setChecked(work.isComplete());

    }

    @Override
    public int getItemCount() {
        return arrWork.size();
    }

    public class WorkItem extends RecyclerView.ViewHolder{
        private TextView lblHeader;
        private TextView lblDateCreate;
        private Switch switch1;

        public WorkItem(@NonNull View itemView) {
            super(itemView);
            lblHeader = itemView.findViewById(R.id.lblHeader);
            lblDateCreate = itemView.findViewById(R.id.lblDateCreate);
            switch1 = itemView.findViewById(R.id.switch1);
        }
    }
}
