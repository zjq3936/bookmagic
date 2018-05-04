package com.cynick.bookmagic.service;

import com.cynick.bookmagic.entity.Content;
/**
 * 
 * @author CyNick
 * @date 2018Äê5ÔÂ4ÈÕ
 */
public interface ContentService {
    int deleteByPrimaryKey(Long id);

    int insert(Content record);

    int insertSelective(Content record);

    Content selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKeyWithBLOBs(Content record);

    int updateByPrimaryKey(Content record);
    
    void getBooksContent();
}