package com.example.test;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

import java.net.MalformedURLException;

import org.json.JSONObject;

import android.os.AsyncTask;

    
class ConnectionTask extends AsyncTask<Void, Void, String> {

	private static final String ADDRESS_SERVER = "http://demo-project-alexcalinescu.c9.io:80";
	
	private static SocketIO socket;
	private static String response;
	
	public static SocketIO getSocket() {
		return socket;
	}
	
	public static String getResponse() {
		return response;
	}
	
	@Override
	protected String doInBackground(Void... params) {
		
		try {
			socket = new SocketIO(ADDRESS_SERVER);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
        socket.connect(new IOCallback() {

			@Override
			public void on(String arg0, IOAcknowledge arg1, Object... arg2) {
				System.out.println("Server triggered event '" + arg0 + "'");
				if ("server message".equals(arg0)) {
					response = arg0.toString();
				}
			}

			@Override
			public void onConnect() {
				System.out.println("Connection established");
				
			}

			@Override
			public void onDisconnect() {
				System.out.println("Connection terminated.");
			}

			@Override
			public void onError(SocketIOException arg0) {
                System.out.println("an Error occured");
                arg0.printStackTrace();
				
			}

			@Override
			public void onMessage(String arg0, IOAcknowledge arg1) {
				System.out.println("Received from server: " + arg0);
			}

			@Override
			public void onMessage(JSONObject arg0, IOAcknowledge arg1) {
				System.out.println("Received from server: " + arg0);
			}


        });
		return null;
	}
}
