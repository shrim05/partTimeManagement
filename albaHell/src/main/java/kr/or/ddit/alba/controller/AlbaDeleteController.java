package kr.or.ddit.alba.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.alba.dao.AlbaDAOImpl;
import kr.or.ddit.alba.dao.IAlbaDAO;
import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.LicenseVO;

@CommandHandler
public class AlbaDeleteController {
	IAlbaService service = AlbaServiceImpl.getInstance();
	IAlbaDAO dao = AlbaDAOImpl.getInstance();
	
	@URIMapping(value="/alba/albaDelete.do",method=HttpMethod.POST)
	public String albaDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String al_id = req.getParameter("al_id");
		String message=null;
		String viewName=null;
		if(StringUtils.isNotBlank(al_id)) {
			AlbaVO av = new AlbaVO();
			av.setAl_id(al_id);
			ServiceResult result = service.removeAlba(av);
			switch (result) {
			case OK:
				message ="삭제 성공";
				viewName ="redirect:/";
				break;
			case FAILED:
				message="서버오류";
				viewName ="/";
				break;
			}
		}else {
			resp.sendError(resp.SC_BAD_REQUEST);
			return null;
		}
		req.getSession().setAttribute("message", message);
		return viewName;
	}
	
	@URIMapping(value="/alba/albaLicenseDelete.do",method=HttpMethod.POST)
	public String albaLicDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String lic_code = req.getParameter("lic_code"); 
		String al_id = req.getParameter("al_id");
		if(StringUtils.isNotBlank(al_id)&&StringUtils.isNotBlank(lic_code)) {
			LicenseVO lv = new LicenseVO();
			lv.setAl_id(al_id);
			lv.setLic_code(lic_code);
			int cnt = dao.deleteLicAlba(lv);
			resp.setContentType("plain/text; charset=UTF-8");
			if(cnt>0) {
				try(
						PrintWriter out = resp.getWriter();
					){
					out.println("삭제성공");
				}
				
			}else {
				try(
						PrintWriter out = resp.getWriter();
					){
					out.println("서버에러");
				}
			}
		}else {
			resp.sendError(resp.SC_BAD_REQUEST);
			return null;
		}
		return null;
	}
}
