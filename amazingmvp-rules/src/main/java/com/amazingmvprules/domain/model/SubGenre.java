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
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer;

@JsonObject
@Table(database = AppDatabase.class)
public class SubGenre extends BaseModel implements Parcelable {

  public static final Parcelable.Creator<SubGenre> CREATOR
      = new Parcelable.Creator<SubGenre>() {
    public SubGenre createFromParcel(Parcel in) {
      return new SubGenre(in);
    }
    public SubGenre[] newArray(int size) {
      return new SubGenre[size];
    }
  };

  @PrimaryKey(autoincrement = true) int id;
  @Column @JsonField(name = "title") String title;
  @Column @JsonField(name = "image") String image;
  @Column @JsonField(name = "details") String details;

  public SubGenre() {
    super();
  }

  public SubGenre(Parcel in) {
    id = in.readInt();
    title = in.readString();
    image = in.readString();
    details = in.readString();
  }

  public SubGenre(String title, String image, String details) {
    super();
    this.title = title;
    this.image = image;
    this.details = details;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel parcel, int i) {
    parcel.writeInt(id);
    parcel.writeString(title);
    parcel.writeString(image);
    parcel.writeString(details);
  }


  @ForeignKey(saveForeignKeyModel = false) ForeignKeyContainer<Genre> queenForeignKeyContainer;

  /**
   * Example of setting the model for the queen.
   */
  public void associateQueen(Genre genre) {
    queenForeignKeyContainer = FlowManager
        .getContainerAdapter(Genre.class)
        .toForeignKeyContainer(genre);
  }

}
