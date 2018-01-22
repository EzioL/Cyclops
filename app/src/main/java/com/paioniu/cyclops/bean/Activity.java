package com.paioniu.cyclops.bean;

import java.util.List;

/**
 * Here be dragons
 * Created by Ezio on 2018/1/22 下午2:46
 */

public class Activity {

    /**
     * b2cOpened : true
     * cityId : 1
     * type : 1
     * duration :
     * activitySeatMapOpened : false
     * campaigns : []
     * crossPoster : http://7xllta.com1.z0.glb.clouddn.com/crossPoster/474aa859feb8a134e7a70caf397c865e1f3d3746.jpg
     * venueId : 3
     * canBuyEcard : false
     * id : 38880
     * openTime : 1516263844000
     * publishStatus : 1
     * events : []
     * timeRange :
     * star : 0
     * venueCityId : 1
     * subCategoryId : 7
     * shortname :
     * areaTicketType : 2
     * soldOut : true
     * searchable : 1
     * venueName : 新光影艺苑—悬疑剧场
     * actorId : 0
     * name : 测试报名活动003
     * ticketCategories : []
     * activityType : 1
     * poster : http://7xllta.com1.z0.glb.clouddn.com/poster/1cd2d601e9b8c1f4bd5a366abcf032b24d729d46.jpg
     * categoryId : 2
     * status : 1
     * desc : 这是一段演出简介
     */

    private boolean b2cOpened;
    private int cityId;
    private int type;
    private String duration;
    private boolean activitySeatMapOpened;
    private String crossPoster;
    private int venueId;
    private boolean canBuyEcard;
    private int id;
    private long openTime;
    private int publishStatus;
    private String timeRange;
    private int star;
    private int venueCityId;
    private int subCategoryId;
    private String shortname;
    private int areaTicketType;
    private boolean soldOut;
    private int searchable;
    private String venueName;
    private int actorId;
    private String name;
    private int activityType;
    private String poster;
    private int categoryId;
    private int status;
    private String desc;
    private List<?> campaigns;
    private List<?> events;
    private List<?> ticketCategories;

    public boolean isB2cOpened() {
        return b2cOpened;
    }

    public void setB2cOpened(boolean b2cOpened) {
        this.b2cOpened = b2cOpened;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isActivitySeatMapOpened() {
        return activitySeatMapOpened;
    }

    public void setActivitySeatMapOpened(boolean activitySeatMapOpened) {
        this.activitySeatMapOpened = activitySeatMapOpened;
    }

    public String getCrossPoster() {
        return crossPoster;
    }

    public void setCrossPoster(String crossPoster) {
        this.crossPoster = crossPoster;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public boolean isCanBuyEcard() {
        return canBuyEcard;
    }

    public void setCanBuyEcard(boolean canBuyEcard) {
        this.canBuyEcard = canBuyEcard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(long openTime) {
        this.openTime = openTime;
    }

    public int getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(int publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getVenueCityId() {
        return venueCityId;
    }

    public void setVenueCityId(int venueCityId) {
        this.venueCityId = venueCityId;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public int getAreaTicketType() {
        return areaTicketType;
    }

    public void setAreaTicketType(int areaTicketType) {
        this.areaTicketType = areaTicketType;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public void setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
    }

    public int getSearchable() {
        return searchable;
    }

    public void setSearchable(int searchable) {
        this.searchable = searchable;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActivityType() {
        return activityType;
    }

    public void setActivityType(int activityType) {
        this.activityType = activityType;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<?> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<?> campaigns) {
        this.campaigns = campaigns;
    }

    public List<?> getEvents() {
        return events;
    }

    public void setEvents(List<?> events) {
        this.events = events;
    }

    public List<?> getTicketCategories() {
        return ticketCategories;
    }

    public void setTicketCategories(List<?> ticketCategories) {
        this.ticketCategories = ticketCategories;
    }
}
