package com.eloqua.api.bulk;

import com.eloqua.api.Response;
import com.eloqua.api.bulk.clients.contacts.*;
import com.eloqua.api.bulk.clients.customObjects.*;
import com.eloqua.api.bulk.models.login.AccountInfo;

public class BulkClient extends BaseClient {
		public BulkClient(String site, String user, String password, String url) {
		super(site, user, password, url);
	}
	
	public static AccountInfo GetAccountInfo(String site, String user, String password) {
		BaseClient client = new BaseClient(site, user, password, "https://login.eloqua.com");
		Response response = client.get("/id");
		
		AccountInfo info = client.Gson().fromJson(response.body, AccountInfo.class);		
        return info;
	}
		
	public ContactFieldClient ContactFieldClient() {
		if (_contactFieldClient == null)
			_contactFieldClient = new ContactFieldClient(this);

		return _contactFieldClient;
	}	
	private ContactFieldClient _contactFieldClient;

	public ContactFilterClient ContactFilterClient() {
		if (_contactFilterClient == null)
			_contactFilterClient = new ContactFilterClient(this);

		return _contactFilterClient;
	}	
	private ContactFilterClient _contactFilterClient;

	public ContactImportClient ContactImportClient() {
		if (_contactImportClient == null)
			_contactImportClient = new ContactImportClient(this);

		return _contactImportClient;
	}	
	private ContactImportClient _contactImportClient;
	
	public ContactExportClient ContactExportClient() {
		if (_contactExportClient == null)
			_contactExportClient = new ContactExportClient(this);

		return _contactExportClient;
	}	
	private ContactExportClient _contactExportClient;

	public CustomObjectFieldClient CustomObjectFieldClient() {
		if (_CustomObjectFieldClient == null)
			_CustomObjectFieldClient = new CustomObjectFieldClient(this);

		return _CustomObjectFieldClient;
	}	
	private CustomObjectFieldClient _CustomObjectFieldClient;

	public CustomObjectFilterClient CustomObjectFilterClient() {
		if (_CustomObjectFilterClient == null)
			_CustomObjectFilterClient = new CustomObjectFilterClient(this);

		return _CustomObjectFilterClient;
	}	
	private CustomObjectFilterClient _CustomObjectFilterClient;

	public CustomObjectImportClient CustomObjectImportClient() {
		if (_CustomObjectImportClient == null)
			_CustomObjectImportClient = new CustomObjectImportClient(this);

		return _CustomObjectImportClient;
	}	
	private CustomObjectImportClient _CustomObjectImportClient;
	
	public CustomObjectExportClient CustomObjectExportClient() {
		if (_CustomObjectExportClient == null)
			_CustomObjectExportClient = new CustomObjectExportClient(this);

		return _CustomObjectExportClient;
	}	
	private CustomObjectExportClient _CustomObjectExportClient;
	
}
