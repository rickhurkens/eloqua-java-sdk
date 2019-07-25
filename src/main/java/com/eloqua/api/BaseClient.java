package com.eloqua.api;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;

public class BaseClient {
	
	private String _authToken;
	private String _baseUrl;

	public BaseClient(String site, String user, String password, String url)
	{
		_baseUrl = url;	

		String authString = site + "\\" + user + ":" + password;
		_authToken = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(authString.getBytes());	
	}

	public Response get(String uri)
	{
		return this.execute(uri, Method.GET, null);
	}

	public Response post(String uri, String body)
	{
		return this.execute(uri, Method.POST, body);
	}

	public Response put(String uri, String body)
	{
		return this.execute(uri, Method.PUT, body);
	}

	public void delete(String uri)
	{
		this.execute(uri, Method.DELETE, null);
	}

	public Response execute(String uri, Method method, String body)
	{
		Response response = new Response();

		try
		{
			URL url = new URL(_baseUrl + uri);

			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod(method.toString());
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", _authToken);
			
			if (method == Method.POST || method == Method.PUT)
			{
				conn.setDoOutput(true);
	
				final OutputStream os = conn.getOutputStream();
				                os.write(body.getBytes());
				                os.flush();
				                os.close();
			}
			
			InputStream is = conn.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader( is));
			
			String line;
			while ((line = rd.readLine()) != null)
			{
				response.body += line;
			}	
			
			rd.close();
						
			response.statusCode = conn.getResponseCode();
			conn.disconnect();			
		}		
		catch (Exception e)		
		{		
			response.exception = e.getMessage();		
		}		     
		return response;		
	}
}