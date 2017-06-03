package com.merpyzf.reviewandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.merpyzf.reviewandroid.R;
import com.merpyzf.reviewandroid.domian.Book;
import com.merpyzf.reviewandroid.utils.orm.DatabaseHelper;
import com.merpyzf.reviewandroid.utils.orm.dao.BookDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * OrmLite数据库框架的使用
 */
public class OrmLiteActivity extends AppCompatActivity {

    private TextView tv_show;
    private DatabaseHelper mHelper;

    private BookDao dao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orm_lite);
        tv_show = (TextView) findViewById(R.id.tv_show);
        mHelper = DatabaseHelper.getHelper(this);

        dao = new BookDao(this);


    }

    public void clickAdd(View v) {


        Book book = new Book();

        book.setAdd_date(new Date(System.currentTimeMillis()));

        book.setBook_author("马丁");

        book.setBook_name("冰与火之歌");

        dao.addBook(book);


    }

    public void clickDelete(View v) {


        dao.delBook(3);


    }

    public void clickUpdate(View v) {

        Book book = new Book();

        book.setBook_name("一句话顶一万句");

        book.setBook_author("刘震云");

        book.setAdd_date(new Date(System.currentTimeMillis()));
        dao.UpdateBook(book,1);



    }

    public void clickQuery(View v) {


        List<Book> books = dao.QueryBook(1);

        StringBuffer sb = new StringBuffer();
        for (Book book : books) {
            Date add_date = book.getAdd_date();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String add_date_str = dateFormat.format(add_date);


            sb.append(" id==>"+book.getId()+" book_name==> "+book.getBook_name()+" book_author==> "+book.getBook_author()+" add_date==>" +add_date_str+"\n");

        }

        tv_show.setText(sb.toString());

    }

}
