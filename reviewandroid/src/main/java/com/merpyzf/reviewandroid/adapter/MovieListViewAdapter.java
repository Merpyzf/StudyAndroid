package com.merpyzf.reviewandroid.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.merpyzf.reviewandroid.R;
import com.merpyzf.reviewandroid.domian.MovieBean;

import java.util.ArrayList;

/**
 * 豆瓣Top250电影展示的listview
 *
 * @author wangke
 * 2017/3/5.
 */

public class MovieListViewAdapter extends BaseAdapter {


    private ArrayList<MovieBean> movieList;
    private Context context;

    public MovieListViewAdapter(  Context context,ArrayList<MovieBean> movieList){


        this.movieList = movieList;
        this.context = context;
    }



    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHoler viewHoler = null;

        MovieBean movie = movieList.get(position);

        if(convertView == null){

            convertView = View.inflate(context, R.layout.item_movie_listview,null);

            viewHoler = new ViewHoler();

            viewHoler.cardView = (CardView) convertView.findViewById(R.id.cardView);
            viewHoler.tv_douban_point = (TextView)convertView.findViewById(R.id.tv_douban_point);
            viewHoler.imageView= (ImageView) convertView.findViewById(R.id.imageView);
            viewHoler.tv_movie_cast =  (TextView)convertView.findViewById(R.id.tv_movie_cast);
            viewHoler.tv_douban_point=  (TextView)convertView.findViewById(R.id.tv_douban_point);
            viewHoler.tv_movie_tip=  (TextView)convertView.findViewById(R.id.tv_movie_tip);
            viewHoler.tv_movie_director=  (TextView)convertView.findViewById(R.id.tv_movie_director);
            viewHoler.tv_show_name = (TextView)convertView.findViewById(R.id.tv_movie_name);

            convertView.setTag(viewHoler);

        }else {


            viewHoler = (ViewHoler) convertView.getTag();
        }


        //使用Glide加载图片
        Glide.with(context).load(movie.getMovieImage()).into(viewHoler.imageView);

//        viewHoler.tv_show_name.setText(movie.getMovieName());

        StringBuilder sb_d = new StringBuilder();

        sb_d.append("导演: ");

        for(int i=0;i<movie.getDirectors().size();i++){


            String director = movie.getDirectors().get(i);

            sb_d.append(director+" ");


        }


        StringBuilder sb_c = new StringBuilder();

        sb_c.append("演员: " );

        for(int j = 0;j<movie.getCasts().size();j++){


            sb_c.append(movie.getCasts().get(j)+"");




        }

        StringBuilder sb_t = new StringBuilder();

        sb_t.append("标签: ");

        for(int t = 0;t<movie.getGenres().size();t++){


            sb_t.append(movie.getGenres().get(t)+" ");


        }






        viewHoler.tv_movie_director.setText(sb_d.toString());

        viewHoler.tv_movie_cast.setText(sb_c.toString());

        viewHoler.tv_movie_tip.setText(sb_t.toString());
        viewHoler.tv_douban_point.setText(movie.getAverage()+"");

        viewHoler.tv_show_name.setText(movie.getMovieName());



        return convertView;
    }

    static class ViewHoler{

        ImageView imageView;
        TextView tv_show_name;
        TextView tv_movie_director;
        TextView tv_movie_cast;
        TextView tv_movie_tip;
        TextView tv_douban_point;
        CardView cardView;




    }
}
