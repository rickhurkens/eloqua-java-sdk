package com.eloqua.api.bulk.clients.accounts;

import com.eloqua.api.Response;
import com.eloqua.api.bulk.BaseClient;
import com.eloqua.api.bulk.models.Export;
import com.eloqua.api.bulk.models.Sync;

// TODO: update to comply with current version of API
public class AccountExportClient {
	private BaseClient client;
	
	public AccountExportClient(BaseClient client) {
		client = client;
	}

	public Export createExport(Export export) {
		Response response = client.post("/account/export", client.getGson().toJson(export));
		
		Export updatedExport = client.getGson().fromJson(response.body, Export.class);

		return updatedExport;
	}
	
	public Sync createSync(Sync sync) {
		Response response = client.post("/sync", client.getGson().toJson(sync));
		
		Sync updatedSync = client.getGson().fromJson(response.body, Sync.class);

		return updatedSync;		
	}
	
	public String getExportData(String exportUri) {
		Response response = client.get(exportUri + "/data");
		
		return response.body;
	}
}
