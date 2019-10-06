package kr.or.ddit.alba.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.GradeVO;
import kr.or.ddit.vo.LicenseVO;
import kr.or.ddit.vo.PagingInfoVO;

public class AlbaDAOImpl implements IAlbaDAO {
	
	private static AlbaDAOImpl dao;
	private SqlSessionFactory sqlSessionFactory =
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	public AlbaDAOImpl() {
		
	}
	public static AlbaDAOImpl getInstance() {
		if(dao ==null) {
			dao = new AlbaDAOImpl();
		}
		return dao;
	}

	@Override
	public List<AlbaVO> selectAlbaList(PagingInfoVO pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
		return mapper.selectAlbaList(pagingVO);
		}
	}

	@Override
	public AlbaVO selectAlba(AlbaVO av) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
		return mapper.selectAlba(av);
		}
	}

	
	@Override
	public int selectAlbaCount(PagingInfoVO pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
		return mapper.selectAlbaCount(pagingVO);
		}
	}

	@Override
	public int updateAlba(AlbaVO av) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			int cnt = mapper.updateAlba(av);
//			if(cnt>0&&av.getLicList()!=null) {
////				cnt=
//				mapper.updateLic_Alba(av);
//			}
			if(cnt>0&&av.getLicList()!=null) {
				List<LicenseVO> llv = av.getLicList();
				for (int i = 0; i < llv.size(); i++) {
					llv.get(i).setAl_id(av.getAl_id());
					mapper.insertLicAlba(llv.get(i));
					if(llv.get(i).getLic_image()!=null) {
						cnt = mapper.updateLic_Alba(llv.get(i));
					}else {
						continue;
					}
				}
			}
			if(cnt>0) {
				sqlSession.commit();
			}
		return cnt;
		}
	}
	
	@Override
	public int updateLic_Alba(LicenseVO lv) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			int cnt = mapper.updateLic_Alba(lv);
		return cnt;
		}
	}
	@Override
	public int deleteAlba(AlbaVO av) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			LicenseVO lv = new LicenseVO();
			lv.setAl_id(av.getAl_id());
			mapper.deleteLicAlba(lv);
			int cnt = mapper.deleteAlba(av);
			sqlSession.commit();
		return cnt;
		}
	}
	
	@Override
	public int deleteLicAlba(LicenseVO lv) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			int cnt = mapper.deleteLicAlba(lv);
			sqlSession.commit();
		return cnt;
		}
	}

	@Override
	public int insertAlba(AlbaVO av) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			int cnt = mapper.insertAlba(av);
			if(cnt>0&&av.getLicList()!=null) {
				List<LicenseVO> llv = av.getLicList();
				for (int i = 0; i < llv.size(); i++) {
					llv.get(i).setAl_id(av.getAl_id());
					cnt = mapper.insertLicAlba(llv.get(i));
					if(cnt<0) {
						return cnt;
					}
				}
			}
			if(cnt>0) {
				sqlSession.commit();
			}
		return cnt;
		}
	}
	@Override
	public List<GradeVO> selectGradeList() {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
		return mapper.selectGradeList();
		}
	}
	@Override
	public LicenseVO selectLicImg(LicenseVO lv) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
		return mapper.selectLicImg(lv);
		}
	}
	@Override
	public List<LicenseVO> selectLicenseList() {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
		return mapper.selectLicenseList();
		}
	}
	@Override
	public int insertLicAlba(LicenseVO lv) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			IAlbaDAO mapper = sqlSession.getMapper(IAlbaDAO.class);
			int cnt = mapper.insertLicAlba(lv);
		return cnt;
		}
	}

}
