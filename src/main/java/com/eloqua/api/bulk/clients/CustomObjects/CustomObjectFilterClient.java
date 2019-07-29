package com.eloqua.api.bulk.clients.customObjects;

import com.eloqua.api.Response;
import com.eloqua.api.bulk.BaseClient;
import com.eloqua.api.bulk.models.ExportFilter;
import com.eloqua.api.bulk.models.SearchResponse;

public class CustomObjectFilterClient {
	private BaseClient client;

	public CustomObjectFilterClient(BaseClient client) {
		this.client = client;
	}
	
	public SearchResponse<ExportFilter> search(String searchTerm, int customObjectId, int page, int pageSize)
	{
		Response response = client.get("/customObject/" +customObjectId + "/filters?search=" + searchTerm + "&page=" + page + "&pageSize=" + pageSize );
						
		SearchResponse<ExportFilter> fields = client.getGson().fromJson(response.body, SearchResponse.class);

		return fields;
	}
}
