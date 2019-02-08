package com.rent.services;

import java.util.List;

import com.rent.entity.RepaireReason;

public interface RepaireReasonService {
	/**
	 * ����
	 * @return ִ�н��1��-1
	 */
	int addRepaireReason(RepaireReason repaireReason);
	/**
	 * �޸�
	 * @return ִ�н��1��-1
	 */
	int updateRepaireReason(RepaireReason repaireReason);
	
	/**
	 * ɾ��
	 * @return ִ�н��1��-1
	 */
	int delRepaireReason(Integer id);
	

	/**
	 * ɾ��(��������)
	 * @return ִ�н��1��-1
	 */
	int delRepaireReason(Integer[] ids);
	/**
	 * ����������ѯ
	 * @return ִ�н��1��-1
	 */
	RepaireReason findById(Integer id);
	
	/**
	 * ��ҳ��ѯȫ��
	 * @param ��ǰҳ
	 * @param ҳ��С
	 * @return �������ͼ���
	 */
	List<RepaireReason> findAllPaged(Integer currpage,Integer size);
	
	/**
	 * ��ѯ����
	 * @return ����
	 */
	Integer getTotalCount();
}
