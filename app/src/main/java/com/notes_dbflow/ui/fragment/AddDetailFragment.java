package com.notes_dbflow.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.notes_dbflow.R;
import com.notes_dbflow.model.Note;
import com.notes_dbflow.util.ToastUtil;

import java.text.SimpleDateFormat;

public class AddDetailFragment extends BaseFragment {
    private EditText fadl_content;
    private String flag;
    private Note theNote;
    private SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(null, null, null);
        setHomeBackEnable(true);
        View layoutView = inflater.inflate(R.layout.fragment_adddetail, container, false);
        fadl_content = getView(layoutView, R.id.fadl_content);

        Bundle arguments = getArguments();
        flag = arguments.getString("flag");
        if (flag.equals("detail")) {
            theNote = arguments.getParcelable("theNote");
            setTitle(dateFormat.format(theNote.getTime()));
            fadl_content.setText(theNote.getContent());
        } else {
            setTitle("" + System.currentTimeMillis());
        }

        return layoutView;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_right).setTitle("保存");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            onBackPressed();
        } else if (itemId == R.id.action_right) {
            String content = fadl_content.getText().toString().trim();
            if (TextUtils.isEmpty(content)) {
                ToastUtil.show("便签内容不能为空哦");
                return true;
            }
            if (flag.equals("add")) {
                //添加新书签
                theNote = new Note();
            }
            theNote.setContent(content);
            theNote.setTime(System.currentTimeMillis());
            theNote.save();
            onBackPressed();
        }
        return true;
    }
}

