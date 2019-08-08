package com.eloqua.api.bulk.clients.customObject;

import com.eloqua.api.bulk.BulkClient;
import com.eloqua.api.bulk.exception.EloquaSyncFailedException;
import com.eloqua.api.bulk.models.Export;
import com.eloqua.api.bulk.models.Sync;
import com.eloqua.api.bulk.models.SyncStatusType;
import com.eloqua.api.bulk.models.login.AccountInfo;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertNotNull;

public class CustomObjectExportTest {
	
	@Test
	public void exportTest()
	{
		// instantiate a new instance of the client
        AccountInfo info = BulkClient.getAccountInfo("site", "user", "password");
		BulkClient client = new BulkClient( "site", "user", "password", info.urls.apis.rest.bulk);

		HashMap<String, String> fields = new HashMap<>();
        fields.put("filedId", "{{fieldExpression}}");

        // define the filter criteria for the export
        // use : client.contactFilterClient().search(searchTerm, page, pageSize)
		String filter = "'{{CustomObject[229].Field(Normalized1)}}' = '1'";

		// create the definition for the Export
		Export export = new Export();
		export.name = "sample export";
		export.fields = fields;
		export.filter = filter;
		// export.secondsToAutoDelete = 3600;
		// export.secondsToRetainData = 3600;
		Export exportResult = client.customObjectExportClient().createExport(export, 1);


        Sync sync = new Sync();
        sync.syncedInstanceUri = exportResult.uri;

        try {
            Sync syncResult = client.customObjectExportClient().createSync(sync);
            while (syncResult.status == SyncStatusType.pending || syncResult.status == SyncStatusType.active) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                syncResult = client.customObjectExportClient().createSync(sync);
            }
        } catch (EloquaSyncFailedException e) {
            e.printStackTrace();
        }

		
		// retrieve the data
		String data = client.customObjectExportClient().getExportData(exportResult.uri);
		assertNotNull(data);
	}
}
