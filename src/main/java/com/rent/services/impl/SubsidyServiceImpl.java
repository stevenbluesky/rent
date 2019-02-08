package com.rent.services.impl;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.dao.EstateMapper;
import com.rent.dao.SubsidyMapper;
import com.rent.dao.SubsidyPercentMapper;
import com.rent.dao.SubsidyTypeMapper;
import com.rent.dao.SubsidyWithTypeMapper;

import com.rent.entity.PrHouse;
import com.rent.entity.Subsidy;
import com.rent.entity.SubsidyPercent;
import com.rent.entity.SubsidyType;
import com.rent.entity.SubsidyWithType;
import com.rent.services.BuildingNoService;
import com.rent.services.EstateService;
import com.rent.services.RoomTypeService;
import com.rent.services.SubsidyService;
@Service
@Transactional(readOnly = true)
public class SubsidyServiceImpl implements SubsidyService{
	@Autowired
	private EstateMapper estateMapper;
	@Autowired
	private SubsidyPercentMapper subsidyPercentMapper;
	@Autowired
	private SubsidyMapper subsidyMapper;
	
	@Autowired
	private SubsidyWithTypeMapper subsidyWithTypeMapper;
	@Autowired
	private SubsidyTypeMapper subsidyTypeMapper;

	public SubsidyWithTypeMapper getSubsidyWithTypeMapper() {
		return subsidyWithTypeMapper;
	}
	public void setSubsidyWithTypeMapper(SubsidyWithTypeMapper subsidyWithTypeMapper) {
		this.subsidyWithTypeMapper = subsidyWithTypeMapper;
	}
	public EstateMapper getEstateMapper() {
		return estateMapper;
	}
	public void setEstateMapper(EstateMapper estateMapper) {
		this.estateMapper = estateMapper;
	}

	public SubsidyPercentMapper getSubsidyPercentMapper() {
		return subsidyPercentMapper;
	}

	public void setSubsidyPercentMapper(SubsidyPercentMapper subsidyPercentMapper) {
		this.subsidyPercentMapper = subsidyPercentMapper;
	}

	public SubsidyMapper getSubsidyMapper() {
		return subsidyMapper;
	}

	public void setSubsidyMapper(SubsidyMapper subsidyMapper) {
		this.subsidyMapper = subsidyMapper;
	}


	public SubsidyTypeMapper getSubsidyTypeMapper() {
		return subsidyTypeMapper;
	}
	public void setSubsidyTypeMapper(SubsidyTypeMapper subsidyTypeMapper) {
		this.subsidyTypeMapper = subsidyTypeMapper;
	}
	/**
	 * 设置导航属性
	 * @param subsidies
	 */
	public void setGuideProperty(List<Subsidy> subsidies){
		for (Subsidy s : subsidies) {
			s.setEstate(estateMapper.selectByPrimaryKey(s.getEstateId()));
			s.setSubsidyPercent(subsidyPercentMapper.selectByPrimaryKey(s.getInPercentId()));
			List<SubsidyWithType> withTypes = subsidyWithTypeMapper.findAll();
			//类型表
			List<SubsidyType> subsidyTypes=subsidyTypeMapper.findAll();
			//封装补贴人类型补贴比例
			Map<Integer, Double> ins=new HashMap<Integer, Double>();
			Map<Integer, Double> outs=new HashMap<Integer, Double>();

			//补贴人类型（先把所有类型名初始化到key）
			
			for (SubsidyType sType : subsidyTypes) {
				ins.put(sType.getId(),null);
				outs.put(sType.getId(), null);
			}
			
			if (withTypes!=null&&withTypes.size()!=0) {
				for (SubsidyWithType w  : withTypes) {
					Double num= Double.parseDouble((new DecimalFormat("#0.0000").format(w.getPercent()))) ;
					if (w.getSubsidyId()==s.getId()&& w.getInOrOut()==1) {
						ins.put(w.getTypeId(), num);
					}else if(w.getSubsidyId()==s.getId()&&w.getInOrOut()==2){
						outs.put(w.getTypeId(), num);
					}
				}
			}
			s.setIn(ins);
			s.setOut(outs);
			
		}
	
	}
	/**
	 * 设置导航属性
	 * @param subsidies
	 */
	public void setGuideProperty(Subsidy s){
		
			s.setEstate(estateMapper.selectByPrimaryKey(s.getEstateId()));
			s.setSubsidyPercent(subsidyPercentMapper.selectByPrimaryKey(s.getInPercentId()));
			//关联表
			List<SubsidyWithType> withTypes = subsidyWithTypeMapper.findAll();
			//类型表
			List<SubsidyType> subsidyTypes=subsidyTypeMapper.findAll();
			Map<Integer, Double> ins=new HashMap<Integer, Double>();
			Map<Integer, Double> outs=new HashMap<Integer, Double>();
			
			//补贴人类型（先把所有类型名初始化到key）
			
			for (SubsidyType sType : subsidyTypes) {
				ins.put(sType.getId(),null);
				outs.put(sType.getId(),null);
			}
			
			if (withTypes!=null&&withTypes.size()!=0) {
				for (SubsidyWithType w  : withTypes) {
					Double num= Double.parseDouble((new DecimalFormat("#0.0000").format(w.getPercent()))) ;
					System.out.println(w.getPercent()+"---"+ num);
					if (w.getSubsidyId()==s.getId()&& w.getInOrOut()==1) {
						ins.put(w.getTypeId(), num);
					}else if(w.getSubsidyId()==s.getId()&&w.getInOrOut()==2){
						outs.put(w.getTypeId(), num);
					}
				}
			}
			s.setIn(ins);
			s.setOut(outs);
			
		
	}
	
