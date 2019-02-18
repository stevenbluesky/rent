package com.rent.services.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.common.utils.GenerateSequenceUtil;
import com.rent.dao.EstateMapper;
import com.rent.dao.EstateTypeMapper;

import com.rent.entity.Estate;
import com.rent.entity.EstateType;
import com.rent.services.EstateService;

@Service
@Transactional(readOnly = true)
public class EstateServiceImpl implements EstateService {

	@Autowired
	private EstateMapper estateMapper;
	@Autowired
	private EstateTypeMapper estateTypeMapper;

	public EstateTypeMapper getEstateTypeMapper() {
		return estateTypeMapper;
	}

	public void setEstateTypeMapper(EstateTypeMapper estateTypeMapper) {
		this.estateTypeMapper = estateTypeMapper;
	}

	public EstateMapper getEstateMapper() {
		return estateMapper;
	}

	public void setEstateMapper(EstateMapper estateMapper) {
		this.estateMapper = estateMapper;
	}

	// 封装导航属性
	private void setGuideProperty(List<Estate> estates) {
		for (Estate e : estates) {
			// 物业类型
			EstateType type = estateTypeMapper
					.selectByPrimaryKey(e.getTypeId());
			e.setEstateType(type);
		}
	}

	// 封装导航属性
	private void setGuideProperty(Estate e) {
		// 物业类型

		EstateType type = estateTypeMapper.selectByPrimaryKey(e.getTypeId());
		e.setEstateType(type);

	}

	// 查询全部
	public List<Estate> findAll() {
		List<Estate> estates = estateMapper.findAll();
		this.setGuideProperty(estates);
		return estates;
	}

	// 新增
	public int addEstate(Estate estate) {
		Estate e = this.findById(estate.getId());
		if (e != null) {
			return -1;
		}


		String code= GenerateSequenceUtil.generateSequenceNo();
		if (code.length()!=24) {
			if (code.length()>24) {
				code= code.substring(0, 24);
			}else{
				int num= 24-code.length();
				for (int i = 0; i < num; i++) {
					Random r=new Random();
					String s=r.nextInt(10)+"";
					code+=s;
				}
			}
		}
		estate.setAuthorCode(code);
		return estateMapper.insert(estate);
	}

	// 修改
	public int updateEstate(Estate estate) {

		return estateMapper.updateByPrimaryKey(estate);
	}

	// 删除
	public int delEstate(Integer id) {
		return estateMapper.deleteByPrimaryKey(id);
	}

	// 根据主键查询
	public Estate findById(Integer id) {
		return estateMapper.selectByPrimaryKey(id);
	}

	// 查询所有物业类型
	public List<EstateType> findAllEstateTypes() {
		List<EstateType> types = estateTypeMapper.findAll();
		return types;
	}

	// 按名称查找
	public Estate findByName(String name) {
		Estate estate = estateMapper.findByName(name);
		if (estate!=null) {
			setGuideProperty(estate);
		}
		return estate;
	}
	private String getEstateCode(int estateId){
		String id = estateId+"";
		int length=16-id.length();
		String before="";
		for (int i = 0; i < length; i++) {
			before+="0";
		}
		return before+id;
	}
}
