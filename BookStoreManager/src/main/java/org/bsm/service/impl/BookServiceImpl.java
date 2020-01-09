package org.bsm.service.impl;

import org.bsm.service.BookServiceI;
import org.springframework.stereotype.Service;
@Service(value="bookI")
public class BookServiceImpl implements BookServiceI {

	@Override
	public void test() {
		System.out.println("GZC........");
	}

}
