package com.eloqua.api.bulk.clients.customObjects;

import java.util.HashMap;
import java.util.Map;

import com.eloqua.api.bulk.exception.EloquaSyncFailedException;
import org.apache.commons.lang3.StringUtils;

import com.eloqua.api.Response;
import com.eloqua.api.bulk.BaseClient;
import com.eloqua.api.bulk.models.Import;
import com.eloqua.api.bulk.models.Sync;

public class CustomObjectImportClient {
	private BaseClient client;

    private Map<String,String> importUriToSyncUri = new HashMap<>();
	
	public CustomObjectImportClient(BaseClient client) {
		this.client = client;
	}

	public Import createImport(Import importDefintion, int customObjectId) {
		Response response = client.post("customObjects/" + customObjectId + "/imports", client.getGson().toJson(importDefintion));
		Import updatedImport = client.getGson().fromJson(response.body, Import.class);
		return updatedImport;
	}
	
	public Sync importData(String importUri, Map<String, String> data) {
        return importData(importUri, client.getGson().toJson(data));
	}

	public Sync importData(String importUri, String jsonData) {
	    Response response = client.post(importUri + "/data" , jsonData);
	    Sync sync = client.getGson().fromJson(response.body, Sync.class);
	    return sync;
    }

    public Sync createSync(Sync sync) throws EloquaSyncFailedException {
        String syncUri = getSyncUri(sync);
        Response response = client.get(syncUri);

        Sync updatedSync = client.getGson().fromJson(response.body, Sync.class);

        return updatedSync;
    }

    private String getSyncUri(Sync sync) throws EloquaSyncFailedException {
        if (importUriToSyncUri.containsKey(sync.syncedInstanceUri)) {
            return importUriToSyncUri.get(sync.syncedInstanceUri);
        }
        Response response = client.post("syncs", client.getGson().toJson(sync));
        Sync intermediateSyncObject = client.getGson().fromJson(response.body, Sync.class);
        if (intermediateSyncObject != null && StringUtils.isNotBlank(intermediateSyncObject.uri)) {
            importUriToSyncUri.put(sync.syncedInstanceUri, intermediateSyncObject.uri);
            return intermediateSyncObject.uri;

        } else {
            throw new EloquaSyncFailedException(sync.syncedInstanceUri);
        }
    }
}