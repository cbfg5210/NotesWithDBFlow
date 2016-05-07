package com.notes_dbflow.ui.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.notes_dbflow.R;
import com.notes_dbflow.adapter.NoteAdapter;
import com.notes_dbflow.model.Note;
import com.notes_dbflow.model.Note_Table;
import com.notes_dbflow.util.ClickTimeUtil;
import com.notes_dbflow.util.ToastUtil;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment {
        private TextView fman_nonote;
        private NoteAdapter adapter;
        private AlertDialog deleteItemDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(null,null,null);
        setHomeBackEnable(false);
        setTitle("如一便签");
        View layoutView=inflater.inflate(R.layout.fragment_main, container, false);
        fman_nonote=getView(layoutView,R.id.fman_nonote);
        ListView fman_notes=getView(layoutView,R.id.fman_notes);
        adapter=new NoteAdapter(getContext(),null);
        fman_notes.setAdapter(adapter);

        fman_notes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note selection= (Note) parent.getItemAtPosition(position);
                Bundle arguments=new Bundle();
                arguments.putString("flag","detail");
                arguments.putParcelable("theNote",selection);
                startFragment(AddDetailFragment.class.getSimpleName(),arguments);
            }
        });

        fman_notes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Note selection= (Note) parent.getItemAtPosition(position);
                showDeleteItemDialog(selection,position);
                return false;
            }
        });
        fman_nonote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle arguments=new Bundle();
                arguments.putString("flag","add");
                startFragment(AddDetailFragment.class.getSimpleName(),arguments);
            }
        });

        List<Note>notes=getNotes();
        if(null==notes||notes.size()==0){
            fman_nonote.setVisibility(View.VISIBLE);
        }else{
            fman_nonote.setVisibility(View.GONE);
            adapter.setList(getNotes());
        }

        return layoutView;
    }

    private void showDeleteItemDialog(final Note noteToDelete, final int indexInList){
        if(null==deleteItemDialog){
            deleteItemDialog=new AlertDialog.Builder(getContext())
                    .setTitle("确定要删除该项便签吗?")
                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            noteToDelete.delete();
                            adapter.remove(indexInList);
                        }
                    })
                    .create();
        }
        String summary;
        if(noteToDelete.getContent().length()>20){
            summary=noteToDelete.getContent().substring(0,20);
        }else{
            summary=noteToDelete.getContent();
        }
        summary+="...";
        deleteItemDialog.setMessage(summary);
        deleteItemDialog.show();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_right).setTitle("+");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_right){
            Bundle arguments=new Bundle();
            arguments.putString("flag","add");
            startFragment(AddDetailFragment.class.getSimpleName(),arguments);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if(ClickTimeUtil.isRetryTooFast(1000)){
            getMainActivity().finish();
        }else{
            ToastUtil.show("再按一次退出应用");
        }
    }

    private List<Note>getNotes(){
        List<Note>notes=new Select()
                .from(Note.class)
                .orderBy(Note_Table.id,false)
                .queryList();
        return notes;
    }
}
