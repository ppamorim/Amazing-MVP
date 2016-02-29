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
package com.amazingmvp.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import butterknife.Bind;
import com.amazingmvp.R;
import com.amazingmvp.ui.fragment.GenreFragment;
import com.amazingmvprules.domain.util.Tags;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.Bundler;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class BaseActivity extends AbstractActivity {

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.smart_tab_layout) SmartTabLayout smartTabLayout;
  @Bind(R.id.view_pager) ViewPager viewPager;

  @Override protected int getLayoutId() {
    return R.layout.activity_base;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    configToolbar();
    configViewPager();
  }

  private void configToolbar() {
    setSupportActionBar(toolbar);
  }

  private void configViewPager() {
    FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
        getSupportFragmentManager(), FragmentPagerItems.with(this)
        .add(R.string.techno, GenreFragment.class,
            new Bundler().putInt(Tags.TAG_GENRE, Tags.HOUSE).get())
        .add(R.string.house, GenreFragment.class,
            new Bundler().putInt(Tags.TAG_GENRE, Tags.TECHNO).get())
        .create());
    viewPager.setAdapter(adapter);
    smartTabLayout.setViewPager(viewPager);
  }

}
