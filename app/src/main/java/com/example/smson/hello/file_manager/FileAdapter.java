package com.example.smson.hello.file_manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smson.hello.R;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by sonsangmun on 2015-05-06.
 */
public class FileAdapter extends BaseAdapter{
    private List<File> mData;
    private Context mContext;

    SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy.MM.dd a KK:mm");
    DecimalFormat mDecimalFormat = new DecimalFormat("#,###");

    public FileAdapter(Context context, List<File> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            // View 를 처음 로딩할 때, Data 를 처음 셋팅할 때
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_file_list, null);
            TextView filename = (TextView) convertView.findViewById(R.id.tv_filename);
            TextView filesize = (TextView) convertView.findViewById(R.id.tv_filesize);
            TextView modified = (TextView) convertView.findViewById(R.id.tv_modified);

            holder = new ViewHolder();
            holder.fileName = filename;
            holder.fileSize = filesize;
            holder.modified = modified;

            convertView.setTag(holder);
        } else {
            // View, Data 재사용
            holder = (ViewHolder) convertView.getTag();
        }

        // position 위치의 데이터를 취득
        File file = (File) getItem(position);

        holder.fileName.setText(file.getName());

        // 디렉토리인지 아닌지
        if (file.isDirectory()) {
            holder.fileSize.setText("<dir>");
        } else {
            long size = file.length() / 1024;
            holder.fileSize.setText(mDecimalFormat.format(size) + "kb");
        }

        holder.modified.setText(mDateFormat.format(new Date(file.lastModified())));

        // 완성된 View return
        return convertView;
    }

    static class ViewHolder {
        TextView fileName;
        TextView fileSize;
        TextView modified;
    }
}
