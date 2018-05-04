package com.cynick.bookmagic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cynick.bookmagic.dao.BookMapper;
import com.cynick.bookmagic.entity.Book;
import com.cynick.bookmagic.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 
 * @author CyNick
 * @date 2018年5月4日
 */
@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookMapper bookMapper;

	public int deleteByPrimaryKey(Long id) {
		return bookMapper.deleteByPrimaryKey(id);
	}

	public int insert(Book record) {
		return bookMapper.insert(record);
	}

	public int insertSelective(Book record) {
		return bookMapper.insertSelective(record);
	}

	public Book selectByPrimaryKey(Long id) {
		return bookMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(Book record) {
		return bookMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Book record) {
		return bookMapper.updateByPrimaryKey(record);
	}

	public List<Book> getAllBook(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Book> list = bookMapper.getAll(pageNum, pageSize);
		PageInfo<Book> pageInfo = new PageInfo<>(list);
		return pageInfo.getList();
	}

	@Override
	public int saveBooks(List<Book> list) {
		for(Book book : list){
			//通过 图书名称和 作者判断，当前图书是否存在
			List<Book> booklist = bookMapper.getBookByBookNameAndSourceUrlAndAuthor(book);
			if(booklist.size() == 0){
				bookMapper.insertSelective(book);
			}
		}
		return 0;
	}

}
