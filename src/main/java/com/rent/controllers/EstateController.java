package com.rent.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.org.apache.xpath.internal.operations.Mod;

import com.rent.entity.Estate;
import com.rent.entity.EstateType;

import com.rent.services.EstateService;
@Controller("estateController")
public class EstateController {
	
	@Autowired
	private EstateService estateService;
	
	
	public EstateService getEstateService() {
		return estateService;
	}

	public void setEstateService(EstateService estateService) {
		this.estateService = estateService;
	}
	//ת��������ҵҳ��
	@RequestMapping("toAddEstate.do")
	public String toAddEstate(ModelMap map) {
		List<EstateType> types = estateService.findAllEstateTypes();
		map.put("types", types);
						
		return "prhCode/estate/estateAdd.jsp";
	}
	
	//��ѯȫ��
	@RequestMapping("findAllEstate.do")
	public String findAll(ModelMap map){
		List<Estate> estates = estateService.findAll();
		map.put("estates", estates);
		return "prhCode/estate/estate.jsp";
	}
	
	//����
	@RequestMapping("addEstate.do")
	public String addEstate(Estate estate, ModelMap map){
		System.out.println(estate.getName());
		if (estateService.findByName(estate.getName())==null) {
			int result = estateService.addEstate(estate);
			 if (result==-1) {
				return "prhCode/estate/estateAdd.jsp";
			}	
		}else{
			map.put("tip", "物业不能重名！");
			map.put("estate", estate);
			
			return "../toAddEstate.do";
		}
		return "../findAllEstate.do";
	}
	//ת���޸�ҳ��
	@RequestMapping("toEditEstate.do")
	public String toEditEstate(Integer id, ModelMap map){
		Estate estate = estateService.findById(id);
		map.put("estate", estate);
		List<EstateType> types = estateService.findAllEstateTypes();
		map.put("types", types);
		return "prhCode/estate/estateEdit.jsp";
	}
	//�޸�
	@RequestMapping("editEstate.do")
	public String editEstate(Estate estate, ModelMap map){
		Estate oldEstate = estateService.findById(estate.getId());
		Estate findByName = estateService.findByName(estate.getName());
		//重名
		if (findByName!=null&&!findByName.getName().equals(oldEstate.getName())) {
			map.put("tip", "物业不能重名！");
			map.put("estate", estate);
			return "../toAddEstate.do";
		}
		
		estate.setAuthorCode(oldEstate.getAuthorCode());
		estate.setPrice(oldEstate.getPrice());
		estate.setMapPic(oldEstate.getMapPic());
		estateService.updateEstate(estate);
		return "../findAllEstate.do";
	}
	
	//ɾ��
	@RequestMapping("delEstate.do")
	public String delEstate(Integer id,ModelMap map){
		
		try {
			estateService.delEstate(id);
			map.put("tip", "删除成功！");
		} catch (Exception e) {
			map.put("tip", "该条数据已被关联，请勿删除！");
		}
		return "../findAllEstate.do";
	}
	
}
