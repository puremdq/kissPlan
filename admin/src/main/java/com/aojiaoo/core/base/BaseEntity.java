package com.aojiaoo.core.base;

import com.aojiaoo.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.Transient;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ToString
@EqualsAndHashCode
public abstract class BaseEntity implements Serializable {
    public static final String DEL_FLAG_NORMAL = "0";
    public static final String DEL_FLAG_DELETED = "1";
    protected Date createDate;

    protected Date updateDate;

    protected Integer createBy;
    protected Integer updateBy;

    protected String remarks;//备注
    @Transient
    @JsonIgnore
    protected String sortBy;//根据什么排序 如时间
    @Transient
    @JsonIgnore
    protected String sortType;//desc asc


    @JsonIgnore
    protected String delFlag = BaseEntity.DEL_FLAG_NORMAL;

    /**
     * @return boolean 判断当前记录的id是否有值 入过没有id 返回false
     */
    @JsonIgnore
    public Boolean isAllIdHaveValue() {

        List<Field> fieldList = this.getIdFields();
        if (fieldList == null || fieldList.size() < 1) {
            return false;
        }

        try {
            for (Field field : fieldList) {
                if (StringUtils.isBlank(BeanUtils.getProperty(this, field.getName()))) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @JsonIgnore
    protected List<Field> getIdFields() {
        List<Field> idFields = new ArrayList<>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(javax.persistence.Id.class) != null) {
                idFields.add(field);
            }
        }
        return idFields;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

}
