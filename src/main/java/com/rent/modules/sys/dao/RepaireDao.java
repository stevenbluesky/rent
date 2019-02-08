package com.rent.modules.sys.dao;

import java.util.List;

import com.rent.common.persistence.CrudDao;
import com.rent.common.persistence.annotation.MyBatisDao;
import com.rent.modules.sys.entity.Repaire;

@MyBatisDao
public interface RepaireDao extends CrudDao<Repaire> {

		// 获取所有的报修单子
		public List<Repaire> listAllRepaireReport();
		
		// 获取第三方报修单子
		public List<Repaire> findSubmit(Repaire rep);
		
		// 修改选中的单子
		public Repaire updateRepaire(Repaire rep);
		
		// 获取主表ID对应的保修单子
		public List<Repaire> getRepaireByMasterID(Repaire rep);
		
		// 获取houseID对应的报修单子
		public List<Repaire> findByHouse(Repaire rep);
		
		// 删除选中的单子
		public Repaire deleteRepaire(Repaire rep);
		
		// 修改审批状态
		public Repaire updateApprovalStatus(Repaire rep);
		
		// 修改报修单的流程状态
		public Repaire updateRepaireStatus(Repaire rep);
		
		// 增加新的报修单
		public List<Repaire> addNewRepaire(Repaire rep);
		
		// 按条件进行查询
		public List<Repaire> search(Repaire rep);
		
		// 提交审批
		public void addApprover(Repaire rep);
	
		// 提交保修验证
		public void updateStatus(Repaire rep);
		
		// 提交第三方
		public void submitThird(Repaire rep);
		
		// 验证时间
		public void validateUpdate(Repaire rep);
		
		//根据主单差masterId
		List<Repaire> findByMaster(Integer masterId);
}
