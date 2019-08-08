package com.eloqua.api.bulk.clients.contacts;

import com.eloqua.api.Response;
import com.eloqua.api.bulk.BaseClient;
import com.eloqua.api.bulk.models.Field;
import com.eloqua.api.bulk.models.SearchResponse;

// TODO: update to comply with current version of API
public class ContactFieldClient {

	private BaseClient client;
	
	public ContactFieldClient(BaseClient client) {
		this.client = client;
	}
	
	public SearchResponse<Field> search(String searchTerm, int page, int pageSize)
	{
		Response response = client.get("/contact/fields?search=" + searchTerm + "&page=" + page + "&pageSize=" + pageSize );
						
		SearchResponse<Field> fields = client.getGson().fromJson(response.body, SearchResponse.class);

		return fields;
	}
}