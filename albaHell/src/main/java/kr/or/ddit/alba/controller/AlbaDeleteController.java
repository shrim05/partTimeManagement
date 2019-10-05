package kr.or.ddit.alba.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;

@CommandHandler
public class AlbaDeleteController {
	IAlbaService service = AlbaServiceImpl.getInstance();
	
	@URIMapping(value="/alba/albaDelete.do",method=HttpMethod.POST)
	public String albaDelete(HttpServletRequest req, HttpServletResponse resp) {
	
		return null;
	}
}
