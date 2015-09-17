package com.amazingMvp.di.components;

import com.amazingMvp.di.SyncModule;
import com.amazingMvp.di.scopes.ActivityScope;
import com.amazingMvp.domain.interactors.GetSync;
import com.amazingMvp.ui.activity.BaseActivity;
import com.amazingMvp.ui.presenter.SyncPresenter;
import dagger.Component;

@ActivityScope @Component(dependencies = ApplicationComponent.class, modules = SyncModule.class)
public interface SyncComponent {
  void inject(BaseActivity baseActivity);
  SyncPresenter presenter();
  GetSync getSync();
}
