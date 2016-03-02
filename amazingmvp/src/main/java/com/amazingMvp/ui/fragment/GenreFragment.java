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
package com.amazingmvp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import com.amazingmvp.AmazingMvpApplication;
import com.amazingmvp.R;
import com.amazingmvp.di.ActivityModule;
import com.amazingmvp.di.components.DaggerGenreFragmentComponent;
import com.amazingmvp.di.components.GenreFragmentComponent;
import com.amazingmvp.ui.adapter.GenreAdapter;
import com.amazingmvp.ui.callback.GenreAdapterCallback;
import com.amazingmvp.ui.callback.GenreCallback;
import com.amazingmvprules.domain.model.Genre;
import com.amazingmvprules.domain.util.Tags;
import com.amazingmvprules.presenter.GenrePresenter;
import java.util.ArrayList;
import javax.inject.Inject;

public class GenreFragment extends AbstractFragment implements GenrePresenter.View,
    GenreAdapterCallback {

  private GenreFragmentComponent genreFragmentComponent;
  private GenreCallback genreCallback;

  @Inject GenrePresenter genrePresenter;

  @Bind(R.id.layout_error) TextView errorLayout;
  @Bind(R.id.layout_empty) TextView emptyLayout;
  @Bind(R.id.layout_loading) RelativeLayout loadingLayout;
  @Bind(R.id.recycler_view) RecyclerView recyclerView;

  @Override protected int getLayoutId() {
    return R.layout.fragment_genre;
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    genreFragmentComponent().inject(this);
    genrePresenter.setView(this);
    genrePresenter.restoreInstance(savedInstanceState);
    genrePresenter.requestGenres(getArguments().getInt(Tags.TAG_GENRE));
  }

  @Override public void onDestroy() {
    genrePresenter.destroy();
    super.onDestroy();
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(genrePresenter.saveInstance(outState));
  }

  @Override public boolean isReady() {
    return isAdded();
  }

  @Override public void renderGenres(ArrayList<Genre> genres) {
    recyclerView.setAdapter(new GenreAdapter(genres, this));
  }

  @Override public void showGenres() {
    recyclerView.setVisibility(View.VISIBLE);
    loadingLayout.setVisibility(View.GONE);
    emptyLayout.setVisibility(View.GONE);
    errorLayout.setVisibility(View.GONE);
  }

  @Override public void showLoading() {
    loadingLayout.setVisibility(View.VISIBLE);
    recyclerView.setVisibility(View.GONE);
    emptyLayout.setVisibility(View.GONE);
    errorLayout.setVisibility(View.GONE);
  }

  @Override public void showEmpty() {
    emptyLayout.setVisibility(View.VISIBLE);
    recyclerView.setVisibility(View.GONE);
    loadingLayout.setVisibility(View.GONE);
    errorLayout.setVisibility(View.GONE);
  }

  @Override public void showError() {
    errorLayout.setVisibility(View.VISIBLE);
    recyclerView.setVisibility(View.GONE);
    loadingLayout.setVisibility(View.GONE);
    emptyLayout.setVisibility(View.GONE);
  }

  @Override public void onItemPositionClick(int position) {
    Genre genre = genrePresenter.getGenreAtPosition(position);
    genreCallback.onGenreClick(genre);
  }

  public void setGenreCallback(GenreCallback genreCallback) {
    this.genreCallback = genreCallback;
  }

  private GenreFragmentComponent genreFragmentComponent() {
    if (genreFragmentComponent == null) {
      genreFragmentComponent = DaggerGenreFragmentComponent.builder()
          .applicationComponent(((AmazingMvpApplication) getActivity().getApplication()).component())
          .activityModule(new ActivityModule(getActivity()))
          .build();
    }
    return genreFragmentComponent;
  }

}
