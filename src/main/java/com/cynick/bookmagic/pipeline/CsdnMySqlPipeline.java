package com.cynick.bookmagic.pipeline;
import com.cynick.bookmagic.dao.ContentVoMapper;
import com.cynick.bookmagic.entity.ContentVo;
import com.cynick.bookmagic.exception.Types;
import com.cynick.bookmagic.service.BookService;
import com.cynick.bookmagic.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;
/**
 * csdn 入库
 * @author CyNick
 *
 */
@Component("CsdnMySqlPipeline")
public class CsdnMySqlPipeline implements Pipeline{
	
	@Autowired
	private BookService bookService;
	
	@Autowired
    private ContentVoMapper contentDao;

//    private MetaVoMapper metaDao;

	@Autowired
    private MetaService metasService;
	
	@Override
	public void process(ResultItems resultItems, Task task) {
//		List<Book> list = resultItems.get("obj");
//		bookService.saveBooks(list);
//		System.out.println("======================================");
//		if(StringUtils.isBlank(resultItems.get("obj"))){
		
		
			System.out.println("title"+"#########################"+(String)resultItems.get("title"));
			System.out.println((String)resultItems.get("obj"));
			ContentVo article = new ContentVo();
			
			article.setAllowComment(true);
			article.setAllowFeed(true);
			article.setAllowPing(true);
			article.setAuthorId(1);
			article.setCategories("搬家公司");
			article.setCommentsNum(0);
			article.setContent((String)resultItems.get("obj"));
			article.setCreated(getCurrentUnixTime());
			article.setHits(0);
			article.setModified(getCurrentUnixTime());
			article.setSlug(null);
			article.setStatus("draft");
			article.setTags((String)resultItems.get("tags"));
			article.setTitle((String)resultItems.get("title"));
			article.setType("post");
			
			String tags = article.getTags();
	        String categories = article.getCategories();
	        contentDao.insert(article);
	        Integer cid = article.getCid();
	        metasService.saveMetas(cid, tags, Types.TAG.getType());
	        metasService.saveMetas(cid, categories, Types.CATEGORY.getType());
//			article.setId(Integer.parseInt(resultItems.get("id")));
//			article.setTitle((String)resultItems.get("title"));
//			article.setContent((String)resultItems.get("obj"));
//			FileUtil.html2HexoMd(article);
//		}
	}

	
	public static int getCurrentUnixTime() {
        return getUnixTimeByDate(new Date());
    }

    public static int getUnixTimeByDate(Date date) {
        return (int)(date.getTime() / 1000L);
    }
}
