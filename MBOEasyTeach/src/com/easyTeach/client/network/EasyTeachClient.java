package com.easyTeach.client.network;

import java.io.*;
import java.net.*;

import com.easyTeach.common.network.*;
import com.easyTeach.common.network.Action.ActionType;

public class EasyTeachClient{
	private Socket requestSocket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Request closeConnection;
	private Response response;
	private Request request;

	public EasyTeachClient(Request request){
		
		Action close = new Action(ActionType.CLOSE);
		this.request = request;
		closeConnection = new Request(Session.getInstance().getUsername(),
				Session.getInstance().getPassword(), close);
	}

	public void run()
	{
		try {
			requestSocket = new Socket("localhost", 8111);
			System.out.println("EasyTeachClient connected to localhost on port 8111...");

			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());

				try {
					response = (Response) in.readObject();
					System.out.println("[Response]: " + response.getResponse());
					sendMessage(request);
					sendMessage(closeConnection);
				}
				catch(ClassNotFoundException classNot){
					System.err.println("![Error]: Data received in an unknown format");
				}
		}
		catch(UnknownHostException unknownHost){
			System.err.println("! [Error]: Host unknown");
		}
		catch(IOException ioException) {
			ioException.printStackTrace();
		}
		finally {
			try {
				in.close();
				out.close();
				requestSocket.close();
			}
			catch(IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	private void sendMessage(Request request) {
		try{
			out.writeObject(request);
			out.flush();
			System.out.println("[Client]: Issuing a " +
					request.getAction().getType().toString() + " request");
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
	public Response getResponse() {
		return this.response;
	}
}
