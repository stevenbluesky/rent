package com.rent.modules.sys.entity;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rent.common.persistence.DataEntity;

public class Repaire extends DataEntity<Repaire>{

	private static final long serialVersionUID = 1L;
	
	private String tenement;
	private String building;
	private String floor;    
	private Integer roomNum;  
	private String roomType; 
	private String roomSize; 
	private String repairer; 
	private String repairerPhone;// phone number
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date repairerTime; 
	
	private String repaireType; 
	private String comments; 
	private String approvalStatus; 
	private String status; 
	private String isDelete; 
	
	private String checker; 
	private String work;    
	private String equipment; 
	
	private String image;    
	private String payment;
	private String nameImage;
	private String applier;
	private String thirdCom;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startTime;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endTime;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date validateTime;
	
	private int masterID;
	
	private int houseID;
	private String houseStatus;
	private String originHouseStatus;
	private List<Payment> payments;
	private List<String> houseStatusList;
	private int sum;
	// public boolean flag; //flag for everything you needed

	public Repaire(){
		super();
	}
	
	public Repaire(String id){
		super(id);
	}
	
	@Override
	public String getId() {
		if(id != null && id.length() >0) {
			return id;
		} else{
			return null;
		}
	}

	public String getTenement() {
		return tenement;
	}

	public void setTenement(String address) {
		if(address != null && address.length() >0) {
			this.tenement = address;
		} else{
			this.tenement = null;
		}
	}

	public Integer getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(Integer roomNum) {
		if(roomNum != null ) {
			this.roomNum = roomNum;
		} else{
			this.roomNum = null;
		}
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(String roomSize) {
		this.roomSize = roomSize;
	}

	public String getRepairer() {
		return repairer;
	}

	public void setRepairer(String repairer) {
		if(repairer != null && repairer.length() >0) {
			this.repairer = repairer.trim();
		} else{
			this.repairer = null;
		}
	}

	public Date getRepairerTime() {
		return repairerTime;
	}

	public void setRepairerTime(Date repairerTime) {
		this.repairerTime = repairerTime;
	}

	public String getRepaireType() {
		return repaireType;
	}

	public void setRepaireType(String repairerType) {
		this.repaireType = repairerType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comment) {
		this.comments = comment;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getRepairerPhone() {
		return repairerPhone;
	}

	public void setRepairerPhone(String repairerPhone) {
		this.repairerPhone = repairerPhone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payments) {
		this.payment = payments;
	}

	public String getNameImage() {
		return this.nameImage;
	}

	public void setNameImage(String nameImage) {
		this.nameImage = nameImage;
		this.image = nameImage;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getApplier() {
		return applier;
	}

	public void setApplier(String applier) {
		this.applier = applier;
	}

	public String getThirdCom() {
		return thirdCom;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getValidateTime() {
		return validateTime;
	}

	public void setValidateTime(Date validateTime) {
		this.validateTime = validateTime;
	}

	public void setThirdCom(String thirdCom) {
		this.thirdCom = thirdCom;
	}

	public int getMasterID() {
		return masterID;
	}

	public void setMasterID(int masterID) {
		this.masterID = masterID;
	}

	public int getHouseID() {
		return houseID;
	}

	public void setHouseID(int houseID) {
		this.houseID = houseID;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
		this.sum = 0;
		if(payments != null && payments.size() > 0) {
			for(Iterator<Payment> p = payments.iterator();p.hasNext();){
				Payment tmp = p.next();
				this.sum+= tmp.getSum();
			}
		}
	}

	public String getHouseStatus() {
		return houseStatus;
	}

	public void setHouseStatus(String houseStatus) {
		this.houseStatus = houseStatus;
		// this.flag = houseStatus == null ? false:true;
	}

	public List<String> getHouseStatusList() {
		return houseStatusList;
	}

	public void setHouseStatusList(List<String> houseStatusList) {
		this.houseStatusList = houseStatusList;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public String getOriginHouseStatus() {
		return originHouseStatus;
	}

	public void setOriginHouseStatus(String originHouseStatus) {
		this.originHouseStatus = originHouseStatus;
	}

}
