package com.caresol.feedbackTracker.loadTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public class GetRequestInitiator {

	public static void main(String[] args) {

		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(
					"http://caresolrestapi-eaas.rhcloud.com/barcode/list");
			HttpResponse response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			System.out.println("Cannot Submit the request");
			e.printStackTrace();
		}
	}
}
