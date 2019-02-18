package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.dao.PrhLinkmanMapper;
import com.rent.entity.PrhLinkman;
import com.rent.entity.Card;
import com.rent.entity.Estate;
import com.rent.services.CardService;
import com.rent.services.FileManagementService;
import com.rent.services.PrhLinkManService;
import com.rent.services.PrhMasterService;
@Service
@Transactional(readOnly = true)
public class PrhLinkManServiceImpl implements PrhLinkManService{


	@Autowired
	private PrhLinkmanMapper prhLinkmanMapper;

	@Autowired
	private FileManagementService fileManagementService;
	@Autowired
	private PrhMasterService prhMasterService;

	@Autowired
	private CardService cardService;

	public CardService getCardService() {
		return cardService;
	}

	public void setCardService(CardService cardService) {
		this.cardService = cardService;
	}

	public FileManagementService getFileManagementService() {
		return fileManagementService;
	}

	public void setFileManagementService(FileManagementService fileManagementService) {
		this.fileManagementService = fileManagementService;
	}

	public PrhMasterService getPrhMasterService() {
		return prhMasterService;
	}

	public void setPrhMasterService(PrhMasterService prhMasterService) {
		this.prhMasterService = prhMasterService;
	}

	public PrhLinkmanMapper getPrhLinkmanMapper() {
		return prhLinkmanMapper;
	}

	public void setPrhLinkmanMapper(PrhLinkmanMapper prhLinkmanMapper) {
		this.prhLinkmanMapper = prhLinkmanMapper;
	}


	// 封装导航属性
	private void setGuideProperty(List<PrhLinkman> prhLinkmans) {
		for (PrhLinkman b : prhLinkmans) {
			b.setProfile(fileManagementService.findById(b.getGuestno()));
			b.setPrhMaster(prhMasterService.findById(b.getMasterId()));
			List<Card> cards = cardService.findByMasterAndLink(b.getMasterId(),b.getId(),0,1);

			if (cards!=null&&cards.size()!=0) {
				b.setCard(cards.get(0));
			}
			List<Card> icards = cardService.findByMasterAndLink(b.getMasterId(),b.getId(), 1,1);

			if (icards!=null&&icards.size()!=0) {
				b.setIdentityCard(icards.get(0));

			}
			System.out.println("sososso"+b.getIdentityCard());

		}
	}

	// 封装导航属性
	private void setGuideProperty(PrhLinkman b) {
		b.setProfile(fileManagementService.findById(b.getGuestno()));
		b.setPrhMaster(prhMasterService.findById(b.getMasterId()));
		List<Card> cards = cardService.findByMasterAndLink(b.getMasterId(),b.getId(),0,1);
		if (cards!=null&&cards.size()!=0) {
			b.setCard(cards.get(0));
		}
		List<Card> icards = cardService.findByMasterAndLink(b.getMasterId(),b.getId(),1,1);
		if (icards!=null&&icards.size()!=0) {
			b.setIdentityCard(icards.get(0));
		}


	}
	@Override
	public List<PrhLinkman> findAllByMaster(int masterId) {

		List<PrhLinkman> allByMaster = prhLinkmanMapper.getAllByMaster(masterId);
		setGuideProperty(allByMaster);
		return allByMaster;
	}

	@Override
	public int addPrhLinkman(PrhLinkman prhLinkman) {
		return prhLinkmanMapper.insert(prhLinkman) ;
	}

	@Override
	public int addPrhLinkman(List<PrhLinkman> prhLinkmans) {
		for (PrhLinkman p : prhLinkmans) {
			prhLinkmanMapper.insert(p) ;
		}
		return 1;
	}


	@Override
	public int updatePrhLinkman(PrhLinkman prhLinkman) {
		return prhLinkmanMapper.updateByPrimaryKey(prhLinkman);
	}
	@Override
	public int updatePrhLinkman(List<PrhLinkman> prhLinkmans) {
		for (PrhLinkman p : prhLinkmans) {
			prhLinkmanMapper.updateByPrimaryKey(p);
		}
		return 1;
	}

	public PrhLinkman findById(Integer id) {
		PrhLinkman selectByPrimaryKey = prhLinkmanMapper.selectByPrimaryKey(id);
		setGuideProperty(selectByPrimaryKey);
		return selectByPrimaryKey;
	}

	@Override
	public int delPrhLinkman(Integer id) {
		return prhLinkmanMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int getTotalCountByMaster(int masterId ) {
		return prhLinkmanMapper.getAllByMaster(masterId).size();
	}

}
