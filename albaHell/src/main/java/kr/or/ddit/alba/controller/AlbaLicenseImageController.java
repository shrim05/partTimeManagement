package kr.or.ddit.alba.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.alba.dao.AlbaDAOImpl;
import kr.or.ddit.alba.dao.IAlbaDAO;
import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.LicenseVO;

@CommandHandler
public class AlbaLicenseImageController {
	IAlbaDAO dao = AlbaDAOImpl.getInstance();
	
	@URIMapping("/alba/licenseImage.do")
	public String albaLicImage(HttpServletRequest req, HttpServletResponse resp) {
		String al_id = req.getParameter("al_id");
		String lic_code = req.getParameter("lic_code");
		LicenseVO lv = new LicenseVO();
		String viewName = null;
		if(StringUtils.isNotBlank(al_id)&&StringUtils.isNotBlank(lic_code)) {
			lv.setLic_code(lic_code);
			lv.setAl_id(al_id);
			lv = dao.selectLicImg(lv);
			req.setAttribute("lic", lv);
			viewName = "/alba/licImage";
		}else {
			String message="서버오류";
			req.setAttribute("message", message);
			viewName = "/alba/albaForm";
		}
		return viewName;
	}
}
