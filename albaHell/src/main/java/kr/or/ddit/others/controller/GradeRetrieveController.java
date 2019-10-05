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
import kr.or.ddit.vo.GradeVO;

@CommandHandler
public class GradeRetrieveController {
	IAlbaDAO dao = AlbaDAOImpl.getInstance();
	@URIMapping("/others/getGradeList.do")
	public String retrieveGrade(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<GradeVO> gradeList = dao.selectGradeList();
		
		resp.setContentType("application/json ; charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		try(
			PrintWriter out = resp.getWriter();
		){
			mapper.writeValue(out, gradeList);
		}
		return null;
	}
}
