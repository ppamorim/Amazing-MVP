package com.amazingmvprules.domain.service;

import java.io.IOException;
import javax.inject.Inject;
import okhttp3.OkHttpClient;

public class GenreService extends BaseService {

  @Inject public GenreService(OkHttpClient okHttpClient) {
    super(okHttpClient);
  }

  public Object requestGenres() throws IOException {
    return get("https://gist.githubusercontent.com/ppamorim/e26c4b6f63245a674516/"
        + "raw/5927897b401ef43167bdfac2c19692808bb5ec4e/json.json");
  }

}
