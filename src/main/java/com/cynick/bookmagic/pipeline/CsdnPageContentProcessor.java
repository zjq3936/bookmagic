package com.cynick.bookmagic.pipeline;

import com.cynick.bookmagic.util.html2markdown.HTML2Md;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 采集csdn
 * 
 * @author CyNick
 *
 */
@Component("CsdnPageContentProcessor")
public class CsdnPageContentProcessor implements PageProcessor {

	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private static Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	protected static MapCache cache = MapCache.single();

	@Override
	// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
	public void process(Page page) {
		// 部分二：定义如何抽取页面信息，并保存下来
		// List<String> pageList = page.getHtml()
		// .xpath("//div[@class='article-item-box
		// csdn-tracking-statistics']/h4/a/@href").all();
//		String id = RegexUtil.match(".*/(\\d*)", page.getUrl().toString(), 1);
		String headContent = "本文为转载文章，原文地址："+page.getUrl().toString()+"</br></br>";
		
		String title = String.valueOf(page.getHtml().xpath(
				"//div[@class='blog-content-box']/div[@class='article-title-box']/h1[@class='title-article']/text()"));
		String content = String.valueOf(page.getHtml().xpath(
				"//div[@class='article_content clearfix csdn-tracking-statistics']/div[@class='htmledit_views']"));
		String tags = listToString(page.getHtml().xpath(
				"//div[@class='article-bar-bottom']/div[@class='tags-box artic-tag-box']/a[@class='tag-link']/text()").all());
		// content = delHTMLTag(content);
		// page.putField("id", id);

		content = HTML2Md.convert(content, "UTF-8");
		page.putField("title", title);
		page.putField("obj", headContent+content);
		page.putField("tags", tags);
		// 部分三：从页面发现后续的url地址来抓取
		// page.addTargetRequests(page.getHtml().links()
		// .regex("(^http://www.qidian.com/all\\?orderId=[^&]*&style=[^&]*&pageSize=[^&]*&siteid=[^&]*&pubflag=[^&]*&hiddenField=[^&]*&page=[^&]*$)")
		// .all());
	}

	@Override
	public Site getSite() {
		return site;
	}

	public String delHTMLTag(String htmlStr) {
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		return htmlStr.trim(); // 返回文本字符串
	}

	// 把list转换为string，用,分割
	public static String listToString(List<String> stringList) {
		if (stringList == null) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		boolean flag = false;
		for (String string : stringList) {
			if (flag) {
				result.append(",");
			} else {
				flag = true;
			}
			result.append(string);
		}
		return result.toString();
	}
}
