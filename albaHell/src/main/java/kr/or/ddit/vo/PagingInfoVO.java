package kr.or.ddit.vo;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString(exclude="pagingHTML")
public class PagingInfoVO<T> {
	private int totalRecord;
	private int totalPage;
	private int screenSize  = 10;
	private int blockSize   = 5;
	private int currentPage = 1;
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;	
	private String pagingHTML;
	private List<T> dataList; 
	private T searchVO;
	private Map<String,Object> searchMap;
	
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(int screenSize) {
		this.screenSize = screenSize;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public T getSearchVO() {
		return searchVO;
	}

	public void setSearchVO(T searchVO) {
		this.searchVO = searchVO;
	}

	public Map<String, Object> getSearchMap() {
		return searchMap;
	}

	public void setSearchMap(Map<String, Object> searchMap) {
		this.searchMap = searchMap;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setPagingHTML(String pagingHTML) {
		this.pagingHTML = pagingHTML;
	}

	public PagingInfoVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = (int)Math.ceil((totalRecord/(double)screenSize));
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		endRow = currentPage * screenSize;
		startRow = endRow - (screenSize -1);
		endPage = blockSize * ((currentPage + (blockSize-1))/blockSize);
		startPage = endPage - (blockSize-1);
	}

	public String getPagingHTML() {
		StringBuffer html = new StringBuffer();
		html.append("<nav aria-label='...'>");
		html.append("<ul class='pagination'>");
		String disabled = startPage<blockSize?"disabled":"";
		html.append("<li class='page-item "+disabled+"'>");
		html.append("<a class='page-link' data-page='"+(startPage-blockSize)+"' href='#' tabindex='-1' aria-disabled='true'>Previous</a></li>");
	    endPage= endPage <totalPage ? endPage : totalPage;
		for (int page = startPage; page <=endPage ; page++) {
			String active = currentPage==page?"active":"";
			html.append("<li class='page-item'"+active+"><a class='page-link'data-page='"+page+"' href='#'>"+page);
			if(currentPage==page) {
				html.append("<span class='sr-only'>(current)</span>");
			}
			html.append("</a></li>");
		}
		disabled = endPage>=totalPage?"disabled":"";
		html.append("<li class='page-item "+disabled+"'><a class='page-link' data-page='"+(endPage+1)+"' href='#'>Next</a></li></ul></nav>");
		this.pagingHTML = html.toString();
		return pagingHTML;
	}
	
}
