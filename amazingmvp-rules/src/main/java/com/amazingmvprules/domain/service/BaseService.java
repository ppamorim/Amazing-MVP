/*
* Copyright (C) 2016 Pedro Paulo de Amorim
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.amazingmvprules.domain.service;

import com.amazingmvprules.domain.util.DebugUtil;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This is a base class to do request to a
 * determined address and it handles the
 * success or error.
 */
public class BaseService {

  private OkHttpClient okHttpClient;

  /**
   * This constructor will be used to configure
   * the OkHttpClient instance, using the singleton
   * configuration provided by Module NetModule.
   * @param okHttpClient Instance of OkHttpClient.
   */
  public BaseService(OkHttpClient okHttpClient) {
    this.okHttpClient = okHttpClient;
  }

  /**
   * This method will do the GET request to a
   * determined address. If success, return
   * the String (ex: JSON), if fail, the status code.
   * @param url Address to do a GET request.
   * @return Object(String or Integer).
   * @throws IOException Handle the error connection
   * exception.
   */
  public Object get(String url) throws IOException {
    Request request = new Request.Builder()
        .url(url)
        .build();
    Response response = okHttpClient.newCall(request).execute();

    return response.isSuccessful()
        ? response.body().byteStream()
        : response.code();
  }

}
