package com.merpyzf.reviewandroid.utils.orm.dao;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.merpyzf.reviewandroid.domian.Book;
import com.merpyzf.reviewandroid.utils.orm.DBHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 春水碧于天 on 2017/5/23.
 */

public class BookDao {

    private Context context;
    private Dao<Book, Integer> BookDaoHelper;


    public BookDao(Context context) {
        this.context = context;
        DBHelper dbHelper = new DBHelper(context);
        BookDaoHelper = dbHelper.getDao(Book.class);

    }


    //添加一本书
    public int addBook(Book book) {

        int addNum = 0;
        try {
            addNum = BookDaoHelper.create(book);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.i("wk", "SQL语句异常");
        }

        return addNum;

    }


    //删除一本书
    public int delBook(int book_id) {

        int Num = 0;

        try {

            Num = BookDaoHelper.deleteById(book_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Num;

    }


    //更新一本书
    public int UpdateBook(Book book, int book_id) {

        int Num = 0;

        try {

            Num = BookDaoHelper.updateId(book, book_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Num;

    }


    //更新一本书
    public List<Book> QueryBook(int id) {


        List<Book> Books = null;

        try {


            //orderBy : false表示降序 true表示升序
            Books = BookDaoHelper.queryBuilder().orderBy("id", true).where().gt("id", 1).query();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Books;

    }


}

