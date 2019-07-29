package com.eloqua.api.bulk.clients.accounts;

import com.eloqua.api.Response;
import com.eloqua.api.bulk.BaseClient;
import com.eloqua.api.bulk.models.ExportFilter;
import com.eloqua.api.bulk.models.SearchResponse;

public class AccountFilterClient {
	private BaseClient client;

	public AccountFilterClient(BaseClient client) {
		this.client = client;
	}
	
	public SearchResponse<ExportFilter> search(String searchTerm, int page, int pageSize)
	{
		Response response = client.get("/account/filters?search=" + searchTerm + "&page=" + page + "&pageSize=" + pageSize );
						
		SearchResponse<ExportFilter> fields = client.getGson().fromJson(response.body, SearchResponse.class);

		return fields;
	}
}