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
package com.amazingmvprules.domain.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;
import org.parceler.ParcelProperty;

@Parcel
public class Genre {

  public static final String TITLE = "title";
  public static final String IMAGE = "image";
  public static final String DETAILS = "details";

  @ParcelProperty(TITLE) String title;
  @ParcelProperty(IMAGE) String image;
  @ParcelProperty(DETAILS) String details;

  public Genre() { }

  @ParcelConstructor
  public Genre(
      @ParcelProperty(TITLE) String title,
      @ParcelProperty(IMAGE) String image,
      @ParcelProperty(DETAILS) String details) {
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

}
