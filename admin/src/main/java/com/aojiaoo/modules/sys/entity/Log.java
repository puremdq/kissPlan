package com.aojiaoo.modules.sys.entity;

import com.aojiaoo.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.aojiaoo.core.mybatis.enums.IdType;
import com.aojiaoo.core.mybatis.annotations.TableId;

@EqualsAndHashCode(callSuper = true)
@Data
public class Log extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public Log() {
    }

    public Log(Long id) {
        this.id = id;
    }

    /**
     * 主键
     * 表字段： sys_log.id
     */
    @TableId(type = IdType.UUID, value = "id")
    private Long id;
    /**
     * 操作的模块名称（自定义指定）
     * 表字段： sys_log.operate_name
     */
    private String operateName;
    /**
     * 操作描述
     * 表字段： sys_log.operate_desc
     */
    private String operateDesc;
    /**
     * 用户id
     * 表字段： sys_log.ip
     */
    private String ip;
    /**
     * 访问URL
     * 表字段： sys_log.url
     */
    private String url;
    /**
     * 请求方式
     * 表字段： sys_log.request_type
     */
    private String requestType;
    /**
     * 方法名
     * 表字段： sys_log.method_name
     */
    private String methodName;
    /**
     * 参数
     * 表字段： sys_log.parameter
     */
    private String parameter;

}