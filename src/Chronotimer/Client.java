package Chronotimer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import Event.Player;


public class Client {
	static DataOutputStream out;
	static HttpURLConnection conn;
	
	public void send(List<Player> finalList, String url){
		//populateServerObjectList(eo);
		
		
		URL site = null;
		
		try {
			//site = new URL("http://localhost:8000/sendresults");
			site = new URL(url);
			conn = (HttpURLConnection) site.openConnection();
			// now create a POST request
			conn.setRequestMethod("POST");
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			out = new DataOutputStream(conn.getOutputStream());
			
			System.out.println("Sending Data");
			Gson gson = new Gson();
			out.writeBytes(gson.toJson(finalList));
			out.flush();
			
			InputStreamReader inputStr = new InputStreamReader(conn.getInputStream());
			
			// string to hold the result of reading in the response
			StringBuilder sb = new StringBuilder();
			
			// read the characters from the request byte by byte and build up
			// the Response
			int nextChar;
			while ((nextChar = inputStr.read()) > -1) {
				sb = sb.append((char) nextChar);
			}
			System.out.println("Return String: " + sb);
			
			conn.disconnect();
		} catch (IOException e1) {
			System.out.println("no connection established");
		}catch(IllegalArgumentException e2){
			System.out.println("no connection");
		}finally{
			System.out.println("connected");
		}
		
	}

}


