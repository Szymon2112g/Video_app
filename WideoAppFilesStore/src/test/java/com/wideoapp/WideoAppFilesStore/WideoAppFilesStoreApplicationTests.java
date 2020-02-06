package com.wideoapp.WideoAppFilesStore;

import com.wideoapp.WideoAppFilesStore.Storage.StorageService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WideoAppFilesStoreApplicationTests {

	@Autowired
	StorageService storageService;

	@Test
	void contextLoads() {

	}

}
