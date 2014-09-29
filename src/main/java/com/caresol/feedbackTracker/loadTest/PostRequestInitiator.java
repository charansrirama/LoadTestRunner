/**
 * 
 */
package com.caresol.feedbackTracker.loadTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.BatchUpdateException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

/**
 * @author csriram2
 * 
 */
public class PostRequestInitiator implements Runnable {

	static String[] boothNames = { "Research&Innovation", "HIE", "OCS", "HSR",
			"FWA", "SS", "SDV" };
	static Integer[] ratings = { 1, 2, 3, 4, 5 };

	@Override
	public void run() {

		// Fire 10 post requests sequentially with 1 second difference
		// for (int i = 0; i < 10; i++) {
		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(
					"http://caresolrestapi-eaas.rhcloud.com/barcode/create");

			JSONObject json = new JSONObject();
			Map<String, String> map = new HashMap<String, String>();

			Random random = new Random();

			String barcode = ((Integer) random.nextInt(Integer.MAX_VALUE))
					.toString();
			System.out.println("Barcode: " + barcode);
			// ((Double) (Math.random() * 1000)).toString();

			String boothName = boothNames[random.nextInt(boothNames.length)];
			System.out.println("BoothName: " + boothName);

			map.put("barCode", barcode);
			map.put("boothId", boothName);

			json.put("id", map);
			String rating = ratings[random.nextInt(ratings.length)].toString();
			json.put("rating", rating);

			StringEntity input = new StringEntity(json.toString());
			input.setContentType("application/json");

			post.setEntity(input);
			HttpResponse response = client.execute(post);
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

		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// System.out.println("Thread Interrupted");
		// e.printStackTrace();
		// }
		// }
	}
}
