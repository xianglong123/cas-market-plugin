package com.cas.enums;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2023/2/10 6:43 下午
 * @desc
 */
public enum MarketPropertyEnum {

    /**
     *  订单编号 长度1-32
     */
    ID("订单编号", "id", "^.{1,32}"),
    /**
     * 手机号 长度 11
     */
    MOBILE_NO("手机号", "mobileNo", "^1\\d{10}"),
    ACTOR_TIME("参与时间", "actorTime", ""),
    BEHAVIOR_DATA("行为数据", "behaviorData", ""),
    BEHAVIOR_CODE("行为码", "behaviorCode", ""),
    ;

    MarketPropertyEnum(String name, String val, String regular) {
        this.name = name;
        this.val = val;
        this.regular = regular;
    }

    private String name;

    private String val;

    /**
     * 粗略正则匹配
     */
    private String regular;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
