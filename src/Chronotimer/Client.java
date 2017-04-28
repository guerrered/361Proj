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


public class Client {
	Console console;
	static DataOutputStream out;
	static HttpURLConnection conn;
	List <ExportObject> exportList = new ArrayList<>();
	List <ServerObject> soList = new ArrayList<>();
	
	
	public class ServerObject{
		int id;
		long time;
		String code;
		
		public ServerObject(int id, long time, String code){
			this.id = id;
			this.time = time;
			this.code = code;
		}
	}
	
	public void send(){
		populateServerObjectList();
		
		System.out.println("Sending Data");
		URL site = null;
		
		try {
			site = new URL("http://localhost:8000/sendresults");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conn = (HttpURLConnection) site.openConnection();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// now create a POST request
		try {
			conn.setRequestMethod("POST");
		} catch (ProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		conn.setDoOutput(true);
		conn.setDoInput(true);
		//DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		try {
			out = new DataOutputStream(conn.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//send info
		try{
			Gson gson = new Gson();
			out.writeBytes(gson.toJson(soList));
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
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		conn.disconnect();
	}

	private void populateServerObjectList() {
		exportList = console.getExportList();
		
		for(ExportObject eo: exportList){
			soList.add(new ServerObject(Integer.parseInt(eo.getID()), eo.getTimeRaw(), eo.getCode()));
		}
	}
}


