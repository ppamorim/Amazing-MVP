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

  public Genre(Parcel in) {

  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel parcel, int i) {

  }

}
