package com.amazingmvprules.domain.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import java.util.ArrayList;

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

  @JsonField(name = "id") private int id;
  @JsonField(name = "title") private String title;
  @JsonField(name = "subgenres") private ArrayList<SubGenre> subGenres;

  public Genre() {
    super();
  }

  public Genre(Parcel in) {
    this.id = in.readInt();
    this.title = in.readString();
    ArrayList arrayList = in.readArrayList(SubGenre.class.getClassLoader());
    this.subGenres = new ArrayList<>(arrayList.size());
    for (Object o : arrayList) {
      if (o instanceof SubGenre) {
        this.subGenres.add((SubGenre) o);
      }
    }
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ArrayList<SubGenre> getSubGenres() {
    return subGenres;
  }

  public void setSubGenres(ArrayList<SubGenre> subGenres) {
    this.subGenres = subGenres;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel parcel, int i) {
    parcel.writeInt(id);
    parcel.writeString(title);
    parcel.writeList(subGenres);
  }

}
