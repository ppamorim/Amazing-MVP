package com.amazingmvprules.domain.service;

import java.io.IOException;
import javax.inject.Inject;
import okhttp3.OkHttpClient;

public class GenreService extends BaseService {

  @Inject public GenreService(OkHttpClient okHttpClient) {
    super(okHttpClient);
  }

  public Object requestGenres() throws IOException {
    return get("https://gist.githubusercontent.com/ppamorim/556f3428e72da4386b17/raw/b9d64cbe004d5db5755bc0bd830136a65fbae127/gistfile1.txt");
  }

}
