package com.rent.services;

import com.rent.entity.Attachment;
import com.rent.entity.DoorlockUser;

import java.util.List;

public interface DoorlockUserService {
	
	DoorlockUser selectByPrimaryKey(Integer doorlockuserid);

	int insert(DoorlockUser record);

	int updateByPrimaryKey(DoorlockUser record);

	List<DoorlockUser> findAvailableByDeviceid(String associatedlock);

	void updateDoorlockUser(String msg);

    List<DoorlockUser> findAllByMasterid(Integer id);

    List<DoorlockUser> findAllByGuestno(String guestNo);

	List<DoorlockUser> findAvailableAllByMasterid(Integer id);
}
