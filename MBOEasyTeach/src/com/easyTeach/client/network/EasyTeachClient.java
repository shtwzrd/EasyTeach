package com.easyTeach.client.network;

import java.io.*;
import java.net.*;

import com.easyTeach.common.network.*;
import com.easyTeach.common.network.Action.ActionType;

public class EasyTeachClient{
	private Socket requestSocket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String user;
	private String password;
	private Request readSomething;
	private Request closeConnection;
	private Response response;

	public EasyTeachClient(){
		this.user = "Brandy";
		this.password = "<3";
		
		Action read = new Action(ActionType.READ);
		Action close = new Action(ActionType.CLOSE);
		
		QuestionResource whatever = new QuestionResource("Idon't", "care", 4);
		readSomething = new Request(user, password, read, whatever);
		closeConnection = new Request(user, password, close);
		
	}
	void run()
	{
		try {
			requestSocket = new Socket("localhost", 8111);
			System.out.println("EasyTeachClient connected to localhost on port 8111...");

			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());

			do {
				try {
					response = (Response) in.readObject();
					System.out.println("[Response]: " + response.getResponse());
					sendMessage(readSomething);
					sendMessage(closeConnection);
				}
				catch(ClassNotFoundException classNot){
					System.err.println("![Error]: Data received in an unknown format");
				}
			} while (!response.isClosing());
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

	public void sendMessage(Request request) {
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

	public static void main(String args[]) {
		EasyTeachClient client = new EasyTeachClient();
		client.run();
	}
}
