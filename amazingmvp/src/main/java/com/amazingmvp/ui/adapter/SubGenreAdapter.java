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
package com.amazingmvp.ui.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.amazingmvp.R;
import com.amazingmvp.ui.callback.GenreAdapterCallback;
import com.amazingmvprules.domain.model.SubGenre;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;

/**
  * This class represent the adapter of the RecyclerView
  * and will load the Genre of the Tab.
  */
public class SubGenreAdapter extends RecyclerView.Adapter<SubGenreAdapter.ViewHolder> {

  private final GenreAdapterCallback genreAdapterCallback;
  private List<SubGenre> subGenres = null;

  /**
   * This constructor will be used to configure the
   * RecyclerView of the GenreFragment.
   * @param subGenres Array of Genres
   */
  public SubGenreAdapter(List<SubGenre> subGenres, GenreAdapterCallback genreAdapterCallback) {
    this.subGenres = subGenres;
    this.genreAdapterCallback = genreAdapterCallback;
  }

  /**
   * This method will load create ViewHolder and inflate the layout.
   * @param parent The RecyclerView.
   * @param viewType The type of the adapter to be inflated.
   * @return The ViewHolder created and with the layout inflated.
   */
  @Override public SubGenreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
      int viewType) {
    return new ViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.adapter_genre, parent, false), genreAdapterCallback);
  }

  /**
   * This method will load the data to the adapter, based
   * on the position of the adapter, this will get the
   * ViewHolder and configure these infos.
   * This method will be used to create the detail item too
   * and be used to inform to the user about the date of the
   * first and last Genre. Based on the information of the
   * method getItemViewType.
   * @param viewHolder Instance of ViewHolder of this Adapter.
   * @param position Position of the item in the Adapter.
   */
  @Override public void onBindViewHolder(SubGenreAdapter.ViewHolder viewHolder, int position) {
    viewHolder.configView(position, getItemAtPosition(position));
  }

  /**
   * This method is needed to inform to the adapter how
   * many items has this adapter.
   * If Genres is equals to null, return 0.
   * @return The size of Genres.
   */
  @Override public int getItemCount() {
    return subGenres != null ? subGenres.size() : 0;
  }

  /**
   * This method will be used to get a Genre object,
   * this object need to be on the ArrayList and can be
   * requested by the method onBindViewHolder of this Adapter.
   * @param index Position of the item on ArrayList.
   * @return Genre object.
   */
  public SubGenre getItemAtPosition(int index) {
    return subGenres != null ? subGenres.get(index) : null;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder
      implements View.OnClickListener {

    private GenreAdapterCallback genreAdapterCallback;

    @Bind(R.id.genre_name) TextView genreName;
    @Bind(R.id.genre_image) SimpleDraweeView genreImage;

    public ViewHolder(View view, GenreAdapterCallback genreAdapterCallback) {
      super(view);
      this.genreAdapterCallback = genreAdapterCallback;
      ButterKnife.bind(this, view);
      view.setOnClickListener(this);
    }

    /**
     * Configure the relative position of the ViewHolder,
     * set the name of the subGenre and load the image
     * of the subGenre.
     * @param position Relative position on the RecyclerView.
     * @param subGenre The model of the adapter.
     */
    public void configView(int position, SubGenre subGenre) {
      itemView.setTag(position);
      genreName.setText(subGenre.getTitle());
      genreImage.setImageURI(Uri.parse(subGenre.getImage()));
    }

    @Override public void onClick(View view) {
      genreAdapterCallback.onItemPositionClick((Integer) itemView.getTag());
    }

  }

}
