package com.easyTeach.client.network;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.easyTeach.common.network.Action;
import com.easyTeach.common.network.Action.ActionType;
import com.easyTeach.common.network.Request;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Session;

/** A client used for connecting to the server and issuing a single request.
 * <br> 
 * <p>
 * The EasyTeachClient is used by the various Presenter classes to query
 * data from the server and alert the server of any changes in Resource state.
 * Each time a Request must be issued, a Client must be created and queried for
 * the Response. 
 * </p>
 * 
 * @see EasyTeachServer
 * @see Request
 * @see Response 
 * @author Brandon Lucas
 * @version 1.1
 * @date 4 December, 2013
 */

public class EasyTeachClient{
	private Socket requestSocket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Request closeConnection;
	private Response response;
	private Request request;

	public EasyTeachClient(Request request) {
		//Build the Request for closing the connection
		Action close = new Action(ActionType.CLOSE);
		this.request = request;
		this.closeConnection = new Request(Session.getInstance(), close);
	}

	public void run() {
		try {
			//Attempt to open a socket with the given address and port
			this.requestSocket = new Socket("localhost", 8111);
			System.out.println("EasyTeachClient connected to localhost on port 8111...");

			//Set up the streams
			this.out = new ObjectOutputStream(this.requestSocket.getOutputStream());
			this.out.flush();
			this.in = new ObjectInputStream(this.requestSocket.getInputStream());

			try {
				//Get and print the initial response - SUCCESS if the server is listening 
				this.response = (Response) this.in.readObject();
				System.out.println("[Response]: " + this.response.getStatus().toString());

				//Attempt to send our request
				sendMessage(this.request);

				//Read the response and log it
				this.response = (Response) this.in.readObject();
				System.out.println("[Response]: " + this.response.getStatus().toString());

				//Tell the server we're done
				sendMessage(this.closeConnection);
			}
			catch(ClassNotFoundException classNot) {
				System.err.println("! [Error]: Data received in an unknown format");
			}
		}
		catch(UnknownHostException unknownHost) {
			System.err.println("! [Error]: Host unknown");
		}
		catch(IOException ioException) {
			ioException.printStackTrace();
		}
		finally {
			try {
				//Cleanup
				this.in.close();
				this.out.close();
				this.requestSocket.close();
			}
			catch(IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	private void sendMessage(Request requestToServer) {
		try {
			this.out.writeObject(requestToServer);
			this.out.flush();
			System.out.println("[Client]: Issuing a " +
					requestToServer.getAction().getType().toString() + " request");
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}

	public Response getResponse() {
		return this.response;
	}
}