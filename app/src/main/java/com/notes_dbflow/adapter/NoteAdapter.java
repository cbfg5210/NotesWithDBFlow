package com.notes_dbflow.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.notes_dbflow.R;
import com.notes_dbflow.model.Note;
import com.notes_dbflow.util.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.List;

/**
    * 在此写用途
    * Created by hawk on 2016/5/6.
            */
    public class NoteAdapter extends BaseListAdapter<Note>{
        private SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        public NoteAdapter(Context ctx, List<Note> list) {
            super(ctx, list);
        }
        @Override
    public View bindView(int position, View layoutView, ViewGroup viewGroup) {
        if(null==layoutView){
            layoutView=mInflater.inflate(R.layout.item_note,null);
        }
        TextView inoe_summary= ViewHolder.getView(layoutView,R.id.inoe_summary);
        TextView inoe_time= ViewHolder.getView(layoutView,R.id.inoe_time);
        Note item=mList.get(position);
        inoe_summary.setText(item.getContent());
        inoe_time.setText(dateFormat.format(item.getTime()));
        return layoutView;
    }
}
