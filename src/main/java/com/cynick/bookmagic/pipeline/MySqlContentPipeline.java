package com.cynick.bookmagic.pipeline;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cynick.bookmagic.dao.ContentMapper;
import com.cynick.bookmagic.entity.Content;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
/**
 * �½���Ϣ���
 * @author CyNick
 * @date 2018��5��4��
 */
@Component("MySqlContentPipeline")
public class MySqlContentPipeline implements Pipeline{
	
	@Resource
	private ContentMapper contentMapper;
	
	@Override
	public void process(ResultItems resultItems, Task task) {
		Content content = resultItems.get("obj");
		content.setBookId(Long.parseLong(task.getUUID()));
		content.setSite(task.getSite().getDomain());
		int count = contentMapper.selectByTitleAndBookId(content.getTitle(), Long.parseLong(task.getUUID()));
		if(count < 1){
			contentMapper.insertSelective(content);
		}
		
	}

}
