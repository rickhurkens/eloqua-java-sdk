package com.eloqua.api.bulk.clients.contacts;

import com.eloqua.api.Response;
import com.eloqua.api.bulk.BaseClient;
import com.eloqua.api.bulk.models.Data;
import com.eloqua.api.bulk.models.Export;
import com.eloqua.api.bulk.models.Sync;

// TODO: update to comply with current version of API
public class ContactExportClient {

	private BaseClient client;
	
	public ContactExportClient(BaseClient client) {
		this.client = client;
	}

	public Export createExport(Export export) {
		Response response = client.post("/contact/export", client.getGson().toJson(export));
		
		Export updatedExport = client.getGson().fromJson(response.body, Export.class);

		return updatedExport;
	}
	
	public Sync createSync(Sync sync) {
		Response response = client.post("/sync", client.getGson().toJson(sync));
		
		Sync updatedSync = client.getGson().fromJson(response.body, Sync.class);

		return updatedSync;		
	}
	
	public Data getExportData(String exportUri) {
		Response response = client.get(exportUri + "/data");
		Data data = client.getGson().fromJson(response.body, Data.class);
		return data;
	}
}
