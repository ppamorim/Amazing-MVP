package com.amazingmvprules.presenter;

import android.os.Bundle;
import com.amazingmvprules.domain.model.Genre;
import java.util.ArrayList;

public interface HomePresenter extends Presenter {
  void requestGenres(Bundle savedInstance);
  void setView(View view);
  interface View {
    boolean isReady();
    void renderGenres(ArrayList<Genre> subGenres);
    void showLoading();
    void showError();
    void showEmpty();
  }
}
