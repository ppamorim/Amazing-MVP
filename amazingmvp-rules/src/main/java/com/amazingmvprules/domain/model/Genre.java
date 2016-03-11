/*
* Copyright (C) 2016 Pedro Paulo de Amorim
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
package com.amazingmvprules.domain.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import java.util.ArrayList;
import java.util.List;

@JsonObject
public class Genre implements Parcelable {

  public static final Parcelable.Creator<Genre> CREATOR
      = new Parcelable.Creator<Genre>() {
    public Genre createFromParcel(Parcel in) {
      return new Genre(in);
    }
    public Genre[] newArray(int size) {
      return new Genre[size];
    }
  };

  @JsonField(name = "id") private Long id;
  @JsonField(name = "title") private String title;
  @JsonField(name = "subgenres") List<SubGenre> subGenres;

  public Genre() {
    super();
  }

  public Genre(Parcel in) {
    this.id = in.readLong();
    this.title = in.readString();
    ArrayList arrayList = in.readArrayList(SubGenre.class.getClassLoader());
    this.subGenres = new ArrayList<>(arrayList.size());
    for (Object o : arrayList) {
      if (o instanceof SubGenre) {
        this.subGenres.add((SubGenre) o);
      }
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<SubGenre> getSubGenres() {
    return subGenres;
  }

  public void setSubGenres(ArrayList<SubGenre> subGenres) {
    this.subGenres = subGenres;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel parcel, int i) {
    parcel.writeLong(id);
    parcel.writeString(title);
    parcel.writeList(subGenres);
  }

}
