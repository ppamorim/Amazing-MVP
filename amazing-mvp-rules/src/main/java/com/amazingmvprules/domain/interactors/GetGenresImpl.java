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
package com.amazingmvprules.domain.interactors;

import com.amazingmvprules.domain.executor.Interactor;
import com.amazingmvprules.domain.executor.InteractorExecutor;
import com.amazingmvprules.domain.executor.MainThread;
import com.amazingmvprules.domain.model.Genre;
import com.amazingmvprules.domain.util.DebugUtil;
import com.amazingmvprules.domain.util.StubData;
import java.util.ArrayList;
import javax.inject.Inject;

public class GetGenresImpl extends BaseImpl implements Interactor, GetGenres {

  private final InteractorExecutor interactorExecutor;
  private final MainThread mainThread;
  private Callback callback;

  @Inject GetGenresImpl(InteractorExecutor interactorExecutor, MainThread mainThread) {
    this.interactorExecutor = interactorExecutor;
    this.mainThread = mainThread;
  }

  @Override public void execute(Callback callback) {
    validateArguments(callback);
    this.callback = callback;
    this.interactorExecutor.run(this);
  }

  @Override public void run() {
    try {
      ArrayList<Genre> cameras = createItems();
      if (cameras.size() > 0) {
        notifyConnectionSuccess(cameras);
      } else {
        notifyEmpty();
      }
    } catch (Exception e) {
      if (DebugUtil.DEBUG) {
        e.printStackTrace();
      }
      notifyConnectionError();
    }
  }

  private void notifyConnectionSuccess(final ArrayList<Genre> genres) {
    mainThread.post(new Runnable() {
      @Override public void run() {
        callback.onGenresLoaded(genres);
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

  private ArrayList<Genre> createItems() {
    ArrayList<Genre> items = new ArrayList<>();

    String[] title = StubData.TECHNO_ARRAY;
    String[] urlImage = StubData.TECHNO_IMAGE;
    String[] details = StubData.TECHNO_DETAIL;

    for (int i = 0, count = title.length; i < count; i++) {
      items.add(new Genre(title[i], urlImage[i], details[i]));
    }

    return items;
  }

}
