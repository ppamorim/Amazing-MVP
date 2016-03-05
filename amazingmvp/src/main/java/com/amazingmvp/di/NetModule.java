package com.amazingmvp.di;

import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;

/**
 * This module will inject the instance of
 * a configured OKHttp as a singleton.
 */
@Module public class NetModule {

  @Provides @Singleton OkHttpClient provideOkHttpClient() {
    return new OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build();
  }

}