package com.cynick.bookmagic.entity;

import java.util.Date;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
/**
 * 
 * @author CyNick
 * @date 2018Äê5ÔÂ4ÈÕ
 */
public class Content implements AfterExtractor {
    private Long id;

    private String title;

    private String site;

    private Integer page;

    private String description;

    private Date createTime;

    private Long bookId;

    private Date qidianUpdatetime;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site == null ? null : site.trim();
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Date getQidianUpdatetime() {
        return qidianUpdatetime;
    }

    public void setQidianUpdatetime(Date qidianUpdatetime) {
        this.qidianUpdatetime = qidianUpdatetime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	@Override
	public void afterProcess(Page page) {
		// TODO Auto-generated method stub
		
	}
}