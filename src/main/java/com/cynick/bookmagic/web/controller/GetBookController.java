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
public class GetBookController {

	@Qualifier("QidianPageProcessor")
	@Autowired
	private PageProcessor qidianPageProcessor;

	@Qualifier("MySqlPipeline")
	@Autowired
	private Pipeline mySqlPipeline;

	@Autowired
	private ContentService contentService;

	@RequestMapping(value = "/getAllBook")
	@ResponseBody
	public void getAllBook() {
		// 44810
		
		String url = "https://www.qidian.com/all?orderId=&style=1&pageSize=50&siteid=1&pubflag=0&hiddenField=0&page="
				+ 6701;
		Spider.create(qidianPageProcessor)
				// ץȡ��ʼ��URL
				.addUrl(url)
				// ס�������������⣩
				.addPipeline(mySqlPipeline)
				// ���ÿ������߳�����
				.thread(1)
				// ��������
				.run();

//		Thread Thread1 = new Thread() {
//			public void run() {
//				for (int i = 6540; i <= 10000; i++) {
//					String url = "https://www.qidian.com/all?orderId=&style=1&pageSize=20&siteid=1&pubflag=0&hiddenField=0&page="
//							+ i;
//					Spider.create(qidianPageProcessor)
//							// ץȡ��ʼ��URL
//							.addUrl(url)
//							// ס�������������⣩
//							.addPipeline(mySqlPipeline)
//							// ���ÿ������߳�����
//							.thread(1)
//							// ��������
//							.run();
//
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		};

//		Thread Thread2 = new Thread() {
//			public void run() {
//				for (int i = 10540; i <= 20000; i++) {
//					String url = "https://www.qidian.com/all?orderId=&style=1&pageSize=20&siteid=1&pubflag=0&hiddenField=0&page="
//							+ i;
//					Spider.create(qidianPageProcessor)
//							// ץȡ��ʼ��URL
//							.addUrl(url)
//							// ס�������������⣩
//							.addPipeline(mySqlPipeline)
//							// ���ÿ������߳�����
//							.thread(1)
//							// ��������
//							.run();
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		};
//
//		Thread Thread3 = new Thread() {
//			public void run() {
//				for (int i = 20540; i <= 30000; i++) {
//					String url = "https://www.qidian.com/all?orderId=&style=1&pageSize=20&siteid=1&pubflag=0&hiddenField=0&page="
//							+ i;
//					Spider.create(qidianPageProcessor)
//							// ץȡ��ʼ��URL
//							.addUrl(url)
//							// ס�������������⣩
//							.addPipeline(mySqlPipeline)
//							// ���ÿ������߳�����
//							.thread(1)
//							// ��������
//							.run();
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		};
//		
//		Thread Thread4 = new Thread() {
//			public void run() {
//				for (int i = 30540; i <= 40000; i++) {
//					String url = "https://www.qidian.com/all?orderId=&style=1&pageSize=20&siteid=1&pubflag=0&hiddenField=0&page="
//							+ i;
//					Spider.create(qidianPageProcessor)
//							// ץȡ��ʼ��URL
//							.addUrl(url)
//							// ס�������������⣩
//							.addPipeline(mySqlPipeline)
//							// ���ÿ������߳�����
//							.thread(1)
//							// ��������
//							.run();
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		};

//		Thread Thread5 = new Thread() {
//			public void run() {
//				for (int i = 40540; i <= 50000; i++) {
//					String url = "https://www.qidian.com/all?orderId=&style=1&pageSize=20&siteid=1&pubflag=0&hiddenField=0&page="
//							+ i;
//					Spider.create(qidianPageProcessor)
//							// ץȡ��ʼ��URL
//							.addUrl(url)
//							// ס�������������⣩
//							.addPipeline(mySqlPipeline)
//							// ���ÿ������߳�����
//							.thread(1)
//							// ��������
//							.run();
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		};
		
//		Thread1.start(); //6700
//		Thread2.start(); //10700
//		Thread3.start(); //20700
//		Thread4.start(); //30700
//		Thread5.start(); //40700

	}

	@RequestMapping(value = "/getAllBookContent")
	@ResponseBody
	public void getAllBookContent() {

		contentService.getBooksContent();
	}

}
