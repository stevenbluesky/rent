package com.rent.dao;

import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.entity.DoorlockUser;

import java.util.List;

@MyBatisDao
public interface DoorlockUserMapper {

    int insert(DoorlockUser record);

    DoorlockUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(DoorlockUser record);

    List<DoorlockUser> selectAvailableByDeviceid(String associatedlock);

    DoorlockUser selectByReceipt(String receipt);

    List<DoorlockUser> findAllByMasterid(Integer masterid);

    List<DoorlockUser> findAllByGuestno(String guestNo);

    void deleteByPrimaryKey(Integer doorlockuserid);

    List<DoorlockUser> findAvailableAllByMasterid(Integer id);
}