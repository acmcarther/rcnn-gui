package controller.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import model.RCNN_Model;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import view.RCNN_View;

public class NetworkController {
	
	String serverAddress;
	String port;
	RCNN_View view;
	RCNN_Model model;
    int refreshCounter = 294;
	
	private String sendGetMessage(URL url){
		// Declare variables
	    HttpURLConnection conn;
	    BufferedReader rd;
	    String line;
	    String result = "";

		
		// Attempt to open network connection
		try {
			// Connect to URL and send message
			conn = (HttpURLConnection) url.openConnection();	
			conn.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			   while ((line = rd.readLine()) != null) {
			    result += line;
			}
			System.out.println(result);
			rd.close();
		} catch (IOException e1) {
			// TODO: Connection Rejected Error
			e1.printStackTrace();
		}

		return result;
	}	
	
	private void sendPostMessage(URL url) {
		// Attempt to open a connection
		try {
			// Connect and send message
	    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    	conn.setRequestMethod("POST");
	    	conn.connect();
	    	conn.disconnect();
	    } catch (IOException e) {
			// TODO: Connection Error
	        e.printStackTrace();
	    }
	}
	
	public NetworkController(RCNN_View view, RCNN_Model model){
		this.view = view;
		this.model = model;
	}
	
	public void setAddress(String serverAddress){
		this.serverAddress = serverAddress;
	}
	
	public void setPort(String port){
		this.port = port;
	}

	public void addNode(String name, String al){
		// TODO: Set Activation Level while creating node
		
		// Attempt to build a URL
    	try {
    		// Create URL object
			URL url = new URL(serverAddress + ":" + port + "/graph/addNode\\?node=" + name);
			
			// Send message to that URL
			sendPostMessage(url);
			
		} catch (MalformedURLException e) {
			// TODO: Malformed URL
	        e.printStackTrace();
		}
	}
	
	public void deleteNode(String name){
		// Attempt to build a URL
    	try {
    		// Create URL object
			URL url = new URL(serverAddress + ":" + port + "/graph/delNode\\?node=" + name);
			
			// Send message to that URL
			sendPostMessage(url);
			
		} catch (MalformedURLException e) {
			// TODO: Malformed URL
	        e.printStackTrace();
		}
	}
	
	public void addEdge(String origin, String destination){
		// Attempt to build a URL
    	try {
    		// Create URL object
			URL url = new URL(serverAddress + ":" + port + "/graph/addEdge\\?from=" + origin + "&to=" + destination);
			
			// Send message to that URL
			sendPostMessage(url);
			
		} catch (MalformedURLException e) {
			// TODO: Malformed URL
	        e.printStackTrace();
		}
	}
	
	public void deleteEdge(String origin, String destination){
		// Attempt to build a URL
    	try {
    		// Create URL object
			URL url = new URL(serverAddress + ":" + port + "/graph/delEdge\\?from=" + origin + "&to=" + destination);
			
			// Send message to that URL
			sendPostMessage(url);
			
		} catch (MalformedURLException e) {
			// TODO: Malformed URL
	        e.printStackTrace();
		}
	}

	public void updateSnapshot(){
		
		// Attempt to construct URL
		try { 
			// Send a GET message
			String result = sendGetMessage(new URL(serverAddress + ":" + port + "/graph/snapshot"));
			
		    // Convert JSON String to a Linked Hash Map 
		    LinkedHashMap<String, Float> nodeMap =  
		    		new Gson().fromJson(result, 
		    				new TypeToken<LinkedHashMap<String, Float>>(){}.getType());
		    
		    // Update the model Hash Map
		    if(refreshCounter < 1){
			    model.updateNodeMap(nodeMap);
		    	refreshCounter = 294;
		    }
		    
		    // Decrement refresh counter
		    refreshCounter--;
		    
		} catch (MalformedURLException e) {
			// TODO: Bad URL Exception
			e.printStackTrace();
		}
	}

	public void updateStream(){
		// TODO: Build asynch networking section
		// This function is designed for streaming data
	}
}
