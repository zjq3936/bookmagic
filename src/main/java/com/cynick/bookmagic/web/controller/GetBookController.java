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
 * 采集入口
 * @author CyNick
 * @date 2018年5月4日
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
				// 抓取开始的URL
				.addUrl(url)
				// 住区结果操作（入库）
				.addPipeline(mySqlPipeline)
				// 设置开启的线程数量
				.thread(1)
				// 开启爬虫
				.run();
				
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
