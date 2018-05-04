package com.cynick.bookmagic.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
/**
 * �ɼ����
 * @author CyNick
 * @date 2018��5��4��
 */
@Controller
public class GetBookController {

	@Qualifier("QidianPageProcessor")
	@Autowired
	private PageProcessor qidianPageProcessor;

	@Qualifier("MySqlPipeline")
	@Autowired
	private Pipeline mySqlPipeline;

	@RequestMapping(value = "/getAllBook")
	@ResponseBody
	public void getAllBook() {
		
		try {
			for(int i =1;i<=44810;i++){
				String url = "https://www.qidian.com/all?orderId=&style=1&pageSize=20&siteid=1&pubflag=0&hiddenField=0&page="+i;
				Spider.create(qidianPageProcessor)
				// ץȡ��ʼ��URL
				.addUrl(url)
				// ס�������������⣩
				.addPipeline(mySqlPipeline)
				// ���ÿ������߳�����
				.thread(1)
				// ��������
				.run();
				
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
