package org.bsm.service.impl;

import org.bsm.service.BookI;
import org.springframework.stereotype.Service;
@Service(value="bookI")
public class BookServiceImpl implements BookI {

	@Override
	public void test() {
		System.out.println("GZC........");
	}

}
