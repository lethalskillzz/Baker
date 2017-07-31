package com.lethalskillzz.baker.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;


/**
 * Created by ibrahimabdulkadir on 23/06/2017.
 */

@AutoValue
public abstract class Step implements Parcelable {
  public abstract int id();
  public abstract String shortDescription();
  public abstract String description();
  public abstract String videoURL();
  public abstract String thumbnailURL();

  public static Builder builder() {
    return new $$AutoValue_Step.Builder();
  }

  public static TypeAdapter<Step> typeAdapter(Gson gson) {
    return new $AutoValue_Step.GsonTypeAdapter(gson);
  }

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder id(int id);
    public abstract Builder shortDescription(String shortDescription);
    public abstract Builder description(String description);
    public abstract Builder videoURL(String videoURL);
    public abstract Builder thumbnailURL(String thumbnailURL);

    public abstract Step build();
  }

  public static class ListTypeAdapter implements com.ryanharter.auto.value.parcel.TypeAdapter<List<Step>> {

    @Override
    public List<Step> fromParcel(Parcel in) {
      return in.createTypedArrayList(new Parcelable.Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel source) {
          return AutoValue_Step.CREATOR.createFromParcel(source);
        }

        @Override
        public Step[] newArray(int size) {
          return new Step[size];
        }
      });
    }

    @Override
    public void toParcel(List<Step> value, Parcel dest) {
      dest.writeTypedList(value);
    }

  }
}
