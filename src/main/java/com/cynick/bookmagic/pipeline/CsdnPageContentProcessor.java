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
 * �ɼ�csdn
 * 
 * @author CyNick
 *
 */
@Component("CsdnPageContentProcessor")
public class CsdnPageContentProcessor implements PageProcessor {

	// ����һ��ץȡ��վ��������ã��������롢ץȡ��������Դ�����
	private static Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	protected static MapCache cache = MapCache.single();

	@Override
	// process�Ƕ��������߼��ĺ��Ľӿڣ��������д��ȡ�߼�
	public void process(Page page) {
		// ���ֶ���������γ�ȡҳ����Ϣ������������
		// List<String> pageList = page.getHtml()
		// .xpath("//div[@class='article-item-box
		// csdn-tracking-statistics']/h4/a/@href").all();
//		String id = RegexUtil.match(".*/(\\d*)", page.getUrl().toString(), 1);
		String headContent = "����Ϊת�����£�ԭ�ĵ�ַ��"+page.getUrl().toString()+"</br></br>";
		
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
		// ����������ҳ�淢�ֺ�����url��ַ��ץȡ
		// page.addTargetRequests(page.getHtml().links()
		// .regex("(^http://www.qidian.com/all\\?orderId=[^&]*&style=[^&]*&pageSize=[^&]*&siteid=[^&]*&pubflag=[^&]*&hiddenField=[^&]*&page=[^&]*$)")
		// .all());
	}

	@Override
	public Site getSite() {
		return site;
	}

	public String delHTMLTag(String htmlStr) {
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // ����script��������ʽ
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // ����style��������ʽ
		String regEx_html = "<[^>]+>"; // ����HTML��ǩ��������ʽ

		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // ����script��ǩ

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // ����style��ǩ

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // ����html��ǩ

		return htmlStr.trim(); // �����ı��ַ���
	}

	// ��listת��Ϊstring����,�ָ�
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
