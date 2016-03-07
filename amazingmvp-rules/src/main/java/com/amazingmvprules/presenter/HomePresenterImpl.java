package com.amazingmvprules.presenter;

import android.os.Bundle;
import com.amazingmvprules.domain.interactors.HomeInteractor;
import com.amazingmvprules.domain.model.Genre;
import com.amazingmvprules.domain.util.Tags;
import java.util.ArrayList;
import javax.inject.Inject;

public class HomePresenterImpl implements HomePresenter {

  private View view;
  private HomeInteractor homeInteractor;
  private ArrayList<Genre> genres;

  @Inject HomePresenterImpl(HomeInteractor homeInteractor) {
    this.homeInteractor = homeInteractor;
  }

  @Override public void requestGenres() {
    showLoading();
    homeInteractor.execute(new HomeInteractor.Callback() {
      @Override public void onGenresLoaded(ArrayList<Genre> genres) {
        showGenres(genres);
      }

      @Override public void onGenresEmpty() {
        showEmpty();
      }

      @Override public void onErrorLoad() {
        showError();
      }
    });
  }

  @Override public void setView(View view) {
    if (view == null) {
      throw new IllegalArgumentException("The view must not be null!");
    }
    this.view = view;
  }

  @Override public Bundle saveInstance(Bundle instance) {
    if (instance != null && genres != null) {
      instance.putParcelableArrayList(Tags.GENRES, genres);
    }
    return instance;
  }

  @Override public void restoreInstance(Bundle instance) {
    if (instance != null && instance.containsKey(Tags.GENRES)) {
      genres = instance.getParcelableArrayList(Tags.GENRES);
    }
  }

  private void showLoading() {
    if (view.isReady()) {
      view.showLoading();
    }
  }

  private void showGenres(ArrayList<Genre> genres) {
    if (view.isReady() && genres != null) {
      this.genres = genres;
      view.renderGenres(genres);
    }
  }

  private void showError() {
    if (view.isReady()) {
      view.showError();
    }
  }

  private void showEmpty() {
    if (view.isReady()) {
      view.showEmpty();
    }
  }

}
