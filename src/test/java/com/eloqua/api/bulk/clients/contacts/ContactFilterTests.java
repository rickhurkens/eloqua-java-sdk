package com.eloqua.api.bulk.clients.contacts;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import com.eloqua.api.bulk.BulkClient;
import com.eloqua.api.bulk.models.ExportFilter;
import com.eloqua.api.bulk.models.SearchResponse;

public class ContactFilterTests {
	
	@Test
	public void SearchFiltersTests() {
		BulkClient client = new BulkClient("site", "user", "password", "https://secure.eloqua.com/API/Bulk/1.0");

		int count = 1;
		SearchResponse<ExportFilter> filters = client.contactFilterClient().search("*", 1, count);
		assertEquals(count, filters.total);
	}
}