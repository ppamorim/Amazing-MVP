package com.amazingmvprules.domain.util;

import com.amazingmvprules.domain.model.SubGenre;
import com.bluelinelabs.logansquare.LoganSquare;
import com.bluelinelabs.logansquare.typeconverters.StringBasedTypeConverter;
import java.io.IOException;
import java.util.List;

public class LoganSubGenreTypeConverter extends StringBasedTypeConverter<List<SubGenre>> {

  @Override public List<SubGenre> getFromString(String string) {
    try {
      if (string != null) {
        return LoganSquare.parseList(string, SubGenre.class);
      } else {
        return null;
      }
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override public String convertToString(List<SubGenre> object) {
    try {
      return LoganSquare.serialize(object);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}