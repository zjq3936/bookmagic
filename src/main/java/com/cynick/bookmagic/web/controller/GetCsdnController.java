package com.cynick.bookmagic.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cynick.bookmagic.service.ContentService;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * �ɼ����
 * 
 * @author CyNick
 * @date 2018��5��4��
 */
@Controller
public class GetCsdnController {

	@Qualifier("CsdnPageProcessor")
	@Autowired
	private PageProcessor csdnPageProcessor;

	@Qualifier("CsdnMySqlPipeline")
	@Autowired
	private Pipeline csdnMySqlPipeline;

	@RequestMapping(value = "/getCsdnGigtree")
	@ResponseBody
	public void getAllBook() {

		Thread Thread1 = new Thread() {
			public void run() {
				for (int i = 6540; i <= 10000; i++) {
					String url = "https://blog.csdn.net/bigtree_3721/article/list/"+i+"?t=1";
					Spider.create(csdnPageProcessor)
							// ץȡ��ʼ��URL
							.addUrl(url)
							// ס�������������⣩
							.addPipeline(csdnMySqlPipeline)
							// ���ÿ������߳�����
							.thread(1)
							// ��������
							.run();

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};

		Thread1.start(); //6700

	}

}
