package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.dao.CardMapper;
import com.rent.dao.HistoryIdenPwdMapper;
import com.rent.entity.Card;
import com.rent.entity.HistoryIdenPwd;
import com.rent.services.CardService;
@Service
@Transactional(readOnly = true)
public class CardServiceImpl implements CardService {

	@Autowired
	private CardMapper cardMapper;
	
	@Autowired
	private HistoryIdenPwdMapper historyIdenPwdMapper;
	
	
	public HistoryIdenPwdMapper getHistoryIdenPwdMapper() {
		return historyIdenPwdMapper;
	}

	public void setHistoryIdenPwdMapper(HistoryIdenPwdMapper historyIdenPwdMapper) {
		this.historyIdenPwdMapper = historyIdenPwdMapper;
	}

	public CardMapper getCardMapper() {
		return cardMapper;
	}

	public void setCardMapper(CardMapper cardMapper) {
		this.cardMapper = cardMapper;
	}

	@Override
	public void addCard(Card card) {
		 cardMapper.insert(card);
		
	}

	@Override
	public void editCard(Card card) {
		cardMapper.updateByPrimaryKey(card);
		
	}

	@Override
	public void delCard(Integer id) {
		cardMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Card getById(Integer id) {
		return cardMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Card> findByMasterAndLink(Integer masterId, Integer linkId,Integer type,Integer state) {
		return cardMapper.findByMasterAndLink(masterId, linkId,type,state);
	}

	@Override
	public Integer getNextId() {
		return cardMapper.getNextId();
	}

	@Override
	public Integer getAuthorizeIdSeq() {
		return cardMapper.getAuthorizeIdSeq();
	}

	@Override
	public Integer getRoomNumSeq() {
		return cardMapper.getRoomNumSeq();
	}

	@Override
	public Integer getRoomIdSeq() {
		return cardMapper.getRoomIdSeq();
	}
	@Override
	public int addHistoryIdenPwd(HistoryIdenPwd historyIdenPwd) {
		return historyIdenPwdMapper.insert(historyIdenPwd);
	}
	@Override
	public List<HistoryIdenPwd> findAllHistoryIdenPwd() {
		return historyIdenPwdMapper.findAll();
	}

	@Override
	public List<HistoryIdenPwd> findHistoryIdenPwdByPerson(Integer masterId, Integer linkId) {
		return historyIdenPwdMapper.findByPerson(masterId, linkId);
	}

}
