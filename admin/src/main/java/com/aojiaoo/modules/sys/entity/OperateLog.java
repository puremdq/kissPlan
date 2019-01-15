package com.aojiaoo.modules.sys.entity;

import com.aojiaoo.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import com.aojiaoo.core.mybatis.enums.IdType;
import com.aojiaoo.core.mybatis.annotations.TableId;

@EqualsAndHashCode(callSuper=true)
@Data
@Table(name = "sys_operate_log")
public class OperateLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public OperateLog() {
    }

    public OperateLog(Long id) {
        this.id=id;
    }

    /**
     * 主键
     * 表字段： sys_operate_log.id
     */
    @Id
    @Column(name = "id")
    private Long id;
    /**
     * 操作的模块名称（自定义指定）
     * 表字段： sys_operate_log.operate_name
     */
    @Column(name = "operate_name")
    private String operateName;
    /**
     * 操作描述
     * 表字段： sys_operate_log.operate_desc
     */
    @Column(name = "operate_desc")
    private String operateDesc;
    /**
     * 用户id
     * 表字段： sys_operate_log.ip
     */
    @Column(name = "ip")
    private String ip;
    /**
     * 访问URL
     * 表字段： sys_operate_log.url
     */
    @Column(name = "url")
    private String url;
    /**
     * 请求方式
     * 表字段： sys_operate_log.request_type
     */
    @Column(name = "request_type")
    private String requestType;
    /**
     * 方法名
     * 表字段： sys_operate_log.method_name
     */
    @Column(name = "method_name")
    private String methodName;
    /**
     * 参数
     * 表字段： sys_operate_log.parameters
     */
    @Column(name = "parameters")
    private String parameters;
    /**
     * 执行时间
     * 表字段： sys_operate_log.execute_time
     */
    @Column(name = "execute_time")
    private String executeTime;
    /**
     * 是否执行成功成功
     * 表字段： sys_operate_log.is_success
     */
    @Column(name = "is_success")
    private String isSuccess;

}