package com.eloqua.api.bulk.clients.accounts;

import com.eloqua.api.Response;
import com.eloqua.api.bulk.BaseClient;
import com.eloqua.api.bulk.models.Field;
import com.eloqua.api.bulk.models.SearchResponse;

public class AccountFieldClient {
	private BaseClient client;
	
	public AccountFieldClient(BaseClient client) {
		this.client = client;
	}
	
	public SearchResponse<Field> search(String searchTerm, int page, int pageSize)
	{
		Response response = client.get("/account/fields?search=" + searchTerm + "&page=" + page + "&pageSize=" + pageSize );
						
		SearchResponse<Field> fields = client.getGson().fromJson(response.body, SearchResponse.class);

		return fields;
	}
}
