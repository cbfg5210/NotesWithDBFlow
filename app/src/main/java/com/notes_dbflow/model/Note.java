package com.notes_dbflow.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * 在此写用途
 * Created by hawk on 2016/5/6.
 */
@Table(database = AppDatabase.class)
public class Note extends BaseModel implements Parcelable {
    @PrimaryKey(autoincrement = true)
    long id; // package-private recommended, not required
    @Column
    private String content;
    @Column
    private long time;

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.content);
        dest.writeLong(this.time);
    }

    public Note() {
    }

    protected Note(Parcel in) {
        this.id = in.readLong();
        this.content = in.readString();
        this.time = in.readLong();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
