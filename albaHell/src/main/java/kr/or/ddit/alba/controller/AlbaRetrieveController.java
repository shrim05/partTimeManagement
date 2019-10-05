package kr.or.ddit.alba.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingInfoVO;

@CommandHandler
public class AlbaRetrieveController {
	IAlbaService service = AlbaServiceImpl.getInstance();
	
	@URIMapping("/alba/albaList.do")
	public String albaList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		AlbaVO av = new AlbaVO();
		String pageParam = req.getParameter("page");
		String searchType = req.getParameter("searchType");
		String searchWord = req.getParameter("searchWord");
		Map<String, Object> searchMap =  new HashMap<>();
		searchMap.put("searchType", searchType);
		searchMap.put("searchWord", searchWord);
		int currentPage =1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		PagingInfoVO<AlbaVO> pagingVO = new PagingInfoVO<AlbaVO>(5,3);
		pagingVO.setSearchMap(searchMap);
		int totalRecord =service.retrieveAlbaCount(pagingVO);
		String accept = req.getHeader("Accept");
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<AlbaVO> lav = service.retrieveAlbaList(pagingVO);
		pagingVO.setDataList(lav);
		if(accept.contains("json")) {
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			try(
					PrintWriter out = resp.getWriter();
					
			){
				mapper.writeValue(out, pagingVO);
			}
			return null;
		}else {
			String viewName ="/alba/albaList";
			return viewName;
		}
	}
	
	
	@URIMapping("/alba/albaView.do")
	public String albaView(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String al_id = req.getParameter("who");
		if(StringUtils.isBlank(al_id)) {
			resp.sendError(400);
			return null;
		}
		AlbaVO av = new AlbaVO();
		av.setAl_id(al_id);
		av = service.retrieveAlba(av);
		req.setAttribute("alba", av);
		return "/alba/albaView";
	}
	
}
