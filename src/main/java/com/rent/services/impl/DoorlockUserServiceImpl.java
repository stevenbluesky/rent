package com.rent.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rent.common.config.Global;
import com.rent.dao.DoorlockUserMapper;
import com.rent.entity.DoorlockUser;
import com.rent.services.DoorlockUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liwenxiang
 * Date:2019/2/28
 * Time:17:43
 */
@Transactional
@Service("doorlockUserService")
public class DoorlockUserServiceImpl implements DoorlockUserService {
    @Autowired
    private DoorlockUserMapper doorlockUserMapper;

    @Override
    public DoorlockUser selectByPrimaryKey(Integer doorlockuserid) {
        return doorlockUserMapper.selectByPrimaryKey(doorlockuserid);
    }

    @Override
    public int insert(DoorlockUser record) {
        doorlockUserMapper.insert(record);
        return 0;
    }

    @Override
    public int updateByPrimaryKey(DoorlockUser record) {
        doorlockUserMapper.updateByPrimaryKey(record);
        return 0;
    }

    @Override
    public List<DoorlockUser> findAvailableByDeviceid(String associatedlock) {
        return doorlockUserMapper.selectAvailableByDeviceid(associatedlock);
    }

    @Override
    public void updateDoorlockUser(String msg) {
        String parse = (String) JSONObject.parse(msg);
        JSONObject resultMap = JSON.parseObject(parse);
        int resultcode = resultMap.getIntValue("resultcode");
        String receipt = resultMap.getString("receipt");
        if(resultcode==1){
            DoorlockUser doorlockUser = doorlockUserMapper.selectByReceipt(receipt);
            if(doorlockUser!=null) {
                if (doorlockUser.getStatus() == Global.STATUS_SENDING || doorlockUser.getStatus() == Global.STATUS_UPDATING_TIME) {
                    doorlockUser.setSynstatus(Global.SYN_STATUS_SYNCHRONIZED);
                    doorlockUser.setStatus(Global.STATUS_NORMAL);
                    doorlockUserMapper.updateByPrimaryKey(doorlockUser);
                } else if (doorlockUser.getStatus() == Global.STATUS_DELETEING) {
                    doorlockUserMapper.updateStatustoDeleteByPrimaryKey(doorlockUser.getDoorlockuserid());
                }
            }
        }else{
            //返回失败
            DoorlockUser doorlockUser = doorlockUserMapper.selectByReceipt(receipt);
            if(doorlockUser!=null) {
                if (doorlockUser.getStatus() == Global.STATUS_SENDING ) {
                    doorlockUser.setSynstatus(Global.SYN_STATUS_SYNCHRONIZED);
                    doorlockUser.setStatus(Global.STATUS_SEND_FAILED);
                }else if(doorlockUser.getStatus() == Global.STATUS_UPDATING_TIME){
                    doorlockUser.setSynstatus(Global.SYN_STATUS_SYNCHRONIZED);
                    doorlockUser.setStatus(Global.STATUS_UPDATE_TIME_FAILED);
                }
                doorlockUserMapper.updateByPrimaryKey(doorlockUser);
            }
        }
    }

    @Override
    public List<DoorlockUser> findAllByMasterid(Integer masterid) {
        return doorlockUserMapper.findAllByMasterid(masterid);
    }

    @Override
    public List<DoorlockUser> findAllByGuestno(String guestNo) {
        return doorlockUserMapper.findAllByGuestno(guestNo);
    }

    @Override
    public List<DoorlockUser> findAvailableAllByMasterid(Integer id) {
        return doorlockUserMapper.findAvailableAllByMasterid(id);
    }

    @Override
    public List<DoorlockUser> findAllByGuestNo(String guestno) {
        return doorlockUserMapper.findAllByGuestno(guestno);
    }

    @Override
    public List<DoorlockUser> findInUseByMasterid(Integer masterId) {
        return doorlockUserMapper.findInUseByMasterid(masterId);
    }
}
