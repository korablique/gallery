package com.example.korablique.gallery.imagesearch;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageInfo implements Parcelable {
    @SerializedName("contentUrl")
    @Expose
    private String contentUrl;

    @SerializedName("encodingFormat")
    @Expose
    private String encodingFormat;

    @SerializedName("thumbnailUrl")
    @Expose
    private String thumbnailUrl;


    protected ImageInfo(Parcel in) {
        contentUrl = in.readString();
        encodingFormat = in.readString();
        thumbnailUrl = in.readString();
    }

    public static final Creator<ImageInfo> CREATOR = new Creator<ImageInfo>() {
        @Override
        public ImageInfo createFromParcel(Parcel in) {
            return new ImageInfo(in);
        }

        @Override
        public ImageInfo[] newArray(int size) {
            return new ImageInfo[size];
        }
    };

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getEncodingFormat() {
        return encodingFormat;
    }

    public void setEncodingFormat(String encodingFormat) {
        this.encodingFormat = encodingFormat;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public int describeContents() {
        return contentUrl.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(contentUrl);
        dest.writeString(encodingFormat);
        dest.writeString(thumbnailUrl);
    }
}
