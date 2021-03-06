package com.example.smson.hello.database.dbtest1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smson.hello.R;

import java.util.ArrayList;

/**
 * Created by sangmun on 2015-04-08.
 */
public class CustomDbAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<InfoClass> infoList;
    private ViewHolder viewHolder;

    public CustomDbAdapter(Context c , ArrayList<InfoClass> array) {
        inflater = LayoutInflater.from(c);
        infoList = array;
    }

    @Override
    public int getCount() {
        return infoList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {

        View v = convertview;

        if(v == null){
            viewHolder = new ViewHolder();
            v = inflater.inflate(R.layout.db_list_row, null);
            viewHolder.name = (TextView)v.findViewById(R.id.tv_name);
            viewHolder.contact = (TextView)v.findViewById(R.id.tv_contact);
            viewHolder.email = (TextView)v.findViewById(R.id.tv_email);
            v.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder)v.getTag();
        }

        viewHolder.name.setText(infoList.get(position).name);
        viewHolder.contact.setText(infoList.get(position).contact);
        viewHolder.email.setText(infoList.get(position).email);

        return v;
    }

    public void setArrayList(ArrayList<InfoClass> arrays){
        this.infoList = arrays;
    }

    public ArrayList<InfoClass> getArrayList(){
        return infoList;
    }


    /*
     * ViewHolder
     */
    class ViewHolder{
        TextView name;
        TextView contact;
        TextView email;
    }
}
