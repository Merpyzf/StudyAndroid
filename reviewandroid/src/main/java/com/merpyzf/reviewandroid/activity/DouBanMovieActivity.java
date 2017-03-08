package com.merpyzf.reviewandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.merpyzf.reviewandroid.R;
import com.merpyzf.reviewandroid.adapter.MovieListViewAdapter;
import com.merpyzf.reviewandroid.customui.PagingListView;
import com.merpyzf.reviewandroid.domian.MovieBean;
import com.merpyzf.reviewandroid.utils.httpUtils.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 *
 * 展示豆瓣电影Top250
 *
 */
public class DouBanMovieActivity extends AppCompatActivity {




    private int page = 0;

    private int count = 10;


    private PagingListView listView_movie;


    private ArrayList<MovieBean> movieBeenList = new ArrayList<MovieBean>();;
    private MovieListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dou_ban_movie);
        listView_movie = (PagingListView) findViewById(R.id.listView_movie);
        adapter = new MovieListViewAdapter(DouBanMovieActivity.this, movieBeenList);
        listView_movie.setAdapter(adapter);


        listView_movie.setOnLoadingListener(new PagingListView.onLoadingListener() {
            @Override
            public void onLoading() {


                Log.i("wk","https://api.douban.com/v2/movie/top250?start="+(page*count)+"&count="+count);

                new HttpRequest("https://api.douban.com/v2/movie/top250?start="+(page*count)+"+&count="+count) {
                    @Override
                    public void getResponse(String htmlDoc) {


                        parseJson(htmlDoc);

                        adapter.notifyDataSetChanged();
                        listView_movie.setLoadingComplete();



                    }
                };

                page++;

            }
        });






    }

    /**
     *
     * 解析json
     *
     * @param htmlDoc
     */
    private void parseJson(String htmlDoc) {



        ArrayList<String> castsList = null;

        ArrayList<String> directorsList = null;

        ArrayList<String> genresList = null;


        try {
            JSONObject jsonObject = new JSONObject(htmlDoc);

            JSONArray subjectsArray = jsonObject.getJSONArray("subjects");

            for(int i=0;i<subjectsArray.length();i++){

                MovieBean movie = new MovieBean();

                JSONObject jb = subjectsArray.getJSONObject(i);
                String alt =  jb.getString("alt");


                movie.setMovieDesc(alt);


                JSONArray castsArray = jb.getJSONArray("casts");

                //解析演员
                for(int j = 0;j<castsArray.length();j++){

                     castsList = new ArrayList<String>();
                    JSONObject jbCasts = castsArray.getJSONObject(j);
                    String name = jbCasts.getString("name");

                    castsList.add(name);



                }

                JSONArray directorsArray = jb.getJSONArray("directors");

                //解析导演

                for(int t=0;t<directorsArray.length();t++){


                    JSONObject jbDirector = directorsArray.getJSONObject(t);

                    directorsList = new ArrayList<String>();

                    directorsList.add(jbDirector.getString("name"));



                }

                //解析电影所属标签

                JSONArray genresArray = jb.getJSONArray("genres");

                for(int x = 0;x<genresArray.length();x++){

                    genresList = new ArrayList<>();

                    genresList.add(genresArray.getString(x));


                }


                movie.setDirectors(directorsList);
                movie.setCasts(castsList);
                movie.setGenres(genresList);

                movie.setAverage((float) jb.getJSONObject("rating").getDouble("average"));
                movie.setMax((float) jb.getJSONObject("rating").getDouble("max"));
                movie.setMin((float) jb.getJSONObject("rating").getDouble("min"));
                movie.setMovieImage(jb.getJSONObject("images").getString("large"));


                movie.setMovieName(jb.getString("title"));

                Log.i("wk",movie.getMovieName());

                movie.setYear(jb.getString("year"));


                movieBeenList.add(movie);
            }





        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
        }


    }
}
