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

import android.os.Parcelable;
import com.amazingmvprules.domain.executor.Interactor;
import com.amazingmvprules.domain.executor.InteractorExecutor;
import com.amazingmvprules.domain.executor.MainThread;
import com.amazingmvprules.domain.model.Genre;
import javax.inject.Inject;
import org.parceler.Parcels;

public class GetGenreDetailsImpl extends BaseImpl implements Interactor, GetGenreDetails {

  private final InteractorExecutor interactorExecutor;
  private final MainThread mainThread;
  private Parcelable parcelable;
  private Callback callback;

  @Inject GetGenreDetailsImpl(InteractorExecutor interactorExecutor, MainThread mainThread) {
    this.interactorExecutor = interactorExecutor;
    this.mainThread = mainThread;
  }

  @Override public void execute(Callback callback) {
    validateArguments(callback);
    this.callback = callback;
    this.interactorExecutor.run(this);
  }

  @Override public void run() {
    Genre genre = Parcels.unwrap(parcelable);
    if (genre != null) {
      notifySuccess(genre);
    } else {
      notifyEmpty();
    }
  }

  @Override public void setParcelable(Parcelable parcelable) {
    this.parcelable = parcelable;
  }

  private void notifySuccess(final Genre genre) {
    mainThread.post(new Runnable() {
      @Override public void run() {
        callback.onGenreLoaded(genre);
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

}
