package com.example.pomslr.model.api;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class Article {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("featured")
        @Expose
        private Boolean featured;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("imageUrl")
        @Expose
        private String imageUrl;
        @SerializedName("newsSite")
        @Expose
        private String newsSite;
        @SerializedName("summary")
        @Expose
        private String summary;
        @SerializedName("publishedAt")
        @Expose
        private String publishedAt;
        @SerializedName("launches")
        @Expose
        private List<Launch> launches = null;
        @SerializedName("events")
        @Expose
        private List<Event> events = null;

        public Article(Integer id, String title, String imageUrl, String newsSite, String summary, String publishedAt) {
            this.id = id;
            this.title = title;
            this.imageUrl = imageUrl;
            this.newsSite = newsSite;
            this.summary = summary;
            this.publishedAt = publishedAt;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Boolean getFeatured() {
            return featured;
        }

        public void setFeatured(Boolean featured) {
            this.featured = featured;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getNewsSite() {
            return newsSite;
        }

        public void setNewsSite(String newsSite) {
            this.newsSite = newsSite;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public List<Launch> getLaunches() {
            return launches;
        }

        public void setLaunches(List<Launch> launches) {
            this.launches = launches;
        }

        public List<Event> getEvents() {
            return events;
        }

        public void setEvents(List<Event> events) {
            this.events = events;
        }

    }
