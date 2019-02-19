package utils;

import java.util.List;

public class Page<T> {
	//��ǰҳ�������󴫹�����ֵ
	private int pageNum = 0;
	//ÿҳ��ʾ����������
	private int pageSize = 0;
	//����������ж���
	private int totalRecord = 0;
	//�ܹ��ж���ҳ,����totalRecord/pageSize����ȡ��
	private int totalPage = 0;
	//�����ݿ�ڼ��п�ʼ������
	private int starIndex = 0;
	//ҳ����ʾ������
	private List<T> list =null;
	//ҳ���ҳ������
	private int start ,end = 0;
	public Page(int pageNum, int pageSize, int totalRecord) {
		double i = (double)totalRecord/pageSize;
		//����ȡ��
		int n = (int)Math.ceil(i);
		this.totalPage = n;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		//��0��ʼ���������Բ��� + 1
		this.starIndex = (pageNum-1)*pageSize;
		//this.start = 1;
		//this.end = 5;
		if(this.totalPage <= 5){
			this.start = 1;
			this.end = this.totalPage;
			if(this.start <= 0){
				//��ǰҳΪǰ��ҳ����ʾ����
				this.start = 1;
			}
		} else if(this.totalPage > 5){
			//һ�����
			this.start = pageNum - 2;
			this.end = pageNum + 2;
			if(this.start <= 0){
				//��ǰҳΪǰ��ҳ����ʾ����
				this.start = 1;
				this.end = 5;
			}
			if(this.end == (this.totalPage+1)){
				//�����ڶ�ҳ����ʾ����
				this.start = pageNum - 3;
				this.end = this.totalPage;
			}
			if(this.end == (this.totalPage+2)){
				//���һҳ����ʾ����
				this.start = pageNum - 4;
				this.end = this.totalPage;
			}
		}
	}
	
	//get set����
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
