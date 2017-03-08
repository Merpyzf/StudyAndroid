package com.merpyzf.reviewandroid.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.merpyzf.reviewandroid.R;
import com.merpyzf.reviewandroid.utils.SQLiteHelper1;

/**
 *sql语句练习
 */
public class TestSqlActivity extends AppCompatActivity {


    private TextView textViewShow;
    private SQLiteDatabase dbWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_sql);

        textViewShow = (TextView) findViewById(R.id.textView_show);

        //用于创建表结构的sql语句
        String sql = "create table tab_book (_id integer not null primary key autoincrement,book_name varchar(100),book_author varchar(100),add_date timestamp)";

        SQLiteHelper1 sqlHelper = new SQLiteHelper1(this, "book_db_1.db", sql);

        //数据操作对象
        dbWriter = sqlHelper.getWritableDatabase();

        Toast.makeText(this,"数据库创建成功",Toast.LENGTH_SHORT).show();



    }

    /**
     *
     * 向数据库中插入一条数据
     * @param v
     */
   public void createAdd(View v){


       for(int i=0;i<10;i++){


           ContentValues values = new ContentValues();

           values.put("book_name","book"+i);
           values.put("book_author","wangke"+i);

           dbWriter.insert("tab_book",null,values);

       }




    }

    /**
     *
     * 更新数据
     * @param v
     */
    public void Update(View v){


        ContentValues values = new ContentValues();

        values.put("book_name","冰与火之歌");

        dbWriter.update("tab_book",values,"_id = ?",new String[]{"15"});


    }

    /**
     *
     * 删除一条数据
     * @param v
     */
    public void delete(View v){

        for(int i=0;i<10;i++){


            dbWriter.delete("tab_book","_id = ?",new String[]{"16"});

        }

    }


    /**
     *
     * 查询所有的数据
     * @param v
     */
    public void query(View v){


        Cursor cursor = dbWriter.query("tab_book", null, null, null, null, null, "_id ASC");

        StringBuilder builder = new StringBuilder();
        while (cursor.moveToNext()){

            String id  = cursor.getString(0);
            String book_name = cursor.getString(1);
            String book_author =  cursor.getString(2);


            builder.append("id: "+id+"book_name: "+book_name+" book_author: "+book_author+"\n");


        }


        textViewShow.setText(builder.toString());


    }

}
