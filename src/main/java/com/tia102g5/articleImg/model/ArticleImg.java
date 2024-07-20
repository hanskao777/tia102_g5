package com.tia102g5.articleImg.model; 

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "articleImg")
public class ArticleImg {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "articleImgID", updatable = false)
	private Integer articleImgID;

	@Column(name = "articleID")
	private Integer articleID;

	@Column(name = "articlePic", columnDefinition = "mediumblob")
	private byte[] articlePic;

	@Column(name = "articleImgCreateTime", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date  articleImgCreateTime;
	
	@PrePersist
	protected void onCreate() {
	    this.articleImgCreateTime = new Date();
	}
		
	
	public Integer getArticleImgID() {
		return articleImgID;
	}

	public void setArticleImgID(Integer articleImgID) {
		this.articleImgID = articleImgID;
	}


	public Integer getArticleID() {
		return articleID;
	}


	public void setArticleID(Integer articleID) {
		this.articleID = articleID;
	}


	public byte[] getArticlePic() {
		return articlePic;
	}

	public void setArticlePic(byte[] articlePic) {
		this.articlePic = articlePic;
	}


	public Date getArticleImgCreateTime() {
		return articleImgCreateTime;
	}


	@Override
	public String toString() {
		return "ArticleImg [articleImgID=" + articleImgID + ", articleID=" + articleID + ", articlePic=" + articlePic + ", articleImgCreateTime=" + articleImgCreateTime + "]";
	}

}
