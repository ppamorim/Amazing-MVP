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
package com.amazingMvp.domain.interactors;

import android.content.res.Resources;
import com.amazingMvp.R;
import com.amazingMvp.domain.model.Genre;
import com.amazingMvp.executor.Interactor;
import com.amazingMvp.executor.InteractorExecutor;
import com.amazingMvp.executor.MainThread;
import com.amazingMvp.util.DebugUtil;
import java.util.ArrayList;
import javax.inject.Inject;

public class GetGenresImpl extends BaseImpl implements Interactor, GetGenres {

  private final InteractorExecutor interactorExecutor;
  private final MainThread mainThread;
  private Callback callback;
  private Resources resources;

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

  @Override public void getResources(Resources resources) {
    this.resources = resources;
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

    String[] title = resources.getStringArray(R.array.techno_array);
    String[] urlImage = resources.getStringArray(R.array.techno_image_array);
    String[] details = resources.getStringArray(R.array.techno_array_details);

    for (int i = 0; i < title.length; i++) {
      items.add(new Genre(title[i], urlImage[i], details[i]));
    }
    return items;
  }

}
