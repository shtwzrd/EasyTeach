package com.easyTeach.server.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.easyTeach.common.network.Action.ActionType;
import com.easyTeach.common.network.Request;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.server.domainLogic.Authenticator;

public class EasyTeachServer {
	private ServerSocket providerSocket;
	private Socket connection = null;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Response response;
	private Request request;

	public EasyTeachServer() {
		response = new Response(ResponseStatus.SUCCESS);
	}

	void run()
	{
		try {
			providerSocket = new ServerSocket(8111, 10);

			System.out.println("Waiting for connection");
			connection = providerSocket.accept();
			System.out.println("Connection received from " + connection.getInetAddress().getHostName());

			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());
			sendMessage(response);

			do {
				try {
					request = (Request) in.readObject();
					System.out.println("[Request]: " + request.getAction().getType().toString());
					switch(request.getAction().getAttribute()) {
					case "authenticate" : response = Authenticator.authenticateUser(
							request.getUser(), request.getPassword());
							break;
					}
					sendMessage(response);
				}
				catch(ClassNotFoundException classnot){
					System.err.println("Data received in unknown format");
				}
			} while (request.getAction().getType() != ActionType.CLOSE);
		}
		catch(IOException ioException) {
			ioException.printStackTrace();
		}
		finally { 
			try {
				in.close();
				out.close();
				providerSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}

	public void sendMessage(Response res) {
		try {
			out.writeObject(res);
			out.flush();
			System.out.println(res.getResponse());
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	public static void main(String args[]) {
		EasyTeachServer server = new EasyTeachServer();
		while(true){
			server.run();
		}
	}
}