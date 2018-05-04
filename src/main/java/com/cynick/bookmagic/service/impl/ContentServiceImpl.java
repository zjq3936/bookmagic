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
/**
 * 
 * @author CyNick
 * @date 2018年5月4日
 */
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
		// 设置每页的条数
		int pageSize = 200;
		// 计算一共有多少页
		int pageNum = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		try {
			for (int i = 0; i < pageNum; i++) {
				List<Book> list = bookMapper.getAll(i, pageSize);
				for (Book book : list) {
					Spider.create(qidianContentPageProcessor)
							// 设置抓取开始的url
							.addUrl("http:" + book.getSourceUrl())
							//设置父节点的ID，当前指 图书ID
							.setUUID(String.valueOf(book.getId()))
							// 讲获取的章节信息保存到mysql
							.addPipeline(mySqlContentPipeline)
							// 设置爬取的线程数量
							.thread(1)
							// 开启爬虫
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
