package com.aojiaoo.modules.kissPlan.entity;

import com.aojiaoo.core.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper=true)
@Data
@Table(name = "kp_message")
public class Message extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public Message() {
    }

    public Message(Integer id) {
        this.id=id;
    }

    /**
     * 
     * 表字段： kp_message.id
     */
    @Id
    @Column(name = "id")
    private Integer id;
    /**
     * 
     * 表字段： kp_message.content
     */
    @Column(name = "content")
    private String content;
    /**
     * 谁发送的
     * 表字段： kp_message.from
     */
    @Column(name = "from")
    private Integer from;
    /**
     * 发送给谁
     * 表字段： kp_message.to
     */
    @Column(name = "to")
    private Integer to;
    /**
     * 是否已读 1 已读 0未读
     * 表字段： kp_message.is_read
     */
    @Column(name = "is_read")
    private Byte isRead;

}