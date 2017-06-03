package com.merpyzf.reviewandroid.domian;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by 春水碧于天 on 2017/5/23.
 */
    @DatabaseTable(tableName = "tb_book") //表的表名
    public class Book {

        @DatabaseField(generatedId = true) //设置id的自增
        private int id;

        @DatabaseField(columnName = "book_name") //设置列名
        private String book_name;
        @DatabaseField(columnName = "book_author")
        private String book_author;
        @DatabaseField(columnName = "add_date")
        private Date add_date;

        public Book(int id, String book_name, String book_author, Date add_date) {
            this.id = id;
            this.book_name = book_name;
            this.book_author = book_author;
            this.add_date = add_date;
        }

        public Book(){



        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBook_name() {
            return book_name;
        }

        public void setBook_name(String book_name) {
            this.book_name = book_name;
        }

        public String getBook_author() {
            return book_author;
        }

        public void setBook_author(String book_author) {
            this.book_author = book_author;
        }

        public Date getAdd_date() {
            return add_date;
        }

        public void setAdd_date(Date add_date) {
            this.add_date = add_date;
        }
    }



