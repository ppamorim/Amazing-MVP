package com.amazingmvprules.presenter;

import com.amazingmvprules.domain.model.Genre;
import java.util.ArrayList;

public interface HomePresenter extends Presenter {
  void requestGenres(int tag);
  void setView(View view);
  Genre getGenreAtPosition(int position);
  interface View {
    boolean isReady();
    void renderGenres(ArrayList<Genre> subGenres);
    void showGenres();
    void showLoading();
    void showError();
    void showEmpty();
  }
}
