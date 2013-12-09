package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class HttpClientTestGoogleCloudMessage {
	 
	public static void main(String[] args) throws Exception {
 
		HttpClientTestGoogleCloudMessage http = new HttpClientTestGoogleCloudMessage();
 
		System.out.println("\nTesting 2 - Send Http POST request");
		http.sendPost();
 
	}
 
 
	// HTTP POST request
	private void sendPost() throws Exception {
 
		String url = "https://android.googleapis.com/gcm/send";
 
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
 
		// add header
		post.setHeader("Authorization", "key=AIzaSyBIZz-aRfklyGjlqF_GSwYJdeHUopSE8wc");
		post.setHeader("Content-Type", "application/json");
 
		post.setEntity(new StringEntity("{ \"data\": {\"score\": \"5x1\",\"time\": \"15:10\"},\"registration_ids\": [\"4\", \"8\", \"15\", \"16\", \"23\", \"42\"]}"));
 
		HttpResponse response = client.execute(post);
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + post.getEntity());
		System.out.println("Response Code : " + 
                                    response.getStatusLine().getStatusCode());
 
		BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));
 
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
 
		System.out.println(result.toString());
 
	}

}
