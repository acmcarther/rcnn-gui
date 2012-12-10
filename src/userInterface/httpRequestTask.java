package userInterface;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TimerTask;

public class httpRequestTask extends TimerTask {
	public void run() {
		// TODO: Take a host parameter -- we wont always be using localhost
	    
	    URL url;
	    HttpURLConnection conn;
	    BufferedReader rd;
	    String line;
	    String result = "";
		

	    try {
	    	url = new URL("http://localhost:8888/graph/snapshot");
	    	conn = (HttpURLConnection) url.openConnection();
	    	conn.setRequestMethod("GET");
	    	rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    	while ((line = rd.readLine()) != null) {
	    		result += line;
	    	}
	    	rd.close();
	    } catch (Exception e) {
	    	System.out.println("connection failed");
	    	//e.printStackTrace();
	    }
	    
		System.out.println(result);
	}
}
