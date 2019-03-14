package com.rent.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rent.common.utils.NumPageUtil;
import com.rent.common.utils.RestfulUtil;
import com.rent.door.HouseInfo;
import com.rent.entity.*;
import com.rent.services.PrHouseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author liwenxiang
 * Date:2019/2/23
 * Time:15:45
 */
@Controller
public class LockManageController {

    @Autowired
    private PrHouseService prHouseService;

    @RequestMapping("lockManage.do")
    public String toBuildingNoAdd(Integer currpage,String lockName,String deviceid,String ifBind, ModelMap map, HttpSession session, HttpServletRequest request) {
        // 处理当前页
        if (currpage==null || currpage <= 0) {
            currpage = 1;
        }
        Integer size = 10; // 页大小
        int totalCount = 0;
        String params = "";
        String params2 = "";
        if("1".equals(ifBind)){
            if(StringUtils.isNotBlank(lockName)&&StringUtils.isNotBlank(deviceid)){
                params = "{\"method\": \"thing.service.GetNodeList\",\"params\": {\"Conditions\": {\"_deviceid\":\""+deviceid+"\",\"Name\":\""+lockName+"\",\"IfBind\":true,\"_nodetype\":\"lock\",\"_nodeid\":1},\"PageSize\": "+size+", \"Page\": "+currpage+"}} ";
            }else if(StringUtils.isNotBlank(lockName)){
                params = "{\"method\": \"thing.service.GetNodeList\",\"params\": {\"Conditions\": {\"Name\":\""+lockName+"\",\"IfBind\":true,\"_nodetype\":\"lock\",\"_nodeid\":1},\"PageSize\": "+size+", \"Page\": "+currpage+"}} ";
            }else if(StringUtils.isNotBlank(deviceid)){
                params = "{\"method\": \"thing.service.GetNodeList\",\"params\": {\"Conditions\": {\"_deviceid\":\""+deviceid+"\",\"IfBind\":true,\"_nodetype\":\"lock\",\"_nodeid\":1},\"PageSize\": "+size+", \"Page\": "+currpage+"}} ";
            }else{
                params = "{\"method\":\"thing.service.GetNodeList\",\"params\":{\"Conditions\":{\"_nodetype\":\"lock\",\"IfBind\":true,\"_nodeid\":1},\"PageSize\":"+size+",\"Page\":"+currpage+"}}";
            }
        }else if(StringUtils.isBlank(ifBind)||"0".equals(ifBind)){
            if(StringUtils.isNotBlank(lockName)&&StringUtils.isNotBlank(deviceid)){
                params = "{\"method\": \"thing.service.GetNodeList\",\"params\": {\"Conditions\": {\"_deviceid\":\""+deviceid+"\",\"Name\":\""+lockName+"\",\"_nodetype\":\"lock\",\"_nodeid\":1},\"PageSize\": "+size+", \"Page\": "+currpage+"}} ";
            }else if(StringUtils.isNotBlank(lockName)){
                params = "{\"method\": \"thing.service.GetNodeList\",\"params\": {\"Conditions\": {\"Name\":\""+lockName+"\",\"_nodetype\":\"lock\",\"_nodeid\":1},\"PageSize\": "+size+", \"Page\": "+currpage+"}} ";
            }else if(StringUtils.isNotBlank(deviceid)){
                params = "{\"method\": \"thing.service.GetNodeList\",\"params\": {\"Conditions\": {\"_deviceid\":\""+deviceid+"\",\"_nodetype\":\"lock\",\"_nodeid\":1},\"PageSize\": "+size+", \"Page\": "+currpage+"}} ";
            }else{
                params = "{\"method\":\"thing.service.GetNodeList\",\"params\":{\"Conditions\":{\"_nodetype\":\"lock\",\"_nodeid\":1},\"PageSize\":"+size+",\"Page\":"+currpage+"}}";
            }
        }else{
            if(StringUtils.isNotBlank(lockName)&&StringUtils.isNotBlank(deviceid)){
                params = "{\"method\": \"thing.service.GetNodeList\",\"params\": {\"Conditions\": {\"_deviceid\":\""+deviceid+"\",\"Name\":\""+lockName+"\",\"_nodetype\":\"lock\",\"_nodeid\":1}}} ";
            }else if(StringUtils.isNotBlank(lockName)){
                params = "{\"method\": \"thing.service.GetNodeList\",\"params\": {\"Conditions\": {\"Name\":\""+lockName+"\",\"_nodetype\":\"lock\",\"_nodeid\":1}}} ";
            }else if(StringUtils.isNotBlank(deviceid)){
                params = "{\"method\": \"thing.service.GetNodeList\",\"params\": {\"Conditions\": {\"_deviceid\":\""+deviceid+"\",\"_nodetype\":\"lock\",\"_nodeid\":1}}} ";
            }else{
                params = "{\"method\":\"thing.service.GetNodeList\",\"params\":{\"Conditions\":{\"_nodetype\":\"lock\",\"_nodeid\":1}}}";
            }

            if(StringUtils.isNotBlank(lockName)&&StringUtils.isNotBlank(deviceid)){
                params2 = "{\"method\": \"thing.service.GetNodeList\",\"params\": {\"Conditions\": {\"_deviceid\":\""+deviceid+"\",\"Name\":\""+lockName+"\",\"IfBind\":true,\"_nodetype\":\"lock\",\"_nodeid\":1}}}";
            }else if(StringUtils.isNotBlank(lockName)){
                params2 = "{\"method\": \"thing.service.GetNodeList\",\"params\": {\"Conditions\": {\"Name\":\""+lockName+"\",\"IfBind\":true,\"_nodetype\":\"lock\",\"_nodeid\":1}}} ";
            }else if(StringUtils.isNotBlank(deviceid)){
                params2 = "{\"method\": \"thing.service.GetNodeList\",\"params\": {\"Conditions\": {\"_deviceid\":\""+deviceid+"\",\"IfBind\":true,\"_nodetype\":\"lock\",\"_nodeid\":1}}} ";
            }else{
                params2 = "{\"method\":\"thing.service.GetNodeList\",\"params\":{\"Conditions\":{\"_nodetype\":\"lock\",\"IfBind\":true,\"_nodeid\":1}}}";
            }
        }

        String result = RestfulUtil.postHttps(params,"app");
        JSONArray pageArray = null;
        List<Lock> pageList = new ArrayList<>();

        // 处理数据
        JSONObject resultMap = JSON.parseObject(result);
        int resultcode = resultMap.getIntValue("resultcode");
        if(!"2".equals(ifBind)) {
            if (!RestfulUtil.checkNull(resultMap.getJSONObject("data"))) {
                JSONObject data = resultMap.getJSONObject("data");
                totalCount = data.getIntValue("TotalCount");
                if (totalCount > 0) {
                    pageArray = data.getJSONArray("PageList");
                    for (int i = 0; i < pageArray.size(); i++) {
                        Lock lock = JSON.parseObject(pageArray.get(i).toString(), Lock.class);
                        if (lock.getName() == null) {
                            lock.setName("");
                        }
                        if (lock.getHouseid() != null) {
                            HouseInfo houseInfo = prHouseService.findRoomInfoById(lock.getHouseid());
                            if (houseInfo != null) {
                                lock.setHousedesc(houseInfo.getDistrictName() + houseInfo.getUnitName() + houseInfo.getRoomNo() + "房");
                            }
                        }
                        pageList.add(lock);
                    }
                }
            }
        }else{
            String result2 = RestfulUtil.postHttps(params2,"app");//所有绑定的门锁
            JSONObject bindResultMap = JSON.parseObject(result2);
            if (!RestfulUtil.checkNull(resultMap.getJSONObject("data"))&&!RestfulUtil.checkNull(bindResultMap.getJSONObject("data"))) {
                JSONObject data = resultMap.getJSONObject("data");
                JSONObject data2 = bindResultMap.getJSONObject("data");
                totalCount = data.getIntValue("TotalCount")-data2.getIntValue("TotalCount");
                if (totalCount > 0) {
                    JSONArray pageArray1 = data.getJSONArray("PageList");
                    JSONArray pageArray2 = data2.getJSONArray("PageList");
                }
            }
        }
        ifBind = StringUtils.isBlank(ifBind)?"0":ifBind;
        String url = "lockManage.do?lockName="+(StringUtils.isNotBlank(lockName)?lockName:"")+"&deviceid="+(StringUtils.isNotBlank(deviceid)?deviceid:"&ifBind="+ifBind);
        NumPageUtil page = new NumPageUtil(url, totalCount, currpage, size);
        page.setBothnum(3);
        String numpage = page.showNumPage();
        currpage = page.getCurrpage();
        // 存作用域
        page.setList(pageList);
        map.put("page", page);
        session.setAttribute("currpage", currpage);
        request.setAttribute("numpage", numpage);
        map.put("deviceid",deviceid);
        map.put("lockName",lockName);
        map.put("ifBind",ifBind);
        return "prh/lock/lockManage.jsp";

    }

