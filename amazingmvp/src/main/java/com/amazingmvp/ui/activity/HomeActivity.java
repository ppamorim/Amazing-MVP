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

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import com.amazingmvp.AmazingMvpApplication;
import com.amazingmvp.R;
import com.amazingmvp.di.ActivityModule;
import com.amazingmvp.di.components.DaggerHomeActivityComponent;
import com.amazingmvp.di.components.HomeActivityComponent;
import com.amazingmvp.ui.adapter.GenreAdapter;
import com.amazingmvp.ui.callback.GenreCallback;
import com.amazingmvp.util.ViewUtil;
import com.amazingmvprules.domain.model.Genre;
import com.amazingmvprules.domain.model.SubGenre;
import com.amazingmvprules.domain.util.Tags;
import com.amazingmvprules.presenter.HomePresenter;
import java.util.ArrayList;
import javax.inject.Inject;

public class HomeActivity extends AbstractActivity implements HomePresenter.View, GenreCallback {

  private HomeActivityComponent homeActivityComponent;

  @Inject HomePresenter homePresenter;

  @Bind(R.id.container) RelativeLayout container;
  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.tab_layout) TabLayout tabLayout;
  @Bind(R.id.view_pager) ViewPager viewPager;
  @Bind(R.id.progress_bar) ProgressBar progressBar;
  @Bind(R.id.text_view_warning) TextView warning;
  @Bind(R.id.try_again) Button tryAgain;

  @Override protected int getLayoutId() {
    return R.layout.activity_base;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    homeActivityComponent().inject(this);
    super.onCreate(savedInstanceState);
    homePresenter.setView(this);
    setSupportActionBar(toolbar);
    homePresenter.requestGenres(savedInstanceState);
  }

  @Override protected void onDestroy() {
    cleanAdapter();
    super.onDestroy();
  }

  @Override public boolean isReady() {
    return !isFinishing();
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(homePresenter.saveInstance(outState));
  }

  @Override public void renderGenres(ArrayList<Genre> genre) {
    configViewPager(genre);
    tabLayout.setVisibility(View.VISIBLE);
    viewPager.setVisibility(View.VISIBLE);
    tryAgain.setVisibility(View.GONE);
    warning.setVisibility(View.GONE);
    progressBar.setVisibility(View.GONE);
  }

  @Override public void showLoading() {
    tabLayout.setVisibility(View.GONE);
    viewPager.setVisibility(View.GONE);
    tryAgain.setVisibility(View.GONE);
    warning.setVisibility(View.GONE);
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override public void showError() {
    tabLayout.setVisibility(View.GONE);
    viewPager.setVisibility(View.GONE);
    tryAgain.setVisibility(View.VISIBLE);
    warning.setVisibility(View.VISIBLE);
    progressBar.setVisibility(View.GONE);
  }

  @Override public void showEmpty() {
    tabLayout.setVisibility(View.GONE);
    viewPager.setVisibility(View.GONE);
    tryAgain.setVisibility(View.VISIBLE);
    warning.setVisibility(View.VISIBLE);
    progressBar.setVisibility(View.GONE);
  }

  @Override public void showOffline(int reason) {
    Snackbar snackbar = Snackbar.make(container, R.string.connection_error, Snackbar.LENGTH_LONG);
    snackbar.show();
  }

  @Override public void onGenreClick(SubGenre subGenre) {
    Intent intent = new Intent(this, SubGenreActivity.class);
    intent.putExtra(Tags.GENRE, subGenre);
    startActivity(intent);
  }

  /**
   * This method will only be executed if the device
   * is a tablet(sw bigger than 600dp).
   */
  private void toggleTabletMode() {
    Resources res = getResources();
    if (res.getBoolean(R.bool.tablet)) {
      int spacing = ViewUtil.getWidth(this) / res.getInteger(R.integer.view_pager_spacing);
      viewPager.setClipToPadding(false);
      viewPager.setPadding(spacing, 0, spacing, 0);
      viewPager.setPageMargin(spacing / 6);
    }
  }

  /**
   * This method will configure the ViewPager
   * with the adapter and will set the ViewPager
   * to the TabLayout.
   */
  private void configViewPager(ArrayList<Genre> genres) {
    viewPager.setAdapter(new GenreAdapter(getSupportFragmentManager(),
        genres.size(), genres, this));
    tabLayout.setupWithViewPager(viewPager);
    toggleTabletMode();
  }

  private void cleanAdapter() {
    PagerAdapter adapter = viewPager.getAdapter();
    if (adapter != null && adapter instanceof GenreAdapter) {
      ((GenreAdapter) adapter).invalidate();
    }
  }

  private HomeActivityComponent homeActivityComponent() {
    if (homeActivityComponent == null) {
      homeActivityComponent = DaggerHomeActivityComponent.builder()
          .applicationComponent(((AmazingMvpApplication) getApplication()).component())
          .activityModule(new ActivityModule(this))
          .build();
    }
    return homeActivityComponent;
  }

}
