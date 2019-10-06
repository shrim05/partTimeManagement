package kr.or.ddit.alba.dao;

import java.util.List;

import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.GradeVO;
import kr.or.ddit.vo.LicenseVO;
import kr.or.ddit.vo.PagingInfoVO;

public interface IAlbaDAO {

	public List<AlbaVO> selectAlbaList(PagingInfoVO pagingVO);
	public AlbaVO selectAlba(AlbaVO av);
	public int selectAlbaCount(PagingInfoVO pagingVO);
	public int updateAlba(AlbaVO av);
	public int updateLic_Alba(LicenseVO lv);
	public int deleteAlba(AlbaVO av);
	public int insertAlba(AlbaVO av);
	public List<GradeVO> selectGradeList();
	public List<LicenseVO> selectLicenseList();
	public LicenseVO selectLicImg(LicenseVO lv);
	public int insertLicAlba(LicenseVO lv);
	public int deleteLicAlba(LicenseVO lv);
}
