package com.cynick.bookmagic.service.impl;

import java.util.ArrayList;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class test implements PageProcessor {

	// é¨åä¸?ï¼æåç½ç«çç¸å³éç½®ï¼åæ¬ç¼ç ã?æåé´éã?éè¯æ¬¡æ°ç­
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    List<String> listUrls = new ArrayList<String>();
	@Override
	public void process(Page page) {
		List<String> urls = page.getHtml().css("ul.lbf-pagination-item-list").links().regex(".*/www.qidian.com/all\\?orderId.*").all();
		// é¨åäºï¼å®ä¹å¦ä½æ½åé¡µé¢ä¿¡æ¯ï¼å¹¶ä¿å­ä¸æ¥
//        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
//        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
//        if (page.getResultItems().get("name") == null) {
//            //skip this page
//            page.setSkip(true);
//        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
//
//        // é¨åä¸ï¼ä»é¡µé¢åç°åç»­çurlå°åæ¥æå?
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
                //ä»?"https://github.com/code4craft"å¼?å§æ
                .addUrl("https://www.qidian.com/all")
                //å¼?å?5ä¸ªçº¿ç¨æå?
                .thread(5)
                //å¯å¨ç¬è«
                .run();
    }
}
