package org.guf.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import rx.Observable;
import rx.Subscriber;


public class Sample1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//定义被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> sub) {
                sub.onNext("hello,world");
                sub.onCompleted();
            }
        });
        //定义订阅者
        Subscriber<String> stringSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                log("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                log("onError");
            }

            @Override
            public void onNext(String s) {
                log("onNext:" + s);
            }
        };
        //观察者订阅订阅者
        observable.subscribe(stringSubscriber);
    }

    public void log(String msg) {
        Log.d(getClass().getSimpleName(), msg);
    }

}
