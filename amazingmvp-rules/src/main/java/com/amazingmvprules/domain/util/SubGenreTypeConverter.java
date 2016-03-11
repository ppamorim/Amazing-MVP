package com.amazingmvprules.domain.util;

import com.amazingmvprules.domain.model.SubGenre;
import com.bluelinelabs.logansquare.LoganSquare;
import com.raizlabs.android.dbflow.converter.TypeConverter;
import java.io.IOException;
import java.util.List;

@com.raizlabs.android.dbflow.annotation.TypeConverter
public class SubGenreTypeConverter extends TypeConverter<String, List> {

  @Override
  public String getDBValue(List model) {
    try {
      return LoganSquare.serialize(model);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List getModelValue(String data) {
    try {
      return LoganSquare.parseList(data, SubGenre.class);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

}

