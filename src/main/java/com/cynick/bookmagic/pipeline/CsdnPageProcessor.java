package com.cynick.bookmagic.pipeline;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.cynick.bookmagic.entity.Book;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * �ɼ�csdn
 * @author CyNick
 *
 */
@Component("CsdnPageProcessor")
public class CsdnPageProcessor implements PageProcessor {

	// ����һ��ץȡ��վ��������ã��������롢ץȡ��������Դ�����
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	@Override
	// process�Ƕ��������߼��ĺ��Ľӿڣ��������д��ȡ�߼�
	public void process(Page page) {
		// ���ֶ���������γ�ȡҳ����Ϣ������������
		List<Book> list = new ArrayList<Book>();
		List<String> pageList = page.getHtml()
				.xpath("//div[@class='article-item-box csdn-tracking-statistics']").all();
//		for (int i = 0; i < pageList.size(); i++) {
//			Book book = new Book();
//			book.setBookName(new Html(pageList.get(i)).xpath("//div[@class='book-mid-info']/h4/a/text()").toString());
//			book.setSourceUrl(new Html(pageList.get(i)).xpath("//div[@class='book-mid-info']/h4/a/@href").toString());
//			book.setTitleImageUrl(
//					new Html(pageList.get(i)).xpath("//div[@class='book-img-box']/a/img/@src").toString());
//			book.setAuthor(new Html(pageList.get(i))
//					.xpath("//div[@class='book-mid-info']/p[@class='author']/a[@class='name']/text()").toString());
//			book.setDescription(new Html(pageList.get(i))
//					.xpath("//div[@class='book-mid-info']/p[@class='intro']/text()").toString());
//			list.add(book);
//		}
		
		
		System.out.println(JSON.toJSON(pageList));
		page.putField("obj", list);
		// ����������ҳ�淢�ֺ�����url��ַ��ץȡ
//		page.addTargetRequests(page.getHtml().links()
//				.regex("(^http://www.qidian.com/all\\?orderId=[^&]*&style=[^&]*&pageSize=[^&]*&siteid=[^&]*&pubflag=[^&]*&hiddenField=[^&]*&page=[^&]*$)")
//				.all());
	}

	@Override
	public Site getSite() {
		return site;
	}

}
