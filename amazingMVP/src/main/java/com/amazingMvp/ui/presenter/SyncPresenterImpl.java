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

import com.amazingMvp.domain.interactors.GetSync;
import javax.inject.Inject;

public class SyncPresenterImpl implements SyncPresenter {

  private View view;
  private GetSync getSync;

  @Inject SyncPresenterImpl(GetSync getSync) {
    this.getSync = getSync;
  }

  @Override public void setView(View view) {
    this.view = view;
  }

  @Override public void initialize() {
    sync();
  }

  @Override public void resume() { }

  @Override public void pause() { }

  private void sync() {
    getSync.execute(new GetSync.Callback() {
      @Override public void onSyncEnd() {
        onSyncEnded();
      }

      @Override public void onSyncFail() {
        onSyncFailed();
      }
    });
  }

  private void onSyncEnded() {
    if (view.isReady()) {
     view.onEndSync();
    }
  }

  private void onSyncFailed() {
    if (view.isReady()) {
      view.onFailSync();
    }
  }

}