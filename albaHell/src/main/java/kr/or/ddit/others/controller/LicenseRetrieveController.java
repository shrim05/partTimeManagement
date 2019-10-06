package kr.or.ddit.others.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.alba.dao.AlbaDAOImpl;
import kr.or.ddit.alba.dao.IAlbaDAO;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.LicenseVO;

@CommandHandler
public class LicenseRetrieveController {
	IAlbaDAO dao = AlbaDAOImpl.getInstance();
	
	@URIMapping("/others/getLicenseList.do")
	public String getLicencseList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<LicenseVO> llv =  dao.selectLicenseList();
		ObjectMapper mapper = new ObjectMapper();
		resp.setContentType("application/json; charset=UTF-8");
		try(
			PrintWriter out = resp.getWriter();
		){
			mapper.writeValue(out, llv);
		}
		return null;
	}
}
