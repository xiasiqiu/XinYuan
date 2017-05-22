package com.xinyuan.xyshop.util;

import com.xinyuan.xyshop.entity.Brand;


import java.util.Comparator;

/**
 * Created by Administrator on 2017/5/22.
 */

public class LetterComparator implements Comparator<Brand> {


	@Override
	public int compare(Brand brand, Brand t1) {
		if (brand == null && t1 == null) {
			return 0;
		}

		String one = brand.getBrandInitial();
		String two = t1.getBrandInitial();
		if (one == null || two == null) {
			return 0;
		}
		return one.compareTo(two);

	}
}
