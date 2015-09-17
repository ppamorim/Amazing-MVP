package com.amazingMvp.di;

import com.amazingMvp.di.scopes.ActivityScope;
import com.amazingMvp.domain.interactors.GetSync;
import com.amazingMvp.domain.interactors.GetSyncImpl;
import com.amazingMvp.ui.presenter.SyncPresenter;
import com.amazingMvp.ui.presenter.SyncPresenterImpl;
import dagger.Module;
import dagger.Provides;

@Module public class SyncModule {

  @Provides @ActivityScope SyncPresenter provideSyncPresenter(
      SyncPresenterImpl presenter) {
    return presenter;
  }

  @Provides @ActivityScope GetSync provideGetSync(
      GetSyncImpl getSync) {
    return getSync;
  }

}
