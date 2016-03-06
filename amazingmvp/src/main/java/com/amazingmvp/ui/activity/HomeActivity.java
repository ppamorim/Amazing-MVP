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
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import butterknife.Bind;
import com.amazingmvp.R;
import com.amazingmvp.di.components.HomeActivityComponent;
import com.amazingmvp.ui.adapter.GenreAdapter;
import com.amazingmvp.ui.callback.GenreCallback;
import com.amazingmvp.util.ViewUtil;
import com.amazingmvprules.domain.model.SubGenre;
import com.amazingmvprules.domain.util.Tags;
import com.amazingmvprules.presenter.HomePresenter;
import java.util.ArrayList;

public class HomeActivity extends AbstractActivity implements HomePresenter.View, GenreCallback {

  private HomeActivityComponent homeActivityComponent;

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.tab_layout) TabLayout tabLayout;
  @Bind(R.id.view_pager) ViewPager viewPager;

  @Override protected int getLayoutId() {
    return R.layout.activity_base;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    homeActivityComponent().inject(this);
    super.onCreate(savedInstanceState);
    setSupportActionBar(toolbar);
    configViewPager();
  }

  @Override protected void onDestroy() {
    cleanAdapter();
    super.onDestroy();
  }

  @Override public void onGenreClick(SubGenre subGenre) {
    Intent intent = new Intent(this, SubGenreActivity.class);
    intent.putExtra(Tags.GENRE, subGenre);
    startActivity(intent);
  }

  @Override public boolean isReady() {
    return !isFinishing();
  }

  @Override public void renderGenres(ArrayList<SubGenre> subGenres) {

  }

  @Override public void showGenres() {

  }

  @Override public void showLoading() {

  }

  @Override public void showError() {

  }

  @Override public void showEmpty() {

  }

  /**
   * This method will only be executed if the device
   * is a tablet(sw bigger than 600dp).
   */
  private void toggleTabletMode(Resources res) {
    if(res.getBoolean(R.bool.tablet)) {
      int spacing = ViewUtil.getWidth(this) / res.getInteger(R.integer.view_pager_spacing);
      viewPager.setClipToPadding(false);
      viewPager.setPadding(spacing, 0, spacing, 0);
      viewPager.setPageMargin(spacing / 6);
    }
  }

  private void configViewPager() {
    Resources resources = getResources();
    GenreAdapter genreAdapter = new GenreAdapter(getSupportFragmentManager(), 2,
        resources.getStringArray(R.array.titles), this);
    viewPager.setAdapter(genreAdapter);
    tabLayout.setupWithViewPager(viewPager);
    toggleTabletMode(resources);
  }

  private void cleanAdapter() {
    PagerAdapter adapter = viewPager.getAdapter();
    if (adapter != null && adapter instanceof GenreAdapter) {
      ((GenreAdapter) adapter).invalidate();
    }
  }

  private HomeActivityComponent homeActivityComponent() {
    if (homeActivityComponent == null) {
      //homeActivityComponent = DaggerGenreFragmentComponent.builder()
      //    .applicationComponent(((AmazingMvpApplication) getActivity().getApplication()).component())
      //    .activityModule(new ActivityModule(getActivity()))
      //    .build();
    }
    return homeActivityComponent;
  }

}
