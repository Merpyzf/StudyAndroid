package com.merpyzf.reviewandroid.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.merpyzf.reviewandroid.R;
import com.merpyzf.reviewandroid.utils.httpUtils.HttpHelper;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.observable.ObservableError;
import io.reactivex.schedulers.Schedulers;

import static io.reactivex.Observable.create;

/**
 * RxJava学习
 * GitHub主页上的介绍:
 * 一个在java VM上使用可观测的序列来组成异步的、基于时间的程序的库
 */
public class StudyRxJavaActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_rx_java);
        mContext = this;

    }

    /**
     * 1.第一个RxJava的HelloWorld例子
     *
     * @param v
     */
    public void clickHelloWorld(View v) {


        //创建一个被观察者
        /**
         * 当Observable被调用的时候ObservableOnSubscribe里的subscribe方法会自动被调用
         *
         * 事件序列会依照设定依次触发。
         *
         * 由被观察者调用了观察者的回调方法，就实现了由被观察者向观察者的事件传递，即观察者模式
         *
         */
        Observable myObservable = create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

                e.onNext("Hello RxAndroid");

                e.onNext("Hello RxJava");

            }
        });

        //创建一个观察者
        Observer myObserver = new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            //普通事件的回调
            @Override
            public void onNext(String value) {

                Toast.makeText(mContext, value, Toast.LENGTH_SHORT).show();


            }

            //出错时的回调
            @Override
            public void onError(Throwable e) {

            }

            //完成时进行回调
            @Override
            public void onComplete() {

            }
        };

        //观察者订阅被观察者
        myObservable.subscribe(myObserver);


    }

    /**
     * 2.使用Just方法快捷创建事件队列
     *
     * @param v
     */
    public void clickJust(View v) {

        /**
         *
         * 将会依次调用:
         *  onNext("Hello")
         *  onNext("hi")
         *  onNext("xiuxiu^ ^")
         *   onComplete()
         */
        final Observable myJustObservable = Observable.just("Hello", "hi", "xiuxiu^ ^");


        Observer myObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {


            }

            @Override
            public void onNext(String value) {

                Toast.makeText(mContext, value, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(Throwable e) {


            }

            @Override
            public void onComplete() {

                Toast.makeText(mContext, "onComplete()", Toast.LENGTH_SHORT).show();


            }
        };


        //进行事件的订阅
        myJustObservable.subscribe(myObserver);


    }


    /**
     * 3.使用Consumer作为观察者只关心onNext()事件
     *
     * @param v
     */
    public void clickConsumer(View v) {


        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

                e.onNext("咳咳 --来自onNext()事件");

            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {


                Toast.makeText(mContext, "我只关心onNext()事件，其他的才不管呢！" + s, Toast.LENGTH_SHORT).show();


            }
        });

    }


    /**
     * RxJava线程相关的学习
     *
     * @param v
     */
    public void clickThread(View v) {


        /**
         *
         * 1.下面实例说明:
         *
         *  被观察者和观察者是处在同一个线程中工作,在被观察者中进行网络请求会报错！！
         *
         */
    /*    ObservableError.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

                e.onNext("hi");

                Log.i("wk","subscribe中所在的线程:"+Thread.currentThread().getName());

//                String htmlDoc = HttpHelper.httpGet("https://www.baidu.com");



            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

                Log.i("wk","从onNext事件中接收到的参数:"+s);

                Log.i("wk","accpet中所在的线程:"+Thread.currentThread().getName());

            }
        });*/

        /**
         *使用RxJava内置的线程调度器:
         *
         *  改变被观察者发送事件的线程到子线程中去执行，然后再改变观察者的线程，让它
         *  去主线程接收事件。
         *
         *  此时请求网络获取服务器的数据是执行在子线程之中的不会报错
         *
         *
         *  在RxJava中, 已经内置了很多线程选项供我们选择, 例如有
         *
         *    Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作
         *    Schedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作
         *    Schedulers.newThread() 代表一个常规的新线程
         *    AndroidSchedulers.mainThread() 代表Android的主线程
         *

         */

        ObservableError.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {


                Log.i("wk", "subscribe中所在的线程:" + Thread.currentThread().getName());

                String htmlDoc = HttpHelper.httpGet("https://www.baidu.com");

                e.onNext(htmlDoc);

            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                        Log.i("wk", "accpet中所在的线程:" + Thread.currentThread().getName());

                        Log.i("wk","从onNext事件中接收到的html代码:"+s);

                    }
                });


        /**
         * 注:subscribeOn(Schedulers.newThread()) 指定被观察者发送事件的线程
         *   observeOn(AndroidSchedulers.mainThread()) 指定观察者接收事件的线程
         *
         *   被观察者只有第一次指定线程有效！
         *   观察者可以多次指定事件接收的线程
         */




    }


}