	public int addSubsidy(Subsidy subsidy) {
		
		return subsidyMapper.insert(subsidy);
	}

	public int updateSubsidy(Subsidy subsidy) {
		return subsidyMapper.updateByPrimaryKey(subsidy);
	}

	public int delSubsidy(Integer id) {
		return subsidyMapper.deleteByPrimaryKey(id);
	}

	public int delSubsidy(Integer[] ids) {
		int result=0;
		if (ids.length!=0) {
			for (Integer id : ids) {
				result= subsidyMapper.deleteByPrimaryKey(id);
				if (result!=1) {
					return -1;
				}
			}
		}
		return 1;
	}

	public Subsidy findById(Integer id) {
		
		Subsidy subsidy = subsidyMapper.selectByPrimaryKey(id);
		setGuideProperty(subsidy);
		return subsidy ;
	}

	public List<Subsidy> findByEstatePaged(Integer estateId, Integer currpage, Integer size) {
		Integer begin=(currpage-1)*size+1;
		Integer end=begin+size-1;
		
		List<Subsidy> subsidies =null;
		if (estateId!=null) {
			subsidies = subsidyMapper.findByEstatePaged(estateId, begin, end);
			setGuideProperty(subsidies);	
		}
		
		return  subsidies;
		
	}

	public Integer getCountByEstate(int estateId) {
		
		return subsidyMapper.getCountByEstate(estateId);
	}
	
	/**
	 * 获取当前自增的id
	 */
	public Integer getCurrSubsidyPercentId() {
     return	subsidyPercentMapper.getCurrId();
		
	}
	public int addSubsidyPercent(SubsidyPercent subsidyPercent) {
		return subsidyPercentMapper.insert(subsidyPercent);
	}
	public int updateSubsidyPercent(SubsidyPercent subsidyPercent) {
		return subsidyPercentMapper.insert(subsidyPercent);
	}
	public Integer getCurrId() {
		return subsidyMapper.getCurrId();
	}
	@Override
	public SubsidyCal findSubsidyCal(int estateId, int typeId, int inOrOut, Date date) {
		List<SubsidyCal> findSubsidyCal = subsidyMapper.findSubsidyCal(estateId, typeId, inOrOut,date );
		
		return findSubsidyCal!=null&&findSubsidyCal.size()!=0?findSubsidyCal.get(0):null;
	}
	


}
