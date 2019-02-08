package com.rent.services;

import java.util.List;

import com.rent.entity.Attachment;

public interface AttachmentService {
	
	void add(Attachment attachment);

	void edit(Attachment attachment);

	void del(Integer id);

	Attachment findById(Integer id);

	List<Attachment> findByMaster(Integer masterId);
	
	List<Attachment> findAll();
	
	Integer deleteByMaster(Integer masterId);
}
