package com.rent.services;

import java.util.Date;
import java.util.List;

import com.rent.entity.Subsidy;
import com.rent.entity.SubsidyPercent;
import com.rent.services.impl.SubsidyCal;


public interface SubsidyService {

	/**
	 * ����
	 * @return ִ�н��1��-1
	 */
	int addSubsidy(Subsidy subsidy);
	/**
	 * �޸�
	 * @return ִ�н��1��-1
	 */
	int updateSubsidy(Subsidy subsidy);
	
	/**
	 * ɾ��
	 * @return ִ�н��1��-1
	 */
	int delSubsidy(Integer id);
	

	/**
	 * ɾ��(��������)
	 * @return ִ�н��1��-1
	 */
	int delSubsidy(Integer[] ids);
	/**
	 * ����������ѯ
	 * @return ִ�н��1��-1
	 */
	Subsidy findById(Integer id);
	
	/**
	 * ��ҳ��ѯȫ��
	 * @param ��ǰҳ
	 * @param ҳ��С
	 * @return �������ͼ���
	 */
	
	List<Subsidy> findByEstatePaged(Integer estateId, Integer currpage,Integer size);
	
	/**
	 * ��ѯ����
	 * @return ����
	 */
	Integer getCountByEstate(int id);
	
	int addSubsidyPercent(SubsidyPercent subsidyPercent);
	
	int updateSubsidyPercent(SubsidyPercent subsidyPercent);
	/**
     * ��ȡ��ǰ������id
     * @return id
     */
    Integer getCurrId();
	
	Integer getCurrSubsidyPercentId();
	
	 
    /**
     * 查询需要计算不补贴信心 
     * @param estateId
     * @param typeId
     * @param inOrOut
     * @param date
     * @return 
     */
   SubsidyCal findSubsidyCal(int estateId,int typeId,int inOrOut,Date date);
}
