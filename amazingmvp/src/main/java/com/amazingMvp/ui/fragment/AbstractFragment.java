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
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;

/**
 * This class allow a simple representation of Fragment(v4),
 * this will simplify the build of Fragment and reducing
 * the boiler plate.
 */
public abstract class AbstractFragment extends Fragment {

  /**
   * This method will inflate the view and apply
   * the binder of ButterKnife in this view.
   *
   * @param inflater Instance of LayoutInflater.
   * @param container The parent of Fragment, a ViewPager.
   * @param savedInstanceState Saved instance of the
   * fragment, not used here.
   * @return The view inflated.
   */
  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(getLayoutId(), container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  /**
   * This abstract method will return the layout
   * of the view that the fragment want to load.
   *
   * @return ResID of Layout.
   */
  protected abstract int getLayoutId();

}
