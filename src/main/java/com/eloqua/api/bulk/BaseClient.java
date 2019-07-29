package com.eloqua.api.bulk;

import com.google.gson.Gson;

public class BaseClient extends com.eloqua.api.BaseClient {

	private Gson gson;

	public BaseClient(String site, String user, String password, String url) {
		super(site, user, password, url);
	}

	public Gson getGson() {
		if (gson == null) {
			gson = new com.google.gson.Gson();
		}
		return gson;
	}
}
