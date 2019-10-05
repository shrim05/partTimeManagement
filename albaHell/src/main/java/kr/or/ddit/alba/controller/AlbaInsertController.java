package kr.or.ddit.alba.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.LicenseVO;
import kr.or.ddit.wrapper.MultipartRequestWapper;
import kr.or.ddit.wrapper.PartWrapper;

@CommandHandler
public class AlbaInsertController {
	IAlbaService service = AlbaServiceImpl.getInstance();
	
	@URIMapping("/alba/albaInsert.do")
	public String albaInsertForm(HttpServletRequest req, HttpServletResponse resp) {
		return "/alba/albaForm";
	}
	
	@URIMapping(value="/alba/albaInsert.do",method=HttpMethod.POST)
	public String albaInsert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		AlbaVO av = new AlbaVO();
		req.setAttribute("alba", av);
		Map queryString= req.getParameterMap();
		try {
			BeanUtils.populate(av, queryString);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException();
		}
		String[] lic_codes = req.getParameterValues("lic_code");
		List<LicenseVO> licList =null;
		LicenseVO lv = null;
		if(lic_codes!=null && lic_codes.length>0) {
			licList = new ArrayList<>();
			PartWrapper[] partWrappers =  ((MultipartRequestWapper) req).getPartWrappers("lic_image");
			for (int i = 0; i < lic_codes.length; i++) {
				lv = new LicenseVO();
				lv.setLic_code(lic_codes[i]);
				if(req instanceof MultipartRequestWapper) {
					if(partWrappers!=null) {
						lv.setLic_image(partWrappers[i].getBytes());
						}
					}
				licList.add(lv);
			}
		}
		av.setLicList(licList);
		Map<String, String> errors = new HashMap<String, String>();
		req.setAttribute("errors", errors);
		Boolean valid = validate(av, errors);
		String message = null;
		String viewName ="/alba/albaForm";
		if(valid) {
			ServiceResult result = service.createAlba(av);
			switch (result) {
			case OK:
				message ="추가 성공";
				viewName ="redirect:/alba/albaView.do?who="+av.getAl_id();
				break;
			case FAILED:
				message="서버오류";
				viewName ="/alba/albaForm";
				break;
			}
		}else {
			message="항목누락";
			viewName="/alba/albaForm";
		}
		req.setAttribute("message", message);
		return viewName;
		
	}
	
	private boolean validate(AlbaVO av, Map errors) {
		boolean valid = true;
		if(!StringUtils.isBlank(av.getAl_id())){ valid  = false; errors.put("al_id","아이디직접입력불가"); }
		if(StringUtils.isBlank(av.getAl_name())){ valid  = false; errors.put("al_name","이름누락"); }
		if(av.getAl_age()<=0){ valid  = false; errors.put("al_age","나이누락"); }
		if(StringUtils.isBlank(av.getAl_address())){ valid  = false; errors.put("al_address","주소누락"); }
		if(StringUtils.isBlank(av.getAl_hp())){ valid  = false; errors.put("al_hp","연락처누락"); }
		if(StringUtils.isBlank(av.getGr_code())){ valid  = false; errors.put("gr_code","학력누락"); }
		if(StringUtils.isBlank(av.getAl_gen())){ valid  = false; errors.put("al_gen","성별누락"); }
		if(StringUtils.isBlank(av.getAl_btype())){ valid  = false; errors.put("al_btype","혈액형누락"); }
		if(StringUtils.isBlank(av.getAl_mail())){ valid  = false; errors.put("al_mail","이메일누락"); }
		return valid;
	}
}