    @RequestMapping("lockNameEdit.do")
    @ResponseBody
    public String lockNameEdit(String deviceid,String houseid,String lockName) {
        JSONArray pageArray0 = null;
        boolean ifBind = false;
        String params0 = "{\"method\": \"thing.service.GetNodeList\",\"params\": {\"Conditions\": {\"_deviceid\":\""+deviceid+"\"}}}";
        String result0 = RestfulUtil.postHttps(params0,"app");
        JSONObject resultMap0 = JSON.parseObject(result0);
        if (!RestfulUtil.checkNull(resultMap0.getJSONObject("data"))) {
            JSONObject data0 = resultMap0.getJSONObject("data");
            if (data0.getIntValue("TotalCount") > 0) {
                pageArray0 = data0.getJSONArray("PageList");
                for (int i = 0; i < pageArray0.size(); i++) {
                    Lock lock = JSON.parseObject(pageArray0.get(i).toString(), Lock.class);
                    if (lock.isIfBind()) {
                        ifBind=true;
                    }
                }
            }
        }

        String params = "{\"method\": \"thing.service.SetNodeExtendedAttribute\",\"deviceid\": \""+deviceid+"\",\"nodeid\":1,\"params\":{\"Name\":\""+lockName+"\",\"Houseid\":"+houseid+",\"IfBind\":"+ifBind+"}}";
        String result = RestfulUtil.postHttps(params,"app");
        JSONObject resultMap = JSON.parseObject(result);
        int resultcode = resultMap.getIntValue("resultcode");
        if(resultcode==1) {
            return "success";
        }else{
            return "failed";
        }
    }
    @RequestMapping("toLockNameEdit.do")
    public String toLockNameEdit(String baseinfo, ModelMap map) {
        String[] info = baseinfo.split("@");
        String deviceid = info[0];
        String lockName = "";
        String houseid = "0";
        try{
            lockName = info[1];
            houseid = info[2];
        }catch (Exception e){}
        map.put("lockName", lockName);
        map.put("deviceid",deviceid);
        map.put("houseid",houseid);

        return "prh/lock/lockNameEdit.jsp";
    }


}
