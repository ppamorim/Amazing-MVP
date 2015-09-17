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
package com.amazingMvp;

import android.app.Application;
import com.amazingMvp.di.ApplicationModule;
import com.amazingMvp.di.components.ApplicationComponent;
import com.amazingMvp.di.components.DaggerApplicationComponent;
import com.facebook.drawee.backends.pipeline.Fresco;

public class AmazingMvpApplication extends Application {

  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    initializeDependencyInjector().inject(this);
    Fresco.initialize(getApplicationContext());
  }

  @Override public void onTerminate() {
    Fresco.shutDown();
    super.onTerminate();
  }

  private ApplicationComponent initializeDependencyInjector() {
    if (applicationComponent == null) {
      applicationComponent = DaggerApplicationComponent.builder()
          .applicationModule(new ApplicationModule(this))
          .build();
    }
    return applicationComponent;
  }

  public ApplicationComponent component() {
    return applicationComponent;
  }

}
