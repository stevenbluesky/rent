package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.dao.AttachmentMapper;
import com.rent.entity.Attachment;
import com.rent.services.AttachmentService;
@Service
public class AttachmentServiceImpl implements AttachmentService{

	
	@Autowired
	private AttachmentMapper attachmentMapper;
	
	
	public AttachmentMapper getAttachmentMapper() {
		return attachmentMapper;
	}

	public void setAttachmentMapper(AttachmentMapper attachmentMapper) {
		this.attachmentMapper = attachmentMapper;
	}
	

	@Override
	public void add(Attachment attachment) {
		attachmentMapper.insert(attachment);
		
	}

	@Override
	public void edit(Attachment attachment) {
		attachmentMapper.updateByPrimaryKey(attachment);
		
	}

	@Override
	public void del(Integer id) {
		attachmentMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public Attachment findById(Integer id) {
		return attachmentMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Attachment> findByMaster(Integer masterId) {
		return attachmentMapper.findByMaster(masterId);
	}

	@Override
	public List<Attachment> findAll() {
		return attachmentMapper.findAll();
	}

	@Override
	public Integer deleteByMaster(Integer master) {
		return attachmentMapper.deleteByMaster(master);
	}

}
