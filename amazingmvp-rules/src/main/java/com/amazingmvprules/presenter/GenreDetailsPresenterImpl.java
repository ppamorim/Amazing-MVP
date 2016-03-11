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
import com.amazingmvprules.domain.model.SubGenre;
import com.amazingmvprules.domain.util.Tags;
import javax.inject.Inject;

public class GenreDetailsPresenterImpl implements GenreDetailsPresenter {

  private View view;
  private SubGenre subGenre;

  @Inject GenreDetailsPresenterImpl() { }

  @Override public void setView(View view) {
    if (view == null) {
      throw new IllegalArgumentException("The view must not be null!");
    }
    this.view = view;
  }

  @Override public void setSubGenre(SubGenre subGenre) {
    this.subGenre = subGenre;
  }

  @Override public Bundle saveInstance(Bundle instance) {
    if (instance != null) {
      instance.putParcelable(Tags.GENRE, subGenre);
    }
    return instance;
  }

  @Override public void restoreInstance(Bundle instance) {
    if (instance != null && instance.containsKey(Tags.GENRE)) {
      subGenre = instance.getParcelable(Tags.GENRE);
    }
  }

}
