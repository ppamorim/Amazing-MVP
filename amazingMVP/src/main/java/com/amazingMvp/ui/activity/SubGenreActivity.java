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
package com.amazingMvp.ui.activity;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.Bind;
import com.amazingMvp.AmazingMvpApplication;
import com.amazingMvp.R;
import com.amazingMvp.di.ActivityModule;
import com.amazingMvp.di.components.DaggerGenreDetailsActivityComponent;
import com.amazingMvp.di.components.GenreDetailsActivityComponent;
import com.amazingMvp.ui.presenter.GenreDetailsPresenter;
import com.amazingMvp.util.Tags;
import com.amazingMvp.util.ViewUtil;
import com.amazingmvprules.domain.model.Genre;
import com.facebook.drawee.view.SimpleDraweeView;
import javax.inject.Inject;

public class SubGenreActivity extends AbstractActivity implements GenreDetailsPresenter.View {

  private GenreDetailsActivityComponent genreDetailsActivityComponent;

  @Inject GenreDetailsPresenter genreDetailsPresenter;

  @Bind(R.id.genre_image) SimpleDraweeView genreImage;
  @Bind(R.id.genre_title) TextView genreTitle;
  @Bind(R.id.genre_details) TextView genreDetails;

  @Override protected int getContentViewId() {
    return R.layout.activity_sub_genre;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    genreDetailsActivityComponent().inject(this);
    genreDetailsPresenter.setParcelable(getIntent().getParcelableExtra(Tags.GENRE));
    genreDetailsPresenter.setView(this);
    genreDetailsPresenter.initialize();
  }

  @Override public boolean isCreated() {
    return !isFinishing();
  }

  @Override public void renderGenre(Genre genre) {
    ViewUtil.bind(genreImage, genre.getImage());
    ViewUtil.verifyStringAndSet(genreTitle, genre.getTitle());
    ViewUtil.verifyStringAndSet(genreDetails, genre.getDetails());
  }

  @Override public void showEmpty() {

  }

  public GenreDetailsActivityComponent genreDetailsActivityComponent() {
    if (genreDetailsActivityComponent == null) {
      genreDetailsActivityComponent = DaggerGenreDetailsActivityComponent.builder()
          .applicationComponent(((AmazingMvpApplication) getApplication()).component())
          .activityModule(new ActivityModule(this))
          .build();
    }
    return genreDetailsActivityComponent;
  }

}
