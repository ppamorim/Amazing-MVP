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
package com.amazingmvprules.domain.interactors;

import com.amazingmvprules.domain.model.Genre;
import com.amazingmvprules.domain.repository.GenreReposity;
import com.amazingmvprules.domain.service.GenreService;
import com.amazingmvprules.domain.util.DebugUtil;
import com.bluelinelabs.logansquare.LoganSquare;
import com.github.ppamorim.threadexecutor.Interactor;
import com.github.ppamorim.threadexecutor.InteractorExecutor;
import com.github.ppamorim.threadexecutor.MainThread;
import java.io.InputStream;
import java.util.ArrayList;
import javax.inject.Inject;
import okhttp3.OkHttpClient;

public class HomeInteractorImpl extends BaseImpl implements Interactor, HomeInteractor {

  private OkHttpClient okHttpClient;
  private final InteractorExecutor interactorExecutor;
  private final MainThread mainThread;
  private Callback callback;

  @Inject HomeInteractorImpl(InteractorExecutor interactorExecutor, MainThread mainThread,
      OkHttpClient okHttpClient) {
    this.interactorExecutor = interactorExecutor;
    this.mainThread = mainThread;
    this.okHttpClient = okHttpClient;
  }

  @Override public void execute(Callback callback) {
    validateArguments(callback);
    this.callback = callback;
    this.interactorExecutor.run(this);
  }

  @Override public void run() {
    try {
      Object result = new GenreService(okHttpClient).requestGenres();
      if(result instanceof InputStream) {
        handleArrayOfItems((ArrayList<Genre>) LoganSquare.parseList(
            (InputStream) result, Genre.class));
      }
    } catch (Exception e) {
      if (DebugUtil.DEBUG) {
        e.printStackTrace();
      }
      notifyNoConnection(0);
      if (!loadFromRepository()) {
        notifyConnectionError();
      }
    }
  }

  private void handleArrayOfItems(ArrayList<Genre> genres) {
    if (genres.size() > 0) {
      GenreReposity.handleGenres(genres);
      notifyConnectionSuccess(genres);
    } else if (!loadFromRepository()) {
      notifyEmpty();
    }
  }

  /**
   * Try to retrieve the information from
   * the database.
   *
   * @return it has success on query
   */
  private boolean loadFromRepository() {
    ArrayList<Genre> genres = (ArrayList<Genre>) GenreReposity.queryGenres();
    if (genres != null && genres.size() > 0) {
      notifyConnectionSuccess(genres);
      return true;
    }
    return false;
  }

  private void notifyConnectionSuccess(final ArrayList<Genre> subGenres) {
    mainThread.post(new Runnable() {
      @Override public void run() {
        callback.onGenresLoaded(subGenres);
      }
    });
  }

  private void notifyEmpty() {
    mainThread.post(new Runnable() {
      @Override public void run() {
        callback.onGenresEmpty();
      }
    });
  }

  private void notifyConnectionError() {
    mainThread.post(new Runnable() {
      @Override public void run() {
        callback.onErrorLoad();
      }
    });
  }

  private void notifyNoConnection(final int reason) {
    mainThread.post(new Runnable() {
      @Override public void run() {
        callback.onNoConnection(reason);
      }
    });
  }

}
