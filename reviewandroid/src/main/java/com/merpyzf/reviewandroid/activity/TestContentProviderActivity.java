package com.merpyzf.reviewandroid.activity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.merpyzf.reviewandroid.R;

public class TestContentProviderActivity extends AppCompatActivity {

    private EditText edt_park;
    private TextView tv_show_usable;
    private Button btn_search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_content_provider);

        edt_park = (EditText) findViewById(R.id.edt_park);
        tv_show_usable = (TextView)findViewById(R.id.tv_show_usable);
        btn_search = (Button)findViewById(R.id.btn_serach);



        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("content://com.merpyzf.parkdemo/query");

                String parkName = edt_park.getText().toString().trim();

                Cursor cursor = getContentResolver().query(uri, null, null, new String[]{parkName}, null);


                if(cursor!=null && cursor.getCount()>0){

                    cursor.moveToNext();

                    String usable = cursor.getString(0);


                    tv_show_usable.setText("可用空闲车位:"+usable);

                }

            }
        });




    }
}
