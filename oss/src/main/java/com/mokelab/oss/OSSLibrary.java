package com.mokelab.oss;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * OSSLibrary
 */
public class OSSLibrary implements Parcelable {
    private String name;
    private String fileNameInAsset;

    public OSSLibrary(@NonNull String name, @NonNull String assetName) {
        this.name = name;
        this.fileNameInAsset = assetName;
    }

    private OSSLibrary(Parcel in) {
        name = in.readString();
        fileNameInAsset = in.readString();
    }

    public String getName() {
        return name;
    }

    public String getFileNameInAsset() {
        return fileNameInAsset;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(fileNameInAsset);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OSSLibrary> CREATOR = new Creator<OSSLibrary>() {
        @Override
        public OSSLibrary createFromParcel(Parcel in) {
            return new OSSLibrary(in);
        }

        @Override
        public OSSLibrary[] newArray(int size) {
            return new OSSLibrary[size];
        }
    };
}
