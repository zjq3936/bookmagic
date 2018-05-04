package com.cynick.bookmagic.service.impl;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class test implements PageProcessor {

	// 部分�?：抓取网站的相关配置，包括编码�?�抓取间隔�?�重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    List<String> listUrls = new ArrayList<String>();
	@Override
	public void process(Page page) {
		List<String> urls = page.getHtml().css("ul.lbf-pagination-item-list").links().regex(".*/www.qidian.com/all\\?orderId.*").all();
		// 部分二：定义如何抽取页面信息，并保存下来
//        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
//        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
//        if (page.getResultItems().get("name") == null) {
//            //skip this page
//            page.setSkip(true);
//        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
//
//        // 部分三：从页面发现后续的url地址来抓�?
        page.addTargetRequests(page.getHtml().links().regex("(https://www.qidian.com/all\\?orderId.*)").all());
		for(String url : urls){
			listUrls.add(url);
		}
		System.out.println(listUrls);
	}

	@Override
	public Site getSite() {
		return site;
	}

	
	public static void main(String[] args) {
//		String url = "https://www.qidian.com/all?orderId=&style=1&pageSize=20&siteid=1&pubflag=0&hiddenField=0&page=1";
        Spider.create(new test())
                //�?"https://github.com/code4craft"�?始抓
                .addUrl("https://www.qidian.com/all")
                //�?�?5个线程抓�?
                .thread(5)
                //启动爬虫
                .run();
    }
}
