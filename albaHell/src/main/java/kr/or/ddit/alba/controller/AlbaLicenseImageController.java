package kr.or.ddit.alba.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;

@CommandHandler
public class AlbaLicenseImageController {
	IAlbaService service = AlbaServiceImpl.getInstance();
	
	@URIMapping("/alba/licenseImage.do")
	public String albaLicImage(HttpServletRequest req, HttpServletResponse resp) {
		
		return null;
	}
}
