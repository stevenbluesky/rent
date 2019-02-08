package com.rent.services;



import java.util.List;

import org.springframework.stereotype.Service;

import com.rent.modules.sys.entity.Repaire;


public interface RepaireService {
	
	List<Repaire> findByMaster(Integer masterId);
}
