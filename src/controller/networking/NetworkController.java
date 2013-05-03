package controller.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import model.RCNN_Model;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class NetworkController {
	
	String serverAddress;
	String port;
	RCNN_Model model;
    int refreshCounter = 294;
    boolean connecting = true;
	
	private String sendGetMessage(URL url){
		// Declare variables
	    HttpURLConnection conn;
	    BufferedReader rd;
	    String line;
	    String result = "";

		if(connecting){
			// Attempt to open network connection
			try {
				// Connect to URL and send message
				conn = (HttpURLConnection) url.openConnection();	
				conn.setRequestMethod("GET");
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				   while ((line = rd.readLine()) != null) {
				    result += line;
				}
				rd.close();
			} catch (IOException e1) {
				connecting = false;
				return "NoConnection";
			}
			//System.out.println("----------------");
			//System.out.println(result);
			return result;
		}
		return "NoConnection";
	}	
	
	private void sendPostMessage(URL url) {
		HttpURLConnection conn;
		
		if(connecting){
			// Attempt to open a connection
			try {
				// Connect and send message
				System.out.println(url);
		    	conn = (HttpURLConnection) url.openConnection();
		    	conn.setDoOutput(true);
		    	conn.setRequestMethod("POST");
		    	OutputStreamWriter out = new OutputStreamWriter(
		    		      conn.getOutputStream());
		    	
		    	System.out.println(conn.getResponseCode());
		    	System.out.println(conn.getResponseMessage());
		    	out.close();
		    } catch (IOException e) {
				// TODO: Connection Error
				connecting = false;
		    }
		}
	}
	
	public NetworkController(RCNN_Model model){
		this.model = model;
	}
	
	public void setAddress(String serverAddress){
		this.serverAddress = serverAddress;
	}
	
	public void setPort(String port){
		this.port = port;
	}

	public void addNode(String name, float al){
		// TODO: Set Activation Level while creating node


		// Attempt to build a URL
    	try {
    		// Create URL object
			URL url = new URL(serverAddress + ":" + port + "/graph/addNode?node=" + name);
			
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
			URL url = new URL(serverAddress + ":" + port + "/graph/delEdge?from=" + origin + "&to=" + destination);
			
			// Send message to that URL
			sendPostMessage(url);
			
		} catch (MalformedURLException e) {
			// TODO: Malformed URL
	        e.printStackTrace();
		}
	}

	public void updateSnapshot(){
		
		if(connecting){
			// Attempt to construct URL
			try { 
				// Send a GET message
				String result = sendGetMessage(new URL(serverAddress + ":" + port + "/graph/snapshot"));
				if(result != "NoConnection"){
				
				    // Convert JSON String to a Linked Hash Map 
				    LinkedHashMap<String, Float> nodeMap =  
				    		new Gson().fromJson(result, 
				    				new TypeToken<LinkedHashMap<String, Float>>(){}.getType());
				    
				    model.updateNodeMap(nodeMap);
				}
				
				// Also need to grab edges
				result = sendGetMessage(new URL(serverAddress + ":" + port + "/graph/topology"));
				if(result != "NoConnection"){
				
				    LinkedHashMap<String, String> edgeMap =  
				    		new Gson().fromJson(result, 
				    				new TypeToken<LinkedHashMap<String, String>>(){}.getType());
				    
				    model.updateEdgeMap(edgeMap);
				}
				
			    
			} catch (MalformedURLException e) {
				// TODO: Bad URL Exception
				e.printStackTrace();
			}
		}
	}

	public void updateStream(){
		// TODO: Build asynch networking section
		// This function is designed for streaming data
	}
	
	public void setConnecting(boolean connect){
		connecting = connect;
	}
}
