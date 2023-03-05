package com.cas.bo;

import java.io.Serializable;
import java.util.Map;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2023/2/6 9:07 下午
 * @desc 这个类型定了就不要改动，容易造成反序列化失败
 */
public class MarketBaseBO implements Serializable {

    private static final long serialVersionUID = 7471391170055841173L;

    /**
     * 流水号
     */
    private String journalNo;

    /**
     * 上游应用名称
     */
    private String applicationName;

    /**
     * 订单编号
     */
    private String id;

    /**
     * 手机号
     */
    private String mobileNo;

    /**
     * 行为码
     */
    private String behaviorCode;

    /**
     * 参与时间
     * @return
     */
    private String actorTime;

    /**
     * 外部行为数据
     */
    private Map<String, String> behaviorData;

    public String getJournalNo() {
        return journalNo;
    }

    public void setJournalNo(String journalNo) {
        this.journalNo = journalNo;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public Map<String, String> getBehaviorData() {
        return behaviorData;
    }

    public void setBehaviorData(Map<String, String> behaviorData) {
        this.behaviorData = behaviorData;
    }

    public String getActorTime() {
        return actorTime;
    }

    public void setActorTime(String actorTime) {
        this.actorTime = actorTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getBehaviorCode() {
        return behaviorCode;
    }

    public void setBehaviorCode(String behaviorCode) {
        this.behaviorCode = behaviorCode;
    }
}
