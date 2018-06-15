package com.cynick.bookmagic.dao;

import org.springframework.stereotype.Repository;

import com.cynick.bookmagic.entity.RelationshipVoExample;
import com.cynick.bookmagic.entity.RelationshipVoKey;

@Repository
public interface RelationshipVoMapper {
    long countByExample(RelationshipVoExample example);


    int insert(RelationshipVoKey record);

}