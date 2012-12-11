package nodeModel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkManager {
	String serverAddress;
	String port;
	
	public NetworkManager(){
		
	}
	
	public void setAddress(String serverAddress){
		this.serverAddress = serverAddress;
	}
	
	public void setPort(String port){
		this.port = port;
	}
	
	private void sendPostMessage(URL url){
		try {
	    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    	conn.setRequestMethod("POST");
	    } catch (Exception e) {
	    	System.out.println("Connection Failed: Post");
	    }
	}
	
	// TODO: Make an interface or something, this is shameful
	private void addNode(String name){
    	try {
			URL url = new URL(serverAddress + ":" + port + "/graph/add\\?node=" + name);
			sendPostMessage(url);
		} catch (MalformedURLException e) {
	    	System.out.println("Malformed URL");
		}
	}
	
	private void deleteNode(String name){
    	try {
			URL url = new URL(serverAddress + ":" + port + "/graph/del\\?node=" + name);
			sendPostMessage(url);
		} catch (MalformedURLException e) {
	    	System.out.println("Malformed URL");
		}
	}
	
	private void addEdge(String origin, String destination){
    	try {
			URL url = new URL(serverAddress + ":" + port + "/graph/add\\?from=" + origin + "&to=" + destination);
			sendPostMessage(url);
		} catch (MalformedURLException e) {
	    	System.out.println("Malformed URL");
		}
	}
	
	private void delEdge(String origin, String destination){
    	try {
			URL url = new URL(serverAddress + ":" + port + "/graph/del\\?from=" + origin + "&to=" + destination);
			sendPostMessage(url);
		} catch (MalformedURLException e) {
	    	System.out.println("Malformed URL");
		}
	}
	
	public void newNode(String name, String parents, String children, int activation){
		// TODO: Make sure node doesn't exist
		
	}
	
	public void modifyNode(String name, String parents, String children, int activation){
		// TODO: Make sure node doesn't exist
		
	}
	
	public nodeData requestNodes(){
		String result = "";
		String line;
		
	    try {
	    	// TODO: Figure out the correct address
	    	URL url = new URL(serverAddress + ":" + port + "/graph/snapshot");
	    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    	conn.setRequestMethod("GET");
	    	BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    	while ((line = rd.readLine()) != null) {
	    		result += line;
	    	}
	    	rd.close();
	    } catch (Exception e) {
	    	System.out.println("Connection Failed: Request Nodes");
	    	return null;
	    }
	    return generateNodeList(null);
	}

	private nodeData generateNodeList(String data) {
		// TODO: Parse data (OR NULL) into a nodeData if possible
		return null;
	}
}
