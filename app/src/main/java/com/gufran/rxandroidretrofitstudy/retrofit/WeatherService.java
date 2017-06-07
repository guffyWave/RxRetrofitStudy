package com.gufran.rxandroidretrofitstudy.retrofit;

import com.gufran.rxandroidretrofitstudy.entity.WeatherData;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Description ...
 *
 * @author Gufran Khurshid
 * @version 1.0
 * @since 6/7/17
 */

public interface WeatherService {
    @GET("weather?")
    Observable<WeatherData> getWeatherData(@Query("q") String city, @Query("APPID") String key);
}
