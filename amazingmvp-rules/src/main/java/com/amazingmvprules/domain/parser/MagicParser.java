package com.amazingmvprules.domain.parser;

import android.support.v4.util.ArrayMap;
import com.bluelinelabs.logansquare.LoganSquare;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class MagicParser<T> {

  public ArrayMap<Integer, T> jsonToArrayMap(InputStream inputStream, Class clazz) throws
      IOException {
    Map<Integer, T> map = LoganSquare.parseMap(inputStream, clazz);
    ArrayMap<Integer, T> arrayMap = new ArrayMap<>();
    arrayMap.putAll(map);
    map.clear();
    return arrayMap;
  }

}
