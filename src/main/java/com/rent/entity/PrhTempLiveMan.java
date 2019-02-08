package com.rent.entity;

import java.util.Date;

public class PrhTempLiveMan {
	
	private String idenPwd;
	
	private Card identityCard;
	
	private Profile profile;
	
	private PrhMaster prhMaster;
	
	private Card card;
	
    private Integer id;

    private Integer masterId;

    private String guestno;

    private Date bdate;

    private Date edate;

    private Integer del;

    
    
    public String getIdenPwd() {
		return idenPwd;
	}

	public void setIdenPwd(String idenPwd) {
		this.idenPwd = idenPwd;
	}

	public Card getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(Card identityCard) {
		this.identityCard = identityCard;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public PrhMaster getPrhMaster() {
		return prhMaster;
	}

	public void setPrhMaster(PrhMaster prhMaster) {
		this.prhMaster = prhMaster;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public String getGuestno() {
        return guestno;
    }

    public void setGuestno(String guestno) {
        this.guestno = guestno;
    }

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}