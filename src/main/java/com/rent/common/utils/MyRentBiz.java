package com.rent.common.utils;

import java.util.List;

import com.rent.entity.Estate;

public class MyRentBiz {
	/**
	 * 通过物业集合获取首个物业
	 * @param estates
	 * @return
	 */
	public static Estate getEstates(List<Estate> estates) {
		if (estates != null && estates.size() != 0) {
			return estates.get(0);
		}
		return null;

	}
}
