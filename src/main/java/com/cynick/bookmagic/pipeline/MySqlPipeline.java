package com.cynick.bookmagic.pipeline;
/**
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cynick.bookmagic.dao.ContentVoMapper;
import com.cynick.bookmagic.entity.Book;
import com.cynick.bookmagic.service.BookService;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
/**
 * 图书信息入库
 * @author CyNick
 * @date 2018年5月4日
 */
@Component("MySqlPipeline")
public class MySqlPipeline implements Pipeline{
	
	@Autowired
	private BookService bookService;
	@Autowired
    private ContentVoMapper contentDao;
	
	@Override
	public void process(ResultItems resultItems, Task task) {
		List<Book> list = resultItems.get("obj");
		bookService.saveBooks(list);
	}

}
