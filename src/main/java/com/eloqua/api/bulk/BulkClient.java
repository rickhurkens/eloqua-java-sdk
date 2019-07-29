package com.eloqua.api.bulk;

import com.eloqua.api.Response;
import com.eloqua.api.bulk.clients.contacts.*;
import com.eloqua.api.bulk.clients.customObjects.*;
import com.eloqua.api.bulk.models.login.AccountInfo;

public class BulkClient extends BaseClient {
		public BulkClient(String site, String user, String password, String url) {
		super(site, user, password, url);
	}

	private ContactFieldClient contactFieldClient;
	private ContactFilterClient contactFilterClient;
	private ContactImportClient contactImportClient;
	private ContactExportClient contactExportClient;
	private CustomObjectFieldClient customObjectFieldClient;
	private CustomObjectFilterClient customObjectFilterClient;
	private CustomObjectImportClient customObjectImportClient;
	private CustomObjectExportClient customObjectExportClient;
	
	public static AccountInfo getAccountInfo(String site, String user, String password) {
		BaseClient client = new BaseClient(site, user, password, "https://login.eloqua.com");
		Response response = client.get("/id");

		response.body = response.body.replaceAll("\\{version}", "2.0");

		AccountInfo info = client.getGson().fromJson(response.body, AccountInfo.class);
        return info;
	}
		
	public ContactFieldClient contactFieldClient() {
		if (contactFieldClient == null)
			contactFieldClient = new ContactFieldClient(this);

		return contactFieldClient;
	}

	public ContactFilterClient contactFilterClient() {
		if (contactFilterClient == null)
			contactFilterClient = new ContactFilterClient(this);

		return contactFilterClient;
	}

	public ContactImportClient contactImportClient() {
		if (contactImportClient == null)
			contactImportClient = new ContactImportClient(this);

		return contactImportClient;
	}
	
	public ContactExportClient contactExportClient() {
		if (contactExportClient == null)
			contactExportClient = new ContactExportClient(this);

		return contactExportClient;
	}

	public CustomObjectFieldClient customObjectFieldClient() {
		if (customObjectFieldClient == null)
			customObjectFieldClient = new CustomObjectFieldClient(this);

		return customObjectFieldClient;
	}

	public CustomObjectFilterClient customObjectFilterClient() {
		if (customObjectFilterClient == null)
			customObjectFilterClient = new CustomObjectFilterClient(this);

		return customObjectFilterClient;
	}

	public CustomObjectImportClient customObjectImportClient() {
		if (customObjectImportClient == null)
			customObjectImportClient = new CustomObjectImportClient(this);

		return customObjectImportClient;
	}
	
	public CustomObjectExportClient customObjectExportClient() {
		if (customObjectExportClient == null)
			customObjectExportClient = new CustomObjectExportClient(this);

		return customObjectExportClient;
	}
}
