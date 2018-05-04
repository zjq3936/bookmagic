package com.cynick.bookmagic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cynick.bookmagic.entity.Book;
/**
 * 
 * @author Administrator
 *
 */
@Repository
public interface BookMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);
    
    List<Book> getAll(@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize);
    
    List<Book> getBookByBookNameAndSourceUrlAndAuthor(Book book);
    
    int getCount();
    
    int saveBooks(List<Book> list);
}