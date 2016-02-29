/*
* Copyright (C) 2015 Pedro Paulo de Amorim
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
package com.amazingMvp.ui.presenter;

import com.amazingMvp.navigation.GenreNavigator;
import com.amazingmvprules.domain.interactors.GetGenres;
import com.amazingmvprules.domain.model.Genre;
import java.util.Collection;
import javax.inject.Inject;

public class GenrePresenterImpl implements GenrePresenter {

  private View view;
  private Collection<Genre> currentGenresLoaded;
  private GenreNavigator genreNavigator;
  private GetGenres getGenres;

  @Inject GenrePresenterImpl(GenreNavigator genreNavigator, GetGenres getGenres) {
    this.genreNavigator = genreNavigator;
    this.getGenres = getGenres;
  }

  @Override public void setView(View view) {
    if (view == null) {
      throw new IllegalArgumentException("The view must not be null!");
    }
    this.view = view;
  }

  @Override public void initialize() {
    loadCameras();
  }

  @Override public void resume() { }

  @Override public void pause() {
    if (currentGenresLoaded != null) {
      currentGenresLoaded.clear();
    }
    currentGenresLoaded = null;
  }

  @Override public void onGenreClick(Genre genre) {
    genreNavigator.openGenreActivity(genre);
  }

  @Override public void restoreLoadedGenres(Collection<Genre> genres) {
    showGenres(currentGenresLoaded);
  }

  @Override public Collection<Genre> getCurrentGenresLoaded() {
    return currentGenresLoaded;
  }

  private void loadCameras() {
    showLoading();
    getGenres.execute(new GetGenres.Callback() {
      @Override public void onGenresLoaded(Collection<Genre> genres) {
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

  private void showLoading() {
    if (view.isReady()) {
      view.showLoading();
    }
  }

  private void showGenres(Collection<Genre> genres) {
    if (view.isReady() && genres != null) {
      currentGenresLoaded = genres;
      view.renderGenres(genres);
      view.showGenres();
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
