package utils;

import java.util.List;

public class Page<T> {
	//当前页，从请求传过来的值
	private int pageNum = 0;
	//每页显示的数据条数
	private int pageSize = 0;
	//表里的数据有多少
	private int totalRecord = 0;
	//总共有多少页,等于totalRecord/pageSize向上取整
	private int totalPage = 0;
	//从数据库第几行开始读数据
	private int starIndex = 0;
	//页面显示的数据
	private List<T> list =null;
	//页面的页数导航
	private int start ,end = 0;
	public Page(int pageNum, int pageSize, int totalRecord) {
		double i = (double)totalRecord/pageSize;
		//向上取整
		int n = (int)Math.ceil(i);
		this.totalPage = n;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		//从0开始计数，所以不能 + 1
		this.starIndex = (pageNum-1)*pageSize;
		//this.start = 1;
		//this.end = 5;
		if(this.totalPage <= 5){
			this.start = 1;
			this.end = this.totalPage;
			if(this.start <= 0){
				//当前页为前两页的显示规则
				this.start = 1;
			}
		} else if(this.totalPage > 5){
			//一般规则
			this.start = pageNum - 2;
			this.end = pageNum + 2;
			if(this.start <= 0){
				//当前页为前两页的显示规则
				this.start = 1;
				this.end = 5;
			}
			if(this.end == (this.totalPage+1)){
				//倒数第二页的显示规则
				this.start = pageNum - 3;
				this.end = this.totalPage;
			}
			if(this.end == (this.totalPage+2)){
				//最后一页的显示规则
				this.start = pageNum - 4;
				this.end = this.totalPage;
			}
		}
	}
	
	//get set方法
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStarIndex() {
		return starIndex;
	}
	public void setStarIndex(int starIndex) {
		this.starIndex = starIndex;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
}
