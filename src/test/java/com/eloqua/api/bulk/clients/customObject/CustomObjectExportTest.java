package com.eloqua.api.bulk.clients.customObject;

import com.eloqua.api.bulk.BulkClient;
import com.eloqua.api.bulk.models.Data;
import com.eloqua.api.bulk.models.Export;
import com.eloqua.api.bulk.models.ExportFilter;
import com.eloqua.api.bulk.models.FilterRuleType;
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
		
		// define the list of fields to be included in the Export
		// use : client.contactFieldClient().search(searchTerm, page, pageSize)
		/*HashMap<String, String> fields = new HashMap<String, String>();
		fields.put("C_EmailAddress", "{{Contact.Field(C_EmailAddress)}}");
		fields.put("C_FirstName", "{{Contact.Field(C_FirstName)}}");*/

		// TODO: remove next block
		HashMap<String, String> fields = new HashMap<>();
        fields.put("filedId", "{{fieldExpression}}");

		// define the filter criteria for the export
        // use : client.contactFilterClient().search(searchTerm, page, pageSize)
        ExportFilter filter = new ExportFilter();
		
		// create the definition for the Export
		Export export = new Export();
		export.name = "sample export";
		export.fields = fields;
		// TODO: uncomment export.filter = filter;
		// export.secondsToAutoDelete = 3600;
		// export.secondsToRetainData = 3600;
				
		Export exportResult = client.customObjectExportClient().createExport(export, 1);

		// create the sync for the Export
		Sync sync = new Sync();
		sync.syncedInstanceUri = exportResult.uri;
		
		// check Sync results (use polling)
		Sync syncResult = client.customObjectExportClient().createSync(sync);
		assertNotNull(syncResult);
		
		// retrieve the data
		String data = client.customObjectExportClient().getExportData(exportResult.uri);
		assertNotNull(data);
	}
}
