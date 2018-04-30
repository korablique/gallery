package com.example.korablique.catsearch.imagesearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageInfo {
    @SerializedName("contentUrl")
    @Expose
    private String contentUrl;

    @SerializedName("encodingFormat")
    @Expose
    private String encodingFormat;

    @SerializedName("thumbnailUrl")
    @Expose
    private String thumbnailUrl;


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
}
