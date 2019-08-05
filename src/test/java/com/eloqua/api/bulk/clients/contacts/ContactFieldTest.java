package com.eloqua.api.bulk.clients.contacts;

import static org.junit.Assert.*;

import org.junit.Test;
import com.eloqua.api.bulk.BulkClient;
import com.eloqua.api.bulk.models.Field;
import com.eloqua.api.bulk.models.SearchResponse;

public class ContactFieldTest {

	@Test
	public void SearchFieldsTests() {
		BulkClient client = new BulkClient("site", "user", "password", "https://secure.eloqua.com/API/Bulk/1.0");
		
		int count = 1;
		SearchResponse<Field> fields = client.contactFieldClient().search("*", 1, count);

		assertEquals(count, fields.total);
	}
}