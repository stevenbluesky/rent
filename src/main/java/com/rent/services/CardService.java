package com.rent.services;

import java.util.List;

import com.rent.entity.Card;
import com.rent.entity.HistoryIdenPwd;

public interface CardService {
	void addCard(Card card);
	void editCard(Card card);
	void delCard(Integer id);
	Card getById(Integer id);
	List<Card> findByMasterAndLink(Integer masterId, Integer linkId, Integer type, Integer state);

	Integer getNextId();
	
	   Integer getAuthorizeIdSeq();
	    
	   Integer getRoomNumSeq();
	    
	   Integer getRoomIdSeq();
	   
	   int addHistoryIdenPwd(HistoryIdenPwd historyIdenPwd);
	   
	   List<HistoryIdenPwd> findAllHistoryIdenPwd();
	   
	   List<HistoryIdenPwd> findHistoryIdenPwdByPerson(Integer master, Integer linkId);
}
