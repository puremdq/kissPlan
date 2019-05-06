package com.aojiaoo.core.base;

import com.aojiaoo.core.mybatis.plugins.paging.Page;
import com.aojiaoo.utils.IdUtil;
import com.aojiaoo.utils.StringUtils;
import com.aojiaoo.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class BaseService<E extends BaseEntity, M extends BaseMapper<E>> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected M mapper;

    public M getMapper() {
        return mapper;
    }

    public E get(E entity) {
        return this.mapper.selectByPrimaryKey(entity);
    }

    public E get(Integer id) {
        if (id == null || id <= 0) {
            logger.warn("根据id查找失败传入的id不能为空或者小于0");
            return null;
        }
        return this.mapper.selectByPrimaryKey(id);
    }

    public E get(String id) {
        if (StringUtils.isBlank(id)) {
            logger.warn("根据id查找失败传入的id不能为空");
            return null;
        }
        return this.mapper.selectByPrimaryKey(id);
    }

    /**
     * 保存 默认是带有Selective的保存 当字段为空时不做更新和插入
     *
     * @param entity entity
     * @return 是否保存成
     */
    @Transactional
    public boolean save(E entity) {
        return this.save(entity, true);
    }

    @Transactional
    public boolean save(E entity, boolean isSelective) {

        if (null == entity) {
            return false;
        }

        if (entity.isAllIdHaveValue()) {
            E oldData = this.mapper.selectByPrimaryKey(entity);
            //不存在数据 插入
            if (oldData == null) {
                return this.insert(entity, isSelective);
            }
            //存在数据
            if (entity.equals(oldData)) {
                return true;
            } else {
                //存在数据 且此数据与原数据不相等
                return this.update(entity, isSelective);
            }
        }
        //不存在id直接插入
        return this.insert(entity, isSelective);
    }


    @Transactional
    public boolean update(E entity) {
        return this.update(entity, true);
    }

    @Transactional
    public boolean update(E entity, boolean isSelective) {

        if (!entity.isAllIdHaveValue()) {
            logger.warn("更新{}失败,传入的entity不包含主键,或者主键为空", entity);
            return false;
        }

        entity.setUpdateDate(new Date());
        entity.setUpdateBy(UserUtil.getCurrentUserId());
        if (isSelective) {
            return this.mapper.updateByPrimaryKeySelective(entity) > 0;
        } else {
            return this.mapper.updateByPrimaryKey(entity) > 0;
        }
    }

    @Transactional
    public boolean insert(E entity) {
        return this.insert(entity, true);
    }

    @Transactional
    public boolean insert(E entity, boolean isSelective) {

        entity.setCreateDate(new Date());
        entity.setUpdateDate(new Date());
        if (!IdUtil.isValidId(entity.getCreateBy())) {
            entity.setCreateBy(UserUtil.getCurrentUserId());
        }
        entity.setUpdateBy(entity.getCreateBy());
        List<Field> idFields = entity.getIdFields();

        //自动生成主键
        if (idFields != null && idFields.size() > 0) {
            try {
                for (Field field : idFields) {
                    javax.persistence.Id tableId = field.getAnnotation(javax.persistence.Id.class);
                    if (tableId == null) {
                        logger.warn("插入失败,传入的主键field 不包含主键注解");
                        return false;
                    }
//                    switch (tableId.type()) {
//                        case INPUT:
//                            if (field.get(entity) == null || field.get(entity).toString().length() < 1) {
//                                logger.error("插入失败,传入的entity的id类型为INPUT,但却未为其id指定值");
//                                return false;
//                            }
//                            break;
//                        case UUID:
//                            field.setAccessible(true);
//                            if (field.get(entity) != null && field.get(entity).toString().length() > 0) {
//                                logger.error("插入失败,传入的entity{}的id类型为UUID,但却已为其id指定值", entity);
//                                return false;
//                            }
//                            field.set(entity, IdGenerator.uuid());
//                            break;
//                        case AUTO:
//                            field.setAccessible(true);
//                            if (field.get(entity) != null) {
//                                logger.error("插入失败,传入的entity{}的id类型为AUTO,但在插入时却已为其id指定值", entity);
//                                return false;
//                            }
//                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        if (isSelective) {
            return this.mapper.insertSelective(entity) > 0;
        }
        return this.mapper.insert(entity) > 0;

    }

    public List<E> findList(E entity) {
        return this.mapper.selectBySelective(entity);
    }


    public Page<E> findPage(E entity, Page<E> page) {
//        RowBounds rowBounds = new RowBounds(page.getOffset(), page.getPageSize());
//        page.setList(this.mapper.selectByRowBounds(entity, rowBounds));
        page.setList(this.mapper.selectBySelective(page, entity));
        return page;
    }

    /**
     * @param entity    要删除的对象
     * @param isPhysics 是否物理删除
     * @return
     */
    @Transactional
    public boolean delete(E entity, boolean isPhysics) {
        if (isPhysics) {
            //物理删除
            return this.mapper.deleteByPrimaryKey(entity) > 0;
        } else {
            entity.setDelFlag(BaseEntity.DEL_FLAG_DELETED);
            return this.mapper.updateByPrimaryKeySelective(entity) > 0;
        }
    }

    public boolean delete(E entity) {
        return this.delete(entity, false);
    }

}
