
package com.mloine.auth.auths.service;



import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mloine.auth.auths.entity.base.BaseEntity;
import com.mloine.auth.auths.entity.base.CommonMapper;
import com.mloine.auth.auths.entity.base.PageInfoVO;
import com.mloine.auth.auths.entity.base.PageVO;
import com.mloine.auth.auths.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public abstract class BaseServiceImpl<D extends CommonMapper<T>, T extends BaseEntity> implements BaseService<T> {
    @Autowired
    protected D dao;

    @Override
    public List<T> list(T entity) {
        return dao.select(entity);
    }

    @Override
    public PageVO<T> list(T entity, PageInfoVO page) {
        Page<T> list = PageHelper.startPage(page.getPageNum(), page.getPageSize()).doSelectPage(() -> dao.select(entity));
        return new PageVO<>(list);
    }

    @Override
    public PageVO<T> listAll(PageInfoVO page) {
        Page<T> list = PageHelper.startPage(page.getPageNum(), page.getPageSize()).doSelectPage(() -> dao.select(null));
        return new PageVO<>(list);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public T findOne(T entity) {
        return dao.selectOne(entity);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public T findById(Object id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int update(T entity) {
        return dao.updateByPrimaryKeySelective(entity);
    }

    @Override
    @Transactional
    public int save(T entity) {
        return dao.insertSelective(entity);
    }

    @Override
    @Transactional
    public int saveList(List<T> list) {
        return dao.insertList(list);
    }

    @Override
    @Transactional
    public int deleteById(Object id) {
        return dao.deleteByPrimaryKey(id);
    }
}
