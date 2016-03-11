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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;

/**
 * This class allows a simple representation of activity,
 * this will simplify the build of Activity and reduce
 * the boilerplate.
 */
public abstract class AbstractActivity extends AppCompatActivity {

  /**
   * This method will inflate the layout of requested
   * ResID, then, it will apply the ButterKnife binder
   * to bind the views of layout.
   *
   * @param savedInstanceState Saved instance of Activity.
   */
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutId());
    ButterKnife.bind(this);
  }

  /**
   * This abstract method will return the layout
   * of the view that the activity wants to load.
   *
   * @return ResID of Layout.
   */
  protected abstract int getLayoutId();

}
