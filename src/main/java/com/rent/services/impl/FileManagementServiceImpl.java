package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.common.utils.MyConvertUtil;
import com.rent.condition.FileCondition;
import com.rent.dao.PrhMasterMapper;
import com.rent.dao.ProfileMapper;
import com.rent.entity.Profile;
import com.rent.services.FileManagementService;

@Service
@Transactional(readOnly = true)
public class FileManagementServiceImpl  implements FileManagementService{
	@Autowired
	private ProfileMapper profileMapper;
	@Autowired
	private PrhMasterMapper prhMasterMapper;

	public ProfileMapper getProfileMapper() {
		return profileMapper;
	}
	public void setProfileMapper(ProfileMapper profileMapper) {
		this.profileMapper = profileMapper;
	}


	@Override
	public List<Profile> findProfileByCondition( String cla,String sta, String name) {

		return profileMapper.findProfileByCondition(cla,sta, name);
	}
	@Override
	public Boolean saveProFile(Profile record) {
		int flag=0;
		try {
			flag =profileMapper.insert(record);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if(flag==0){
			return false;
		}else{
			return true;
		}

	}
	@Override
	@Transactional(readOnly=false)
	public Boolean updateProFile(Profile record) {

		int flag=0;
		try {
			flag =profileMapper.updateByPrimaryKey(record);
			record.setCusno(record.getGuestno());
			profileMapper.updateByCusNoSelective(record);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if(flag==0){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public Boolean deleteProFile(String guestno) {

		int flag=0;
		try {
			flag =profileMapper.deleteByPrimaryKey(guestno);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if(flag==0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public List<Profile> findByCardId(String cardId) {
		return profileMapper.findByCardId(cardId);
	}
	@Override
	public Profile findById(String id) {
		return profileMapper.selectByPrimaryKey(id);
	}
	@Override
	//分页查询
	public List<Profile> findProfileByConditionAndPage(String cla, String sta, String name, Integer currage,
													   Integer size) {
		int begin = (currage - 1) * size + 1;
		int end = begin + size - 1;
		List<Profile> profile = profileMapper.findProfileByConditionAndPage(cla, sta, name, begin, end);
		System.out.println(cla);
		return profile;
	}
	@Override
	public String findName(String guestno) {
		Profile m= profileMapper.findBygusetNo(guestno);
		if(m==null){
			return null;
		}else{
			return m.getName();
		}

	}
	@Override
	public List<Profile> findProfileByConditionAndPage1(FileCondition condition, Integer currage, Integer size) {
		int begin = (currage - 1) * size + 1;
		int end = begin + size - 1;
		condition.setBegin(begin);
		condition.setEnd(end);
		List<Profile> profile = profileMapper.findProfileByConditionAndPage1(condition);

		return profile;
	}
	@Override
	public List<Profile> findProfileByCondition1(FileCondition condition) {

		return profileMapper.findProfileByCondition1(condition);
	}
	@Override
	public List<Profile> findAllCompanyByEstate(Integer estateId) {
		return profileMapper.findAllCompanyByEstateId(estateId);
	}
	@Override
	public String findNameByHouseId(Integer houseid) {

		return profileMapper.findNameByHouseId(houseid);
	}
	@Override
	public List<Profile> findProfileByCondition2(FileCondition condition) {

		return profileMapper.findProfileByCondition2(condition);
	}
	@Override
	public List<Profile> findProfileByConditionAndPage2(FileCondition condition, Integer currpage, Integer size) {
		int begin = (currpage - 1) * size + 1;
		int end = begin + size - 1;
		condition.setBegin(begin);
		condition.setEnd(end);
		List<Profile> profile = profileMapper.findProfileByConditionAndPage2(condition);
		return profile;
	}

	@Override
	public List<Profile> findCompanyByEstatePaged(Integer estateId,String name, Integer currpage, Integer size) {
		Integer[] beginEnd = MyConvertUtil.toPagedBeginEnd(currpage, size);
		return profileMapper.findCompanyByEstatePaged(estateId, beginEnd[0], beginEnd[1],name);
	}
	@Override
	public Integer findCompanyByEstateCount(Integer estateId,String company) {
		return profileMapper.findCompanyByEstateCount(estateId,company);
	}


}
