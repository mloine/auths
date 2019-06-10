package com.mloine.auth.auths.service.impl;




import com.mloine.auth.auths.entity.base.BaseEntity;
import com.mloine.auth.auths.entity.base.PageInfoVO;
import com.mloine.auth.auths.entity.base.PageVO;

import java.util.List;

/**
 * 基础 CURD Service
 */
public interface BaseService<T extends BaseEntity> {
    /**
     * 根据查询条件列出数据表中的所有的数据，传入null值则表示获取所有值
     * @param entity 对需要进行约束的条件设置值
     * @return 数据列表
     */
    List<T> list(T entity);

    /**
     * 根据查询条件及分页信息列出数据表中的数据，entity 传入null值则与 PageVO<T> listAll(PageInfoVO page); 方法相同
     * @param entity 对需要进行约束的条件设置值
     * @param page 分页信息，不可为空
     * @return 数据列表
     */
    PageVO<T> list(T entity, PageInfoVO page);

    /**
     * 根据分页信息列出数据表中的所有数据
     * @param page 分页信息，不可为空
     * @return 数据列表
     */
    PageVO<T> listAll(PageInfoVO page);

    /**
     * 根据查询条件查找一条数据，如果数据库中包含多条数据则会报错
     * @param entity 对需要进行约束的条件设置值
     * @return 返回的一条数据，如果数据库中包含多条数据则会报错
     */
    T findOne(T entity);

    /**
     * 根据 主键 获取一条数据
     * @param id 主键
     * @return 返回的一条数据
     */
    T findById(Object id);

    /**
     * 根据主键更新一条数据，entity中为null的字段不更新
     * @param entity 包含id及需要更新的字段
     * @return 影响的行数
     */
    int update(T entity);

    /**
     * 保存一条数据，entity中为null的字段不更新
     * 如果 id 为空则通过数据库自动生成一个主键值并回填至 entity
     * @param entity 要保存的 entity
     * @return 影响的行数
     */
    int save(T entity);

    /**
     * 批量插入数据
     * @param list 数据列表
     * @return 影响的行数
     */
    int saveList(List<T> list);

    /**
     * 根据主键删除一条数据
     * @param id 主键Id
     * @return 影响的行数
     */
    int deleteById(Object id);

}
