package com.aojiaoo.core.mybatis.enums;


public enum IdType {

    AUTO(0, "数据库ID自增"),
    INPUT(1, "用户输入ID"),
    /* 以下2种类型、只有当插入对象ID 为空，才自动填充。 */
    UUID(3, "全局唯一ID");
    /**
     * 主键
     */
    private final int key;

    /**
     * 描述
     */
    private final String desc;

    IdType(final int key, final String desc) {
        this.key = key;
        this.desc = desc;
    }

    /**
     * <p>
     * 主键策略 （默认 UUID）
     * </p>
     *
     * @param idType ID 策略类型
     * @return
     */
    public static IdType getIdType(int idType) {
        IdType[] its = IdType.values();
        for (IdType it : its) {
            if (it.getKey() == idType) {
                return it;
            }
        }
        return UUID;
    }

    public int getKey() {
        return this.key;
    }

    public String getDesc() {
        return this.desc;
    }



}
