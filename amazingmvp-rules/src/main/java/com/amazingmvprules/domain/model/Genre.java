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
import com.amazingmvprules.domain.database.AppDatabase;
import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;
import java.util.ArrayList;
import java.util.List;

@ModelContainer
@JsonObject
@Table(database = AppDatabase.class)
public class Genre extends BaseModel implements Parcelable {

  public static final Parcelable.Creator<Genre> CREATOR
      = new Parcelable.Creator<Genre>() {
    public Genre createFromParcel(Parcel in) {
      return new Genre(in);
    }
    public Genre[] newArray(int size) {
      return new Genre[size];
    }
  };

  @PrimaryKey @JsonField(name = "id") Long id;
  @Column @JsonField(name = "title") String title;

  /**
   * This list MUST be typed or LoganSquare cannot work.
   */
  @JsonField(name = "subgenres")
  //@Column(typeConverter = SubGenreTypeConverter.class) List<SubGenre> subGenres;
  List<SubGenre> subGenres;

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
        this.subGenres.add((SubGenre)o);
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

  @OneToMany(methods = { OneToMany.Method.SAVE, OneToMany.Method.DELETE}, variableName = "subGenres")
  public List<SubGenre> getSubGenres() {
    if (subGenres == null || subGenres.isEmpty()) {
      subGenres = SQLite.select()
          .from(SubGenre.class)
          .queryList();
    }
    return subGenres;
  }

}
