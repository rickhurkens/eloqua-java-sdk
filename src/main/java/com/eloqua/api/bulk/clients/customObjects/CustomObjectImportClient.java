package com.eloqua.api.bulk.clients.customObjects;

import java.util.Map;

import com.eloqua.api.Response;
import com.eloqua.api.bulk.BaseClient;
import com.eloqua.api.bulk.models.Import;
import com.eloqua.api.bulk.models.Sync;

public class CustomObjectImportClient {
	private BaseClient _client;
	
	public CustomObjectImportClient(BaseClient client) {
		_client = client;
	}

	public Import createImport(Import contactImport, int customObjectId) {
		Response response = _client.post("/customObject" + customObjectId + "/import", _client.Gson().toJson(contactImport));
		
		Import updatedImport = _client.Gson().fromJson(response.body, Import.class);		

		return updatedImport;
	}
	
	public Sync importData(String importUri, Map<String, String> data) {
		Response response = _client.post(importUri + "/data" , _client.Gson().toJson(data));
		
		Sync sync = _client.Gson().fromJson(response.body, Sync.class);		

		return sync;		
	}
	
	public Sync checkSyncResult(Sync sync) {
		Response response = _client.get(sync.uri + "/results");
		
		Sync syncResults = _client.Gson().fromJson(response.body, Sync.class);		

		return syncResults;
	}
}