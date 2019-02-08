package com.rent.entity;

public class RoomNature {
    
    public RoomNature() {
		super();
	}
    
	public RoomNature(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public RoomNature(Integer id, String name, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.remark = remark;
	}


	private Integer id;

    private String name;

   
    private String remark;

    
    public String getName() {
        return name;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
    public void setName(String name) {
        this.name = name;
    }

    
    public String getRemark() {
        return remark;
    }

   
    public void setRemark(String remark) {
        this.remark = remark;
    }
}