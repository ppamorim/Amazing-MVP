package com.amazingmvp.di;

import com.amazingmvp.di.scopes.ActivityScope;
import com.amazingmvprules.domain.interactors.HomeInteractor;
import com.amazingmvprules.domain.interactors.HomeInteractorImpl;
import com.amazingmvprules.presenter.HomePresenter;
import com.amazingmvprules.presenter.HomePresenterImpl;
import dagger.Module;
import dagger.Provides;

@Module public class HomeModule {

  @Provides @ActivityScope HomePresenter provideHomePresenter(HomePresenterImpl presenter) {
    return presenter;
  }

  @Provides @ActivityScope HomeInteractor provideHomeInteractor(HomeInteractorImpl homeInteractor) {
    return homeInteractor;
  }

}
