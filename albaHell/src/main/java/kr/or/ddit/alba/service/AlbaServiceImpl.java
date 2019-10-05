package kr.or.ddit.alba.service;

import java.util.List;

import kr.or.ddit.alba.dao.AlbaDAOImpl;
import kr.or.ddit.alba.dao.IAlbaDAO;
import kr.or.ddit.alba.exception.AlbaNotFoundException;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingInfoVO;

public class AlbaServiceImpl implements IAlbaService {
	
	private static AlbaServiceImpl service;
	private IAlbaDAO dao;
	
	public AlbaServiceImpl() {
		dao = AlbaDAOImpl.getInstance();
	}
	
	public static AlbaServiceImpl getInstance() {
		if(service==null) {
			service = new AlbaServiceImpl();
		}
		return service;
	}
	@Override
	public List<AlbaVO> retrieveAlbaList(PagingInfoVO pagingVO) {
		return dao.selectAlbaList(pagingVO);
	}

	@Override
	public AlbaVO retrieveAlba(AlbaVO av) {
		AlbaVO alba = dao.selectAlba(av);
		if(alba==null) {
			throw new AlbaNotFoundException(av.getAl_name()+"가 없음");
		}
		return alba;
	}

	@Override
	public int retrieveAlbaCount(PagingInfoVO pagingVO) {
		return dao.selectAlbaCount(pagingVO);
	}

	@Override
	public ServiceResult modifyAlba(AlbaVO av) {
		ServiceResult result = null;
		int cnt = dao.updateAlba(av);
		if(cnt>0) {
			result=ServiceResult.OK;
		}else if(cnt<=0) {
			result=ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult removeAlba(AlbaVO av) {
		ServiceResult result = null;
		int cnt = dao.deleteAlba(av);
		if(cnt>0) {
			result=ServiceResult.OK;
		}else if(cnt<=0) {
			result=ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult createAlba(AlbaVO av) {
		ServiceResult result = null;
		if(dao.selectAlba(av)==null) {
			int cnt = dao.insertAlba(av);
			if(cnt>0) result = ServiceResult.OK;
			else result = ServiceResult.FAILED;
		}else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}

}
