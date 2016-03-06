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
package com.amazingmvprules.presenter;

import com.amazingmvprules.domain.model.SubGenre;
import java.util.ArrayList;

public interface GenrePresenter extends Presenter {
  void setView(View view);
  SubGenre getGenreAtPosition(int position);
  interface View {
    boolean isReady();
    void renderGenres(ArrayList<SubGenre> subGenres);
  }
}
