package com.cynick.bookmagic.pipeline;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cynick.bookmagic.entity.Article;
import com.cynick.bookmagic.service.BookService;
import com.cynick.bookmagic.util.FileUtil;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
/**
 * csdn Èë¿â
 * @author CyNick
 *
 */
@Component("CsdnMySqlPipeline")
public class CsdnMySqlPipeline implements Pipeline{
	
	@Autowired
	private BookService bookService;
	
	@Override
	public void process(ResultItems resultItems, Task task) {
//		List<Book> list = resultItems.get("obj");
//		bookService.saveBooks(list);
//		System.out.println("======================================");
//		if(StringUtils.isBlank(resultItems.get("obj"))){
			System.out.println("title"+"#########################"+(String)resultItems.get("title"));
			System.out.println((String)resultItems.get("obj"));
			Article article = new Article();
			article.setId(Integer.parseInt(resultItems.get("id")));
			article.setTitle((String)resultItems.get("title"));
			article.setContent((String)resultItems.get("obj"));
//			FileUtil.html2HexoMd(article);
//		}
	}

}
