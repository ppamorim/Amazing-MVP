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

  private String title;
  private String image;
  private String details;

  public Genre() {
    super();
  }

  public Genre(Parcel in) {
    title = in.readString();
    image = in.readString();
    details = in.readString();
  }

  public Genre(String title, String image, String details) {
    super();
    this.title = title;
    this.image = image;
    this.details = details;
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
    parcel.writeString(title);
    parcel.writeString(image);
    parcel.writeString(details);
  }

}
