package com.lethalskillzz.bakingapp.data.model;

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
public abstract class Ingredient implements Parcelable {

  public abstract float quantity();
  public abstract String measure();
  public abstract String ingredient();

  public static Builder builder() {
    return new AutoValue_Ingredient.Builder();
  }

  public static TypeAdapter<Ingredient> typeAdapter(Gson gson) {
    return new AutoValue_Ingredient.GsonTypeAdapter(gson);
  }

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder quantity(float quantity);
    public abstract Builder measure(String measure);
    public abstract Builder ingredient(String ingredient);

    public abstract Ingredient build();
  }

    public static final class ListTypeAdapter implements com.ryanharter.auto.value.parcel.TypeAdapter<List<Ingredient>> {
        @Override
        public List<Ingredient> fromParcel(Parcel in) {
            return in.createTypedArrayList(new Creator<Ingredient>() {
                @Override
                public Ingredient createFromParcel(Parcel source) {
                    return AutoValue_Ingredient.CREATOR.createFromParcel(source);
                }

                @Override
                public Ingredient[] newArray(int size) {
                    return new Ingredient[size];
                }
            });
        }

        @Override
        public void toParcel(List<Ingredient> value, Parcel dest) {
            dest.writeTypedList(value);
        }
    }
}