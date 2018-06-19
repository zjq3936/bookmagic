package com.cynick.bookmagic.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cynick.bookmagic.pipeline.CsdnMySqlPipeline;
import com.cynick.bookmagic.pipeline.CsdnPageContentProcessor;
import com.cynick.bookmagic.pipeline.CsdnPageProcessor;
import com.cynick.bookmagic.pipeline.MapCache;
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
	
	protected static MapCache cache = MapCache.single();
	
	@Qualifier("CsdnPageProcessor")
	@Autowired
	private PageProcessor csdnPageProcessor;

	@Qualifier("CsdnPageContentProcessor")
	@Autowired
	private PageProcessor csdnPageContentProcessor;

	@Qualifier("CsdnMySqlPipeline")
	@Autowired
	private Pipeline csdnMySqlPipeline;

	@RequestMapping(value = "/getCsdnGigtree")
	@ResponseBody
	public void getCsdnGigtree(String postUrl,int pageSize) {

		// ��ȡ����url 22
		for (int i = 1; i <= pageSize; i++) {
			String url = postUrl+"/article/list/" + i + "?t=1";
			Spider.create(csdnPageProcessor)
					// ץȡ��ʼ��URL
					.addUrl(url)
					// ס�������������⣩
					// .addPipeline(new CsdnMySqlPipeline())
					// ���ÿ������߳�����
					.thread(1)
					// ��������
					.run();
		}
		List<String> urllist = cache.get("urlList");
		System.out.println(JSON.toJSON(urllist));

		// ��ȡҳ����Ϣ
		for (String url : urllist) {
			Spider.create(csdnPageContentProcessor)
					// ץȡ��ʼ��URL
					.addUrl(url)
					// ס�������������⣩
					.addPipeline(csdnMySqlPipeline)
					// ���ÿ������߳�����
					.thread(1)
					// ��������
					.run();
		}

	}

}
