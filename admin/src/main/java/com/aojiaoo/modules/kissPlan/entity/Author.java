package com.aojiaoo.modules.kissPlan.entity;

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
@Table(name = "kp_author")
public class Author extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public Author() {
    }

    public Author(Integer userId) {
        this.userId=userId;
    }

    /**
     * 
     * 表字段： kp_author.user_id
     */
    @Id
    @Column(name = "user_id")
    private Integer userId;
    /**
     * 关注数
     * 表字段： kp_author.follow_num
     */
    @Column(name = "follow_num")
    private Integer followNum;
    /**
     * 粉丝数
     * 表字段： kp_author.fans_num
     */
    @Column(name = "fans_num")
    private Integer fansNum;
    /**
     * 文章数
     * 表字段： kp_author.article_num
     */
    @Column(name = "article_num")
    private Integer articleNum;
    /**
     * 总共写了多少字
     * 表字段： kp_author.words_num
     */
    @Column(name = "words_num")
    private Integer wordsNum;
    /**
     * 收获点赞数
     * 表字段： kp_author.be_like_num
     */
    @Column(name = "be_like_num")
    private Integer beLikeNum;

}