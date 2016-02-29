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
package com.amazingMvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import butterknife.Bind;
import com.amazingMvp.R;
import com.amazingMvp.ui.activity.BaseActivity;
import com.amazingMvp.ui.presenter.GenrePresenter;
import com.amazingMvp.util.ViewUtil;
import com.amazingmvprules.domain.model.Genre;
import icepick.Icepick;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
import jp.wasabeef.recyclerview.animators.adapters.AlphaInAnimationAdapter;

public class GenreFragment extends AbstractFragment implements GenrePresenter.View {

  @Inject GenrePresenter genrePresenter;

  @Bind(R.id.layout_error) FrameLayout errorLayout;
  @Bind(R.id.layout_empty) FrameLayout emptyLayout;
  @Bind(R.id.layout_loading) RelativeLayout loadingLayout;
  @Bind(R.id.recycler_view) RecyclerView recyclerView;

  @Override protected int getFragmentLayout() {
    return R.layout.fragment_genre;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    ((BaseActivity) getActivity()).genreFragmentComponent().inject(this);
    ViewUtil.configRecyclerView(getContext(), recyclerView);
    genrePresenter.setView(this);
    genrePresenter.initialize();
  }

  @Override public void onResume() {
    super.onResume();
    genrePresenter.resume();
  }

  @Override public void onPause() {
    genrePresenter.pause();
    super.onPause();
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    Icepick.saveInstanceState(this, outState);
    super.onSaveInstanceState(outState);
  }

  @Override public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
    super.onViewStateRestored(savedInstanceState);
    if (savedInstanceState != null) {
      Icepick.restoreInstanceState(this, savedInstanceState);
    }
  }

  @Override public boolean isReady() {
    return isAdded();
  }

  @Override public void renderGenres(Collection<Genre> genres) {
    //ArrayList<Renderable> renderable = new ArrayList<>();
    //renderable.addAll(genres);
    //recyclerView.setAdapter(new AlphaInAnimationAdapter(
    //        new RendererAdapter(renderable, new RendererBuilder(new Factory(onGenreCallback)),
    //            LayoutInflater.from(getActivity()))));
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

}
