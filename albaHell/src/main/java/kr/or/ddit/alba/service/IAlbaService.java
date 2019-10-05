package kr.or.ddit.alba.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.PagingInfoVO;

public interface IAlbaService {
	public List<AlbaVO> retrieveAlbaList(PagingInfoVO pagingVO);
	public AlbaVO retrieveAlba(AlbaVO av);
	public int retrieveAlbaCount(PagingInfoVO pagingVO);
	public ServiceResult modifyAlba(AlbaVO av);
	public ServiceResult removeAlba(AlbaVO av);
	public ServiceResult createAlba(AlbaVO av);
}
