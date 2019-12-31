package org.bsm.impl;

import org.bsm.interfa.BookI;
import org.springframework.stereotype.Service;
@Service(value="bookI")
public class BookServiceImpl implements BookI {

	@Override
	public void test() {
		System.out.println("GZC........");
	}

}
