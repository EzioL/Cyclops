package com.paioniu.cyclops.bean;

import java.util.List;

/**
 * Here be dragons
 * Created by Ezio on 2018/1/22 下午2:52
 */

public class PNRep {

    /**
     * data : [{"b2cOpened":true,"cityId":1,"type":1,"duration":"","activitySeatMapOpened":false,"campaigns":[],"venueId":3,"canBuyEcard":false,"id":38835,"openTime":1516174274000,"publishStatus":1,"events":[],"timeRange":"","star":0,"venueCityId":1,"subCategoryId":40,"shortname":"测试报名活动003","areaTicketType":2,"soldOut":true,"searchable":1,"venueName":"新光影艺苑\u2014悬疑剧场","actorId":0,"name":"测试报名活动003测试报名活动003","ticketCategories":[],"activityType":1,"poster":"http://7xllta.com1.z0.glb.clouddn.com/poster/21e94c7d6e37cdf5eaa13f1b3edc33c4a2f3e9ee.jpg","categoryId":4,"status":1,"desc":"这是一段活动简介"},{"b2cOpened":true,"cityId":1,"type":1,"duration":"","activitySeatMapOpened":false,"campaigns":[],"crossPoster":"http://7xllta.com1.z0.glb.clouddn.com/crossPoster/474aa859feb8a134e7a70caf397c865e1f3d3746.jpg","venueId":3,"canBuyEcard":false,"id":38880,"openTime":1516263844000,"publishStatus":1,"events":[],"timeRange":"","star":0,"venueCityId":1,"subCategoryId":7,"shortname":"","areaTicketType":2,"soldOut":true,"searchable":1,"venueName":"新光影艺苑\u2014悬疑剧场","actorId":0,"name":"测试报名活动003","ticketCategories":[],"activityType":1,"poster":"http://7xllta.com1.z0.glb.clouddn.com/poster/1cd2d601e9b8c1f4bd5a366abcf032b24d729d46.jpg","categoryId":2,"status":1,"desc":"这是一段演出简介"},{"b2cOpened":true,"cityId":2,"type":1,"duration":"","activitySeatMapOpened":false,"campaigns":[],"crossPoster":"http://7xllta.com1.z0.glb.clouddn.com/crossPoster/474aa859feb8a134e7a70caf397c865e1f3d3746.jpg","venueId":226,"canBuyEcard":false,"id":38800,"openTime":1516100991000,"publishStatus":1,"events":[],"timeRange":"2018.01.15-2018.01.17","star":0,"venueCityId":2,"subCategoryId":2,"shortname":"","areaTicketType":2,"soldOut":true,"searchable":1,"venueName":"北京金台夕照会馆","actorId":0,"name":"测试报名活动002测试报名活动002","ticketCategories":[],"activityType":1,"poster":"http://7xllta.com1.z0.glb.clouddn.com/poster/6414a048982a338da2d7105893b761b49aa45a18.jpg","categoryId":1,"status":1,"desc":"这是一段活动简介"}]
     * pageIndex : 1 totalNum : 3 hasMore : false pageSize : 3
     */

    private int pageIndex;
    private int totalNum;
    private boolean hasMore;
    private int pageSize;
    private List<Activity> data;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Activity> getData() {
        return data;
    }

    public void setData(List<Activity> data) {
        this.data = data;
    }
}