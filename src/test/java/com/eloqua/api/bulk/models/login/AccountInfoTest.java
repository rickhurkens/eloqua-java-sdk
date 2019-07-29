package com.eloqua.api.bulk.models.login;

import org.junit.Test;
import com.eloqua.api.bulk.BulkClient;

public class AccountInfoTest {

	@Test
	public void GetAccountInfoTest() {		
		AccountInfo info = BulkClient.getAccountInfo("site", "user", "password");
		String baseUrl = info.urls.apis.rest.bulk;
	}
}
