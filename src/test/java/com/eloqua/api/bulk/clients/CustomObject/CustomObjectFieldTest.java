package com.eloqua.api.bulk.clients.customObject;

import com.eloqua.api.bulk.BulkClient;
import com.eloqua.api.bulk.models.Field;
import com.eloqua.api.bulk.models.SearchResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomObjectFieldTest {

	@Test
	public void SearchFieldsTests() {
		BulkClient client = new BulkClient("site", "user", "password", "https://secure.eloqua.com/API/Bulk/1.0");

		SearchResponse<Field> fields = client.customObjectFieldClient().search("*", 229, 1, 38);
        //SearchResponse<Field> fields = client.customObjectFieldClient().search(229, 1, 0);
        //SearchResponse<Field> fields = client.customObjectFieldClient().search(229);

		assertEquals(1, 1);
	}
}