package com.wideoapp.WideoAppSecurity;

import com.netflix.discovery.converters.Auto;
import com.wideoapp.WideoAppSecurity.Dao.LikesDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WideoAppSecurityApplicationTests {

	@Autowired
	LikesDao likesDao;

	@Test
	void contextLoads() {
		likesDao.removeByVideoIdAndUserId(1,1);
	}

}
