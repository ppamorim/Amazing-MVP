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

  @Override public void requestGenres(Bundle savedInstance) {
    showLoading();

    if (savedInstance != null) {
      restoreInstance(savedInstance);
    } else{
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
    if (genres != null) {
      showGenres(genres);
    } else {
      showError();
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
