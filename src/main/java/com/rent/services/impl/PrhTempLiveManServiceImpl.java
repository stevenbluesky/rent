package com.rent.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rent.dao.PrhTempLiveManMapper;
import com.rent.entity.Card;
import com.rent.entity.PrhLinkman;
import com.rent.entity.PrhTempLiveMan;
import com.rent.services.CardService;
import com.rent.services.FileManagementService;
import com.rent.services.PrhMasterService;
import com.rent.services.PrhTempLiveManService;



@Service
public class PrhTempLiveManServiceImpl implements PrhTempLiveManService{

	@Autowired
	private PrhTempLiveManMapper prhTempLiveManMapper;
	@Autowired
	private FileManagementService fileManagementService;
	@Autowired
	private PrhMasterService prhMasterService;
	@Autowired
	private CardService cardService;


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

	public CardService getCardService() {
		return cardService;
	}

	public void setCardService(CardService cardService) {
		this.cardService = cardService;
	}

	public PrhTempLiveManMapper getPrhTempLiveManMapper() {
		return prhTempLiveManMapper;
	}

	public void setPrhTempLiveManMapper(PrhTempLiveManMapper prhTempLiveManMapper) {
		this.prhTempLiveManMapper = prhTempLiveManMapper;
	}



	// 封装导航属性
	private void setGuideProperty(List<PrhTempLiveMan> prhLinkmans) {
		for (PrhTempLiveMan b : prhLinkmans) {
			b.setProfile(fileManagementService.findById(b.getGuestno()));
			b.setPrhMaster(prhMasterService.findById(b.getMasterId()));
				/*List<Card> cards = cardService.findByMasterAndLink(b.getMasterId(),b.getId(),0,1);

				if (cards!=null&&cards.size()!=0) {
					b.setCard(cards.get(0));
				}
				List<Card> icards = cardService.findByMasterAndLink(b.getMasterId(),b.getId(), 1,1);

				if (icards!=null&&icards.size()!=0) {
					b.setIdentityCard(icards.get(0));

				}
				System.out.println("sososso"+b.getIdentityCard());*/

		}
	}

	// 封装导航属性
	private void setGuideProperty(PrhTempLiveMan b) {
		b.setProfile(fileManagementService.findById(b.getGuestno()));
		b.setPrhMaster(prhMasterService.findById(b.getMasterId()));
			
			/*List<Card> cards = cardService.findByMasterAndLink(b.getMasterId(),b.getId(),0,1);
			if (cards!=null&&cards.size()!=0) {
				b.setCard(cards.get(0));
			}
			List<Card> icards = cardService.findByMasterAndLink(b.getMasterId(),b.getId(),1,1);
			if (icards!=null&&icards.size()!=0) {
				b.setIdentityCard(icards.get(0));
			}*/


	}

	@Override
	public List<PrhTempLiveMan> findByMaster(Integer masterId) {
		List<PrhTempLiveMan> mans = prhTempLiveManMapper.findByMaster(masterId);
		setGuideProperty(mans);
		return mans;
	}

}
