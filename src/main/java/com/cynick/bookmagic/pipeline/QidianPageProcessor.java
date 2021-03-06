package com.cynick.bookmagic.pipeline;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cynick.bookmagic.entity.Book;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * 采集图书信息
 * @author CyNick
 * @date 2018年5月4日
 */
@Component("QidianPageProcessor")
public class QidianPageProcessor implements PageProcessor {

	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	@Override
	// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	public void process(Page page) {
		// 部分二：定义如何抽取页面信息，并保存下来
		List<Book> list = new ArrayList<Book>();
		List<String> pageList = page.getHtml()
				.xpath("//div[@class='all-book-list']/div[@class='book-img-text']/ul[@class='all-img-list cf']/li")
				.all();
		for (int i = 0; i < pageList.size(); i++) {
			Book book = new Book();
			book.setBookName(new Html(pageList.get(i)).xpath("//div[@class='book-mid-info']/h4/a/text()").toString());
			book.setSourceUrl(new Html(pageList.get(i)).xpath("//div[@class='book-mid-info']/h4/a/@href").toString());
			book.setTitleImageUrl(
					new Html(pageList.get(i)).xpath("//div[@class='book-img-box']/a/img/@src").toString());
			book.setAuthor(new Html(pageList.get(i))
					.xpath("//div[@class='book-mid-info']/p[@class='author']/a[@class='name']/text()").toString());
			book.setDescription(new Html(pageList.get(i))
					.xpath("//div[@class='book-mid-info']/p[@class='intro']/text()").toString());
			list.add(book);
		}
		page.putField("obj", list);
		// 部分三：从页面发现后续的url地址来抓取
//		page.addTargetRequests(page.getHtml().links()
//				.regex("(^http://www.qidian.com/all\\?orderId=[^&]*&style=[^&]*&pageSize=[^&]*&siteid=[^&]*&pubflag=[^&]*&hiddenField=[^&]*&page=[^&]*$)")
//				.all());
	}

	@Override
	public Site getSite() {
		return site;
	}
	
	
	public static void main(String[] args) {
		String url = "https://www.qidian.com/all?orderId=&style=1&pageSize=50&siteid=1&pubflag=0&hiddenField=0&page="
				+ 6701;
		Spider.create(new QidianPageProcessor())
				// 抓取开始的URL
				.addUrl(url)
				// 住区结果操作（入库）
				.addPipeline(new MySqlPipeline())
				// 设置开启的线程数量
				.thread(1)
				// 开启爬虫
				.run();
	}

}
