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
package com.amazingMvp.ui.renderers.viewholder;

import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.amazingMvp.R;
import com.amazingMvp.domain.model.Genre;
import com.amazingMvp.ui.renderers.factory.Factory;
import com.amazingMvp.util.ViewUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.ppamorim.recyclerrenderers.viewholder.RenderViewHolder;

public class ViewHolderGenre extends RenderViewHolder<Genre> {

  private final Factory.GenreCallback genreCallback;

  @Bind(R.id.genre_image) SimpleDraweeView genreImage;
  @Bind(R.id.genre_name) TextView genreName;

  public ViewHolderGenre(View view, Factory.GenreCallback genreCallback) {
    super(view);
    ButterKnife.bind(this, view);
    this.genreCallback = genreCallback;
  }

  @Override public void onBindView(final Genre genre) {
    itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        genreCallback.onGenreClick(genre);
      }
    });
    genreName.setText(genre.getTitle());
    ViewUtil.bind(genreImage, genre.getImage());
  }

}
