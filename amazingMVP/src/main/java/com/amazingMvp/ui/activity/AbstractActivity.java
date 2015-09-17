
package com.amazingMvp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;

public abstract class AbstractActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getContentViewId());
    injectViews();
  }

  protected abstract int getContentViewId();

  private void injectViews() {
    ButterKnife.bind(this);
  }

}
