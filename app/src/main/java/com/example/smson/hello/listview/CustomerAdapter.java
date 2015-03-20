package com.example.smson.hello.listview;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smson.hello.R;

import java.util.ArrayList;

/**
 * Created by junsuk on 15. 3. 19..
 */
public class CustomerAdapter extends ArrayAdapter<proFile> {

    // ViewHolder 패턴
    static class ViewHolder {
        private TextView labelText;
        private ImageView mIcon;
        private TextView mName;
        private TextView mAddress;
        private TextView mTel;
    }

    // Layout을 가져오기 위한 객체
    private LayoutInflater inflater;

    public CustomerAdapter(Context context, int resource, ArrayList<proFile> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("CustomAdapter", "position : " + position);

        ViewHolder holder;
        View view = convertView;


        if (view == null) {
            // View 를 처음 로딩할 때, Data 를 처음 셋팅할 때
            inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.simple_list_item_2, null);
            TextView label = (TextView) view.findViewById(R.id.xTextLine1);
            ImageView viewIcon = (ImageView) view.findViewById(R.id.list_item_image);
            TextView viewName = (TextView) view.findViewById(R.id.xTextLine1);
            TextView viewAddress = (TextView) view.findViewById(R.id.xTextLine2);
            TextView viewTel = (TextView) view.findViewById(R.id.xTextLine3);


            holder = new ViewHolder();

            holder.mIcon = viewIcon;
            holder.mName = viewName;
            holder.mAddress = viewAddress;
            holder.mTel = viewTel;

            view.setTag(holder);
        } else {
            // View, Data 재사용
            holder = (ViewHolder) view.getTag();
        }


        proFile profile = getItem(position);
        int icon = profile.getPhoto();
        String name = (String) profile.getName();
        String address = (String) profile.getAddress().toString();
        String tel = (String) profile.getTel();

        if (!TextUtils.isEmpty(name)) {
            holder.mName.setText(name);
        }
        if (!TextUtils.isEmpty(address)) {
            holder.mAddress.setText(address);
        }
        if (!TextUtils.isEmpty(tel)) {
            holder.mTel.setText(tel);
        }

        // position 위치의 데이터를 취득
//        String name = getItem(position);
//        if (!TextUtils.isEmpty(name.toString())) {
//            holder.labelText.setText(name.toString());
//        }

        // 홀수, 짝수 줄 배경색 변경
//        if (position % 2 == 0) {
//            holder.labelText.setBackgroundColor(Color.parseColor("#FFFFFF"));
//        } else {
//            holder.labelText.setBackgroundColor(Color.parseColor("#CCCCCC"));
//        }

        // 애니메이션 적용
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.sample_ani);
        view.startAnimation(animation);

        // 완성된 View return
        return view;
    }

}