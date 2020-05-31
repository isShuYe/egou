package com.ego.commons;

public class UploadResult {
    private int error;
    private String url;
    private String message;

    public int getError() {
        return this.error;
    }

    public void setError(final int error) {
        this.error = error;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
