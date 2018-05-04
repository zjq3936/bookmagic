package com.cynick.bookmagic.pipeline;

import java.text.ParseException;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import com.cynick.bookmagic.entity.Content;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 采集章节内容
 * @author CyNick
 * @date 2018�?5�?4�?
 */
@Component("QidianContentPageProcessor")
public class QidianContentPageProcessor implements PageProcessor {

	// 部分�?：抓取网站的相关配置，包括编码�?�抓取间隔�?�重试次数等
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	@Override
	// process是定制爬虫�?�辑的核心接口，在这里编写抽取�?�辑
	public void process(Page page) {
		try {
			// 部分二：定义如何抽取页面信息，并保存下来
			Content content = new Content();
			content.setTitle(String.valueOf(page.getHtml().xpath("//h3[@class='j_chapterName']/text()")));
			content.setQidianUpdatetime(DateUtils.parseDate(
					String.valueOf(page.getHtml().xpath("//h3[@class='j_chapterName']/text()")), "yyyy.MM.dd HH:mm"));
			content.setContent(String.valueOf(page.getHtml().xpath("//div[@class='read-content j_readContent']")));
			page.putField("obj", content);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 部分三：从页面发现后续的url地址来抓�?
		page.addTargetRequests(page.getHtml().links()
				.regex("(^http://www.qidian.com/all\\?orderId=[^&]*&style=[^&]*&pageSize=[^&]*&siteid=[^&]*&pubflag=[^&]*&hiddenField=[^&]*&page=[^&]*$)")
				.all());
	}

	@Override
	public Site getSite() {
		return site;
	}

}
