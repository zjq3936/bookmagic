package com.cynick.bookmagic.service;

import java.util.List;

import com.cynick.bookmagic.entity.Book;
/**
 * 
 * @author CyNick
 * @date 2018Äê5ÔÂ4ÈÕ
 */
public interface BookService {
    int deleteByPrimaryKey(Long id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);
    
    List<Book> getAllBook(int pageNum,int pageSize);
    
    int saveBooks(List<Book> list);
    
    
}