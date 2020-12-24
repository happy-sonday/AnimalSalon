package com.cndsalon.domain.shop;

import java.sql.Date;


/**
 * 
 * <pre>
 * CndSalonReviewVO : 매장 리뷰 상세 정보 VO
 * 주의사항 : lombok 사용시 POST Ajax 통신 시 VO 객체에 자동으로 담기지 않는 버그가 발생 한다.
 * </pre>
 * @author <a href="simhyung777@naver.com">심현종</a></br>
 * @date ${date}
 * @version 1.0
 * @since 2020-12-21
 * @param 
 * rCode[String] : 리뷰 코드
 * sCode[String] : 매장 코드
 * dCode[String] : 디자이너 코드
 * dName[String] : 디자이너 이름
 * rWriter[String] : 리뷰 작성자
 * rTitle[String] : 리뷰 제목
 * rContent[String] : 리뷰 내용
 * rScore[double] : 리뷰 평점
 * rEmpaty[int] : 리뷰 공감 수
 * rCount[int] : 리뷰 조회 수
 * rDate[Date] : 리뷰 작성 일
 * rWriterIp[String] : 리뷰 작성자의 local IP 정보
 * rPhotopath[String] : 리뷰 글의 사진 파일 경로
 * rPhotoname[String] : 리뷰 글의 사진 파일 이름
 * rPhotonameOrigin[String] : 리뷰 글의 사진 파일 원래 이름
 */
public class CndSalonReviewVO {

	private String rCode;
	private String sCode;
	private String dCode;
	private String rWriter;
	private String rTitle;
	private String rContent;
	private double rScore;
	private int rEmpaty;
	private int rCount;
	private Date rDate;
	private String rWriterIp;
	private String rPhotopath;
	private String rPhotoname;
	private String rPhotonameOrigin;
	public String getrCode() {
		return rCode;
	}
	public void setrCode(String rCode) {
		this.rCode = rCode;
	}
	public String getsCode() {
		return sCode;
	}
	public void setsCode(String sCode) {
		this.sCode = sCode;
	}
	public String getdCode() {
		return dCode;
	}
	public void setdCode(String dCode) {
		this.dCode = dCode;
	}
	public String getrWriter() {
		return rWriter;
	}
	public void setrWriter(String rWriter) {
		this.rWriter = rWriter;
	}
	public String getrTitle() {
		return rTitle;
	}
	public void setrTitle(String rTitle) {
		this.rTitle = rTitle;
	}
	public String getrContent() {
		return rContent;
	}
	public void setrContent(String rContent) {
		this.rContent = rContent;
	}
	public double getrScore() {
		return rScore;
	}
	public void setrScore(double rScore) {
		this.rScore = rScore;
	}
	public int getrEmpaty() {
		return rEmpaty;
	}
	public void setrEmpaty(int rEmpaty) {
		this.rEmpaty = rEmpaty;
	}
	public int getrCount() {
		return rCount;
	}
	public void setrCount(int rCount) {
		this.rCount = rCount;
	}
	public Date getrDate() {
		return rDate;
	}
	public void setrDate(Date rDate) {
		this.rDate = rDate;
	}
	public String getrWriterIp() {
		return rWriterIp;
	}
	public void setrWriterIp(String rWriterIp) {
		this.rWriterIp = rWriterIp;
	}
	public String getrPhotopath() {
		return rPhotopath;
	}
	public void setrPhotopath(String rPhotopath) {
		this.rPhotopath = rPhotopath;
	}
	public String getrPhotoname() {
		return rPhotoname;
	}
	public void setrPhotoname(String rPhotoname) {
		this.rPhotoname = rPhotoname;
	}
	public String getrPhotonameOrigin() {
		return rPhotonameOrigin;
	}
	public void setrPhotonameOrigin(String rPhotonameOrigin) {
		this.rPhotonameOrigin = rPhotonameOrigin;
	}
	@Override
	public String toString() {
		return "CndSalonReviewVO [rCode=" + rCode + ", sCode=" + sCode + ", dCode=" + dCode + ", rWriter=" + rWriter
				+ ", rTitle=" + rTitle + ", rContent=" + rContent + ", rScore=" + rScore + ", rEmpaty=" + rEmpaty
				+ ", rCount=" + rCount + ", rDate=" + rDate + ", rWriterIp=" + rWriterIp + ", rPhotopath=" + rPhotopath
				+ ", rPhotoname=" + rPhotoname + ", rPhotonameOrigin=" + rPhotonameOrigin + "]";
	}
	

}
