package com.cynick.bookmagic.entity;

import java.util.Date;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * 
 * @author CyNick
 * @date 2018Äê5ÔÂ4ÈÕ
 */
//@HelpUrl("https://www.qidian.com/all/*")
//@TargetUrl("^http://www.qidian.com/all\\?orderId=[^&]*&style=[^&]*&pageSize=[^&]*&siteid=[^&]*&pubflag=[^&]*&hiddenField=[^&]*&page=[^&]*$")
//@TargetUrl("http://www.qidian.com/all/*")
@ExtractBy(value = "//div[@class='all-book-list']/div[@class='book-img-text']/ul[@class='all-img-list cf']/li", multi = true)
public class BookQuit {

	private Long id;

	@ExtractBy("//div[@class='book-mid-info']/h4/a/text()")
	private String bookName;
	@ExtractBy("//div[@class='book-mid-info']/h4/a/@href")
	private String sourceUrl;
	@ExtractBy("//div[@class='book-img-box']/a/img/@src")
	private String titleImageUrl;
	@ExtractBy("//div[@class='book-mid-info']/p[@class='author']/a[@class='name']/text()")
	private String author = "";
	@ExtractBy("//div[@class='book-mid-info']/p[@class='intro']/text()")
	private String description;

	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getTitleImageUrl() {
		return titleImageUrl;
	}

	public void setTitleImageUrl(String titleImageUrl) {
		this.titleImageUrl = titleImageUrl;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public static void main(String[] args) {
		String url = "http://www.qidian.com/all?orderId=&style=1&pageSize=20&siteid=1&pubflag=0&hiddenField=0&page=1";
		OOSpider.create(Site.me(), new ConsolePageModelPipeline(), BookQuit.class).addUrl(url)
				.thread(1).run();
	}

}
