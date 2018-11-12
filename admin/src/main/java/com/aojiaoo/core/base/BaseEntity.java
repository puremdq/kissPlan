package com.aojiaoo.core.base;

import com.aojiaoo.core.mybatis.annotations.TableId;
import com.aojiaoo.utils.StringUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BaseEntity implements Serializable {

    protected Date createDate;
    protected Date updateDate;


    protected Integer createBy;
    protected Integer updateBy;

    /**
     * @return boolean 判断当前记录的id是否有值 入过没有id 返回false
     */
    public Boolean isAllIdHaveValue() {
        List<Field> fieldList = this.getIdFields();
        if (fieldList == null || fieldList.size() < 1) {
            return false;
        }

        try {
            for (Field field : fieldList) {
                if (StringUtil.isBlank(BeanUtils.getProperty(this, field.getName()))) {
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
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getAnnotation(TableId.class) != null) {
                idFields.add(fields[i]);
            }
        }
        return idFields;
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        updateDate = updateDate;
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


}
