package com.cynick.bookmagic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cynick.bookmagic.dao.BookMapper;
import com.cynick.bookmagic.dao.ContentMapper;
import com.cynick.bookmagic.entity.Book;
import com.cynick.bookmagic.entity.Content;
import com.cynick.bookmagic.service.ContentService;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

@Service
public class ContentServiceImpl implements ContentService {

	@Qualifier("QidianContentPageProcessor")
	@Autowired
	private PageProcessor qidianContentPageProcessor;

	@Qualifier("MySqlContentPipeline")
	@Autowired
	private Pipeline mySqlContentPipeline;

	@Autowired
	private ContentMapper contentMapper;

	@Autowired
	private BookMapper bookMapper;

	public int deleteByPrimaryKey(Long id) {
		return contentMapper.deleteByPrimaryKey(id);
	}

	public int insert(Content record) {
		return contentMapper.insert(record);
	}

	public int insertSelective(Content record) {
		return contentMapper.insertSelective(record);
	}

	public Content selectByPrimaryKey(Long id) {
		return contentMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(Content record) {
		return contentMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKeyWithBLOBs(Content record) {
		return contentMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	public int updateByPrimaryKey(Content record) {
		return contentMapper.updateByPrimaryKey(record);
	}

	@Override
	public void getBooksContent() {

		int count = bookMapper.getCount();
		// 设置每页条数
		int pageSize = 200;
		// 设置�?共多少页
		int pageNum = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		try {
			for (int i = 0; i < pageNum; i++) {
				List<Book> list = bookMapper.getAll(i, pageSize);
				for (Book book : list) {
					Spider.create(qidianContentPageProcessor)
							// �?"https://github.com/code4craft"�?始抓
							.addUrl("http:" + book.getSourceUrl())
							//设置在本地数据库中的ID
							.setUUID(String.valueOf(book.getId()))
							// 数据集保存到mysql
							.addPipeline(mySqlContentPipeline)
							// �?�?5个线程抓�?
							.thread(1)
							// 启动爬虫
							.run();

					Thread.sleep(500);

				}
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
