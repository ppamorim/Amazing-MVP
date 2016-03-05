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
package com.amazingmvprules.presenter;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import com.amazingmvprules.domain.interactors.GenresInteractor;
import com.amazingmvprules.domain.model.Genre;
import com.amazingmvprules.domain.util.DebugUtil;
import com.amazingmvprules.domain.util.Tags;
import java.util.ArrayList;
import javax.inject.Inject;

public class GenrePresenterImpl implements GenrePresenter {

  private View view;
  private ArrayMap<Integer, Genre> currentGenresLoaded;
  private GenresInteractor genresInteractor;

  @Inject GenrePresenterImpl(GenresInteractor genresInteractor) {
    this.genresInteractor = genresInteractor;
  }

  @Override public void setView(View view) {
    if (view == null) {
      throw new IllegalArgumentException("The view must not be null!");
    }
    this.view = view;
  }

  @Override public void requestGenres(int tag) {
    if(currentGenresLoaded == null) {
      showLoading();
      genresInteractor.setTag(tag);
      genresInteractor.execute(new GenresInteractor.Callback() {
        @Override public void onGenresLoaded(ArrayMap<Integer, Genre> genres) {
          showGenres(genres);
        }

        @Override public void onGenresEmpty() {
          showEmpty();
        }

        @Override public void onErrorLoad() {
          showError();
        }
      });
    } else {
      showGenres(currentGenresLoaded);
    }
  }

  @Override public Bundle saveInstance(Bundle instance) {
    if (instance != null && currentGenresLoaded != null) {
      instance.putParcelableArrayList(Tags.GENRES,
          new ArrayList<>(currentGenresLoaded.values()));
    }
    return instance;
  }

  @Override public void restoreInstance(Bundle instance) {
    if (instance != null && instance.containsKey(Tags.GENRES)) {
      ArrayList<Genre> genres = instance.getParcelableArrayList(Tags.GENRES);
      currentGenresLoaded = new ArrayMap<>(genres.size());
      for(int i = 0, count = genres.size(); i < count; i++) {
        currentGenresLoaded.put(i, genres.get(i));
      }
    }
  }

  @Override public Genre getGenreAtPosition(int position) {
    return currentGenresLoaded != null ? currentGenresLoaded.get(position) : null;
  }

  private void showLoading() {
    if (view.isReady()) {
      view.showLoading();
    }
  }

  private void showGenres(ArrayMap<Integer, Genre> genres) {
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
