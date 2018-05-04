package com.cynick.bookmagic.pipeline;
/**
 * å›¾ä¹¦å…¥åº“
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cynick.bookmagic.entity.Book;
import com.cynick.bookmagic.service.BookService;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
/**
 * 
 * @author CyNick
 * @date 2018å¹?5æœ?4æ—?
 */
@Component("MySqlPipeline")
public class MySqlPipeline implements Pipeline{
	
	@Autowired
	private BookService bookService;
	
	@Override
	public void process(ResultItems resultItems, Task task) {
		List<Book> list = resultItems.get("obj");
		bookService.saveBooks(list);
	}

}
