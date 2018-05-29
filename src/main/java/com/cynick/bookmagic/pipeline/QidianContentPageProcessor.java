package com.cynick.bookmagic.pipeline;

import java.text.ParseException;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import com.cynick.bookmagic.entity.Content;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * �ɼ��½���Ϣ
 * @author CyNick
 * @date 2018��5��4��
 */
@Component("QidianContentPageProcessor")
public class QidianContentPageProcessor implements PageProcessor {

	// ����һ��ץȡ��վ��������ã��������롢ץȡ��������Դ�����
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	@Override
	// process�Ƕ��������߼��ĺ��Ľӿڣ��������д��ȡ�߼�
	public void process(Page page) {
		try {
			// ���ֶ���������γ�ȡҳ����Ϣ������������
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

		// ����������ҳ�淢�ֺ�����url��ַ��ץȡ
		page.addTargetRequests(page.getHtml().links()
				.regex("(^https://read.qidian.com/chapter/[^&]*/[^&]*$)")
				.all());
	}

	@Override
	public Site getSite() {
		return site;
	}

}
