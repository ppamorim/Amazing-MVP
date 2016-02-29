
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
