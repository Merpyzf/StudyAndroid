package com.merpyzf.reviewandroid.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.merpyzf.reviewandroid.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 使用AsyncTask进行网络图片加载并显示到ImageView
 */
public class AsyncTaskActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private EditText editText;
    private Button btnSubmit;
    private String mImageUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ansy_task);
        InitUI();

        btnSubmit.setOnClickListener(this);


    }

    private void InitUI() {
        imageView = (ImageView) findViewById(R.id.imageView_net);
        editText = (EditText) findViewById(R.id.edt_img_url);
        btnSubmit = (Button) findViewById(R.id.btn_show);
    }

    @Override
    public void onClick(View view) {

        MyAsyncTask myAsyncTask = new MyAsyncTask();
        mImageUrl = editText.getText().toString();
        myAsyncTask.execute(mImageUrl);



    }

    /**
     * 继承 AsyncTask 抽象类三个泛型参数的作用
     * 1.Params: 在执行AsyncTask时需要的参数,用于在后台任务中使用 (doInBackground)
     * 2.Progress: 后台任务执行的时候，如果需要在界面上显示进度，此处的反省执行的类型，为进度的单位,一般为Integer
     * 3.Result: 当后台任务执行完毕后需要返回值的的类型
     *  如果不需要传递参数，或者不需要返回值，此处将泛型指定为Void即可
     */
    class MyAsyncTask extends AsyncTask<String,Integer,Bitmap>{

        /**
         * 此方法在后台任务开始之前调用,用于进行界面上的初始化操作，比如显示一个进度条的对话框
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("wk","onPreExecute()方法执行了,此方法用于进行初始化相关的工作");

        }


        /**
         *此方法中的代码执行在子线程，用于处理一些耗时操作,
         * doInBackground(String... strings)中的参数在 execute(str)方法中传入。
         * 处理完后使用return返回处理结果
         *
         * 当需要反馈当前操作进度的时候，可以调用publishProgress()方法来完成，根据传递的参数，在onProgressUpdate()
         * 进行显示进度的状态。
         *
         * 注:由于代码运行在子线程，不能在这个方法中进行更新UI的操作。
         *
         * String ...属于不定参数,可以传递对个String对象
         * @param strings
         * @return
         */
        @Override
        protected Bitmap doInBackground(String... strings) {

            Log.i("wk","imageUrl:"+strings[0]);

            //调用此方法来触发 onProgressUpdate()方法的执行，传入参数进行进度的更新
            publishProgress(100);


            InputStream is = getStreamFromUrl(strings[0]);

            Bitmap bitmap = BitmapFactory.decodeStream(is);


            return bitmap;
        }


        /**
         * 在 doInBackground()方法中调用了 publishProgress()方法后,此方法将很快被调用,在这个方法
         * 中可以进行更新UI的操作,根据 publishProgress();传入的参数进行进度的更新
         * @param values
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            Log.i("wk","当前进度:"+values[0]);
        }

        /**
         * 当后台任务执行完毕并通过return 返回后,就会调用此方法,doInBackground()方法返回的
         * 数据会作为参数传递到onPostExecute()方法,此时可以利用返回的结果进行Ui的操作，或者关闭对话框。
         * @param bitmap
         */
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            imageView.setImageBitmap(bitmap);
        }
    }

    /**
     *
     * 根据Image的URL获取流
     * @param string
     * @return
     */
    private InputStream getStreamFromUrl(String string) {

        try {
            URL url = new URL(string);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            InputStream inputStream = conn.getInputStream();

            if(inputStream!=null){


                return inputStream;
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

        return null;

    }


}
