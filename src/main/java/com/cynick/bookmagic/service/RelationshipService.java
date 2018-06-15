package com.cynick.bookmagic.service;

import com.cynick.bookmagic.entity.RelationshipVoKey;

/**
 * Created by BlueT on 2017/3/18.
 */
public interface RelationshipService {

    /**
     * 按主键统计条数
     * @param cid
     * @param mid
     * @return 条数
     */
    Long countById(Integer cid, Integer mid);


    /**
     * 保存對象
     * @param relationshipVoKey
     */
    void insertVo(RelationshipVoKey relationshipVoKey);

}
