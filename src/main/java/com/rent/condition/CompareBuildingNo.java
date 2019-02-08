package com.rent.condition;
import java.util.Comparator;
import java.util.List;

import com.rent.entity.BuildingNo;

import java.util.ArrayList;
import java.util.Collections;

public class CompareBuildingNo implements Comparator{
	@Override
	public int compare(Object o1, Object o2) {
		BuildingNo no1= (BuildingNo)o1;
		BuildingNo no2= (BuildingNo)o2;

		String name1=no1.getName();
		String name2=no2.getName();
		
		Integer id1=Integer.valueOf(name1.substring(0, name1.length()-2));
		Integer id2=Integer.valueOf(name2.substring(0, name2.length()-2));
		 if(id1> id2){  
             return 1;  
         }  
         if(id1== id2){  
             return 0;  
         }  
         
		return 0;
	}  
  
 
}


