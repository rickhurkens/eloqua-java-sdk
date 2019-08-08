package com.eloqua.api.bulk.clients.contacts;

import com.eloqua.api.Response;
import com.eloqua.api.bulk.BaseClient;
import com.eloqua.api.bulk.models.ExportFilter;
import com.eloqua.api.bulk.models.SearchResponse;

// TODO: update to comply with current version of API
public class ContactFilterClient {

	private BaseClient client;

	public ContactFilterClient(BaseClient client) {
		this.client = client;
	}
	
	public SearchResponse<ExportFilter> search(String searchTerm, int page, int pageSize)
	{
		Response response = client.get("/contact/filters?search=" + searchTerm + "&page=" + page + "&pageSize=" + pageSize );
						
		SearchResponse<ExportFilter> fields = client.getGson().fromJson(response.body, SearchResponse.class);

		return fields;
	}
}
