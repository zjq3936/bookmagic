package com.cynick.bookmagic.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cynick.bookmagic.entity.Content;
/**
 * 
 * @author CyNick
 * @date 2018å¹?5æœ?4æ—?
 */
@Repository
public interface ContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Content record);

    int insertSelective(Content record);

    Content selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKeyWithBLOBs(Content record);

    int updateByPrimaryKey(Content record);
    
    int selectByTitleAndBookId(@Param("title") String title,@Param("bookId") Long bookId);
}