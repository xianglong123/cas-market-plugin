package com.cas.enums;


/**
 * @author xiang_long
 * @version 1.0
 * @date 2023/2/6 10:43 上午
 * @desc
 */
public enum MarketTypeEnum {

    /**
     * 内部活动
     */
    ACTIVE_1("活动1", "1001"),
    ACTIVE_2("活动2", "1002"),
    ACTIVE_3("活动3", "1003"),
    ACTIVE_4("活动4", "1004"),

    /**
     * 外部活动
     */
    ACTIVE_N("活动N", "0005"),

    /**
     * 外部活动 统一采用COMMON
     */
    COMMON("公共活动", "8888"),
    ERROR("未知活动", "9999"),
    ;

    /**
     * 营销名称/用户行为
     */
    private final String marketName;

    /**
     * 行为码
     */
    private final String code;


    public String getCode() {
        return code;
    }

    public String getMarketName() {
        return marketName;
    }

    MarketTypeEnum(String marketName, String code) {
        this.marketName = marketName;
        this.code = code;
    }

    public static MarketTypeEnum queryByCode(String code) {
        MarketTypeEnum[] enums = values();
        for (MarketTypeEnum typeEnum : enums) {
            if (typeEnum.getCode().equals(code)) {
                return typeEnum;
            }
        }
        return null;
    }
}
