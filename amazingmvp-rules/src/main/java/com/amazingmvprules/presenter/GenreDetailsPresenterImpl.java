///*
//* Copyright (C) 2015 Pedro Paulo de Amorim
//*
//* Licensed under the Apache License, Version 2.0 (the "License");
//* you may not use this file except in compliance with the License.
//* You may obtain a copy of the License at
//*
//* http://www.apache.org/licenses/LICENSE-2.0
//*
//* Unless required by applicable law or agreed to in writing, software
//* distributed under the License is distributed on an "AS IS" BASIS,
//* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//* See the License for the specific language governing permissions and
//* limitations under the License.
//*/
//package com.amazingmvprules.presenter;
//
//import android.os.Bundle;
//import android.os.Parcelable;
//import com.amazingmvprules.domain.model.Genre;
//import javax.inject.Inject;
//
//public class GenreDetailsPresenterImpl implements GenreDetailsPresenter {
//
//  private View view;
//  private Genre genre;
//  private GetGenreDetails getGenreDetails;
//
//  @Inject GenreDetailsPresenterImpl(GetGenreDetails getGenreDetails) {
//    this.getGenreDetails = getGenreDetails;
//  }
//
//  @Override public void setView(View view) {
//    if (view == null) {
//      throw new IllegalArgumentException("The view must not be null!");
//    }
//    this.view = view;
//  }
//
//  @Override public Bundle saveInstance(Bundle instance) {
//    return null;
//  }
//
//  @Override public void restoreInstance(Bundle instance) {
//
//  }
//
//  @Override public void destroy() {
//    genre = null;
//  }
//
//  private void loadCameras() {
//    getGenreDetails.execute(new GetGenreDetails.Callback() {
//      @Override public void onGenreLoaded(Genre genre) {
//        showGenre(genre);
//      }
//
//      @Override public void onGenresEmpty() {
//        showEmpty();
//      }
//
//    });
//  }
//
//  public void setParcelable(Parcelable parcelable) {
//    getGenreDetails.setParcelable(parcelable);
//  }
//
//  private void showGenre(Genre genre) {
//    if (genre != null && view.isCreated()) {
//      this.genre = genre;
//      view.renderGenre(genre);
//    }
//  }
//
//  private void showEmpty() {
//    if (view.isCreated()) {
//      view.showEmpty();
//    }
//  }
//
//}
