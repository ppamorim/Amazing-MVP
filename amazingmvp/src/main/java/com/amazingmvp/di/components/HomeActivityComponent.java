package com.amazingmvp.di.components;

import com.amazingmvp.di.ActivityModule;
import com.amazingmvp.di.HomeModule;
import com.amazingmvp.di.scopes.ActivityScope;
import com.amazingmvp.ui.activity.HomeActivity;
import com.amazingmvprules.domain.interactors.HomeInteractor;
import com.amazingmvprules.presenter.HomePresenter;
import dagger.Component;

@SuppressWarnings("unused")
@ActivityScope @Component(dependencies = ApplicationComponent.class,
    modules = { ActivityModule.class, HomeModule.class })
public interface HomeActivityComponent {
  void inject(HomeActivity homeActivity);
  HomePresenter getPresenter();
  HomeInteractor getGenres();
}
