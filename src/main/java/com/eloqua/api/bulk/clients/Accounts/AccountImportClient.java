package com.eloqua.api.bulk.clients.accounts;

import java.util.Map;
import com.eloqua.api.Response;
import com.eloqua.api.bulk.BaseClient;
import com.eloqua.api.bulk.models.Import;
import com.eloqua.api.bulk.models.Sync;

// TODO: update to comply with current version of API
public class AccountImportClient {
	private BaseClient client;
	
	public AccountImportClient(BaseClient client) {
		this.client = client;
	}

	public Import createImport(Import accountImport) {
		Response response = client.post("/account/import", client.getGson().toJson(accountImport));
		
		Import updatedImport = client.getGson().fromJson(response.body, Import.class);

		return updatedImport;
	}
	
	public Sync importData(String importUri, Map<String, String> data) {
		Response response = client.post(importUri + "/data" , client.getGson().toJson(data));
		
		Sync sync = client.getGson().fromJson(response.body, Sync.class);

		return sync;		
	}
	
	public Sync checkSyncResult(Sync sync) {
		Response response = client.get(sync.uri + "/results");
		
		Sync syncResults = client.getGson().fromJson(response.body, Sync.class);

		return syncResults;
	}
}