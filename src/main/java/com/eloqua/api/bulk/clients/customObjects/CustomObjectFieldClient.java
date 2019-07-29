package com.eloqua.api.bulk.clients.customObjects;

import com.eloqua.api.Response;
import com.eloqua.api.bulk.BaseClient;
import com.eloqua.api.bulk.models.Field;
import com.eloqua.api.bulk.models.SearchResponse;

public class CustomObjectFieldClient {
	private BaseClient client;
	
	public CustomObjectFieldClient(BaseClient client) {
		this.client = client;
	}
	
	public SearchResponse<Field> search(String searchTerm, int customObjectId, int page, int pageSize)
	{
		StringBuilder uriBuilder = new StringBuilder("/customObject/" + customObjectId + "/fields?");
		if (searchTerm != null) {
			uriBuilder.append("search=" + searchTerm + "&");
		}
		if (page != 0) {
			uriBuilder.append("page=" + page + "&");
		}
		if (pageSize != 0) {
			uriBuilder.append("pageSize=" + pageSize);
		}
		Response response = client.get(uriBuilder.toString());
		SearchResponse<Field> fields = client.getGson().fromJson(response.body, SearchResponse.class);
		return fields;
	}

	public SearchResponse<Field> search(int customObjectId, int page, int pageSize)
	{
		return search(null, customObjectId, page, page);
	}

	public SearchResponse<Field> search(int customObjectId)
	{
		return search(null, customObjectId, 0, 0);
	}
}