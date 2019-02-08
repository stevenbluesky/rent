package com.rent.services;

import java.util.List;

import com.rent.entity.PrhTempLiveMan;

public interface PrhTempLiveManService {
	
	List<PrhTempLiveMan> findByMaster(Integer masterId);
}
