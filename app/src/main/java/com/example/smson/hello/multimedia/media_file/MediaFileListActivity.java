package com.example.smson.hello.multimedia.media_file;

import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.smson.hello.R;

public class MediaFileListActivity extends Activity {

    private TextView textTargetUri, textTargetPath1, textTargetPath2;
    private String TAG = MediaFileListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_file_list);

        Button buttonLoadImage = (Button)findViewById(R.id.loadimage);
        textTargetUri = (TextView)findViewById(R.id.targeturi);
        textTargetPath1 = (TextView)findViewById(R.id.targetpath1);
        textTargetPath2 = (TextView)findViewById(R.id.targetpath2);

        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                Uri url = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                Log.d(TAG, url.toString());
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Uri targetUri = data.getData();
            textTargetUri.setText("Uri: " + targetUri.toString());
            textTargetPath1.setText("path: " + getPathFromUri_managedQuery(targetUri));
            textTargetPath2.setText("path: " + getPathFromUri_CursorLoader(targetUri));
        }
    }

    //using deprecated managedQuery() method
    private String getPathFromUri_managedQuery(Uri uri){
        String [] projection = {MediaStore.Images.Media.DATA};

        Cursor cursor = managedQuery(
                uri,
                projection,
                null,   //selection
                null,   //selectionArgs
                null   //sortOrder
        );

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        return cursor.getString(column_index);
    }

    //using CursorLoader() method for API level 11 or higher
    private String getPathFromUri_CursorLoader(Uri uri){

        String [] projection = {MediaStore.Images.Media.DATA};

        CursorLoader cursorLoader = new CursorLoader(
                getApplicationContext(),
                uri,
                projection,
                null,   //selection
                null,   //selectionArgs
                null   //sortOrder
        );

        Cursor cursor = cursorLoader.loadInBackground();

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        return cursor.getString(column_index);
    }
}
