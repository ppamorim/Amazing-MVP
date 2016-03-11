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

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.ArrayMap;
import com.amazingmvp.ui.callback.GenreCallback;
import com.amazingmvp.ui.fragment.GenreFragment;
import com.amazingmvprules.domain.model.Genre;
import java.util.ArrayList;

public class GenreAdapter extends FragmentPagerAdapter {

  private ArrayMap<Integer, GenreFragment> fragments = null;
  private ArrayList<String> titles;

  public GenreAdapter(FragmentManager fragmentManager,
      int count, ArrayList<Genre> genres, GenreCallback genreCallback) {
    super(fragmentManager);
    generateFragmentsIfNeeded(count, genres, genreCallback);
  }

  /**
   * This method will return the Fragment instance based on
   * the position in the ArrayList.
   * @param position Position of item in ArrayList.
   * @return Fragment instance. If the ArrayList is null,
   * return null;
   */
  @Override public Fragment getItem(int position) {
    return fragments != null ? fragments.get(position) : null;
  }

  /**
   * This method is used to count the size of the ArrayList
   * of Fragments. If null, return 0.
   * @return The size of the ArrayList of Fragments.
   */
  @Override public int getCount() {
    return fragments != null ? fragments.size() : 0;
  }

  /**
   * The title needs only 3 characters to be configured.
   * @param position Position of the fragment.
   * @return The configured title or the super of the method.
   */
  @Override public CharSequence getPageTitle(int position) {
    return titles != null ? titles.get(position) : super.getPageTitle(position);
  }

  public void invalidate() {
    if (fragments != null) {
      fragments.clear();
    }
    fragments = null;
  }

  /**
   * This method will generate the fragments to be used by
   * the ViewPager.
   * Based on the position, this method will name the titles
   * and configure the accentColor of this fragment.
   * @param count Items on the success of the call of Interactor.
   */
  public void generateFragmentsIfNeeded(int count, ArrayList<Genre> genres,
      GenreCallback genreCallback) {
    if (this.fragments == null) {
      this.titles = new ArrayList<>(count);
      this.fragments = new ArrayMap<>(count);
      for (int i = 0; i < count; i++) {
        Genre genre = genres.get(i);
        this.titles.add(genre.getTitle());
        this.fragments.put(i, GenreFragment.newInstance(genreCallback, genre));
      }
    }
  }

}