package com.gufran.rxandroidretrofitstudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gufran.rxandroidretrofitstudy.entity.WeatherData;
import com.gufran.rxandroidretrofitstudy.retrofit.WeatherService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String TAG = "GUFRAN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //http://api.openweathermap.org/data/2.5/weather?q=London&APPID=cce3174e207504202106e7fbea3ed300


        Observer<WeatherData> observer = new Observer<WeatherData>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(@NonNull WeatherData weatherData) {
                Log.d(TAG, "onNext: Description" + weatherData.getWeather()
                        .get(0)
                        .getDescription());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .build();

        WeatherService weatherService = retrofit.create(WeatherService.class);
        Observable<WeatherData> london = weatherService.getWeatherData("Islamabad", "cce3174e207504202106e7fbea3ed300");

        london.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        /* Log.e("Current Weather", weatherData.getWeather()
                            .get(0)
                            .getDescription());*/


    }
}
