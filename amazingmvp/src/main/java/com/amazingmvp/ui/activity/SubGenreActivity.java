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
package com.amazingmvp.ui.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import butterknife.Bind;
import com.amazingmvp.R;
import com.amazingmvp.AmazingMvpApplication;
import com.amazingmvp.di.ActivityModule;
import com.amazingmvp.di.components.DaggerGenreDetailsActivityComponent;
import com.amazingmvp.di.components.GenreDetailsActivityComponent;
import com.amazingmvp.util.ViewUtil;
import com.amazingmvprules.domain.model.SubGenre;
import com.amazingmvprules.domain.util.Tags;
import com.amazingmvprules.presenter.GenreDetailsPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import javax.inject.Inject;

public class SubGenreActivity extends AbstractActivity implements GenreDetailsPresenter.View {

  private GenreDetailsActivityComponent genreDetailsActivityComponent;

  @Inject GenreDetailsPresenter genreDetailsPresenter;

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
  @Bind(R.id.genre_image) SimpleDraweeView genreImage;
  @Bind(R.id.genre_details) TextView genreDetails;

  @Override protected int getLayoutId() {
    return R.layout.activity_sub_genre;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    genreDetailsActivityComponent().inject(this);
    SubGenre subGenre = parseIntent();
    genreDetailsPresenter.setSubGenre(subGenre);
    genreDetailsPresenter.setView(this);
    configToolbar(subGenre);
    configGenre(subGenre);
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(genreDetailsPresenter.saveInstance(outState));
  }

  @Override public boolean isCreated() {
    return !isFinishing();
  }

  private SubGenre parseIntent() {
    Intent intent = getIntent();
    SubGenre subGenre = intent.getParcelableExtra(Tags.GENRE);
    intent.removeExtra(Tags.GENRE);
    return subGenre;
  }

  private void configToolbar(SubGenre subGenre) {
    collapsingToolbarLayout.setTitle(subGenre.getTitle());
    setSupportActionBar(toolbar);
    ActionBar actionBar = getActionBar();
    if (actionBar != null) {
      actionBar.setHomeButtonEnabled(true);
      actionBar.setDisplayShowHomeEnabled(true);
    }
  }

  private void configGenre(SubGenre subGenre) {
    ViewUtil.bind(genreImage, subGenre.getImage());
    ViewUtil.verifyStringAndSet(genreDetails, subGenre.getDetails());
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
