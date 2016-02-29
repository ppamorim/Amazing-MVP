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
package com.amazingmvprules.domain.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

public class ThreadExecutor implements InteractorExecutor {
  private static final int CORE_POOL_SIZE = 3;
  private static final int MAX_POOL_SIZE = 5;
  private static final int KEEP_ALIVE_TIME = 120;
  private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
  private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<>();
  private ThreadPoolExecutor threadPoolExecutor;
  @Inject ThreadExecutor() {
    threadPoolExecutor =
        new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TIME_UNIT,
            WORK_QUEUE);
  }
  @Override public void run(final Interactor interactor) {
    if (interactor == null) {
      throw new IllegalArgumentException("Interactor must not be null");
    }
    threadPoolExecutor.submit(new Runnable() {
      @Override public void run() {
        interactor.run();
      }
    });
  }
}
