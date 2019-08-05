package com.eloqua.api.bulk.clients.customObjects;

import com.eloqua.api.Response;
import com.eloqua.api.bulk.BaseClient;
import com.eloqua.api.bulk.models.Export;
import com.eloqua.api.bulk.models.Sync;

import java.util.HashMap;
import java.util.Map;

public class CustomObjectExportClient {
	private BaseClient client;

	private Map<String,String> exportUriToSyncUri = new HashMap<>();

	public CustomObjectExportClient(BaseClient client) {
		this.client = client;
	}

	public Export createExport(Export export, int customObjectId) {
		Response response = client.post("customObjects/" + customObjectId + "/exports", client.getGson().toJson(export));
		
		Export updatedExport = client.getGson().fromJson(response.body, Export.class);

		return updatedExport;
	}

	// TODO: exact copy of customObjectExportClient
	public Sync createSync(Sync sync) {
		String syncUri = getSyncUri(sync);
		Response response = client.get(syncUri);
		
		Sync updatedSync = client.getGson().fromJson(response.body, Sync.class);

		return updatedSync;		
	}
	
	public String getExportData(String exportUri) {
		Response response = client.get(exportUri + "/data");
		return response.body;
	}

    public String getExportData(String exportUri, String limit, String offset) {
        Response response = client.get(exportUri + "/data" + "?limit=" + limit + "&offset=" + offset);
        return response.body;
    }

	private String getSyncUri(Sync sync) {
        if (exportUriToSyncUri.containsKey(sync.syncedInstanceUri)) {
            return exportUriToSyncUri.get(sync.syncedInstanceUri);
        }
        Response response = client.post("syncs", client.getGson().toJson(sync));
        Sync intermediateSyncObject = client.getGson().fromJson(response.body, Sync.class);
        exportUriToSyncUri.put(sync.syncedInstanceUri, intermediateSyncObject.uri);
        return intermediateSyncObject.uri;
    }
}
