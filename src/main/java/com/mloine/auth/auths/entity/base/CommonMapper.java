package com.mloine.auth.auths.entity.base;

import tk.mybatis.mapper.common.*;

/**
 * Author     : Luda Zhuang
 * Date       : 2017/12/16
 * Version    :
 * Description:
 */
public interface CommonMapper<T> extends
        BaseMapper<T>,
        ConditionMapper<T>,
        RowBoundsMapper<T>,
        IdsMapper<T>,
        MySqlMapper<T>,
        Marker {
}
