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
 * <li> 
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
		closeConnection = new Request(Session.getInstance(), close);
	}

	public void run() {
		try {
			//Attempt to open a socket with the given address and port
			requestSocket = new Socket("localhost", 8111);
			System.out.println("EasyTeachClient connected to localhost on port 8111...");

			//Set up the streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());

			try {
				//Get and print the initial response - SUCCESS if the server is listening 
				response = (Response) in.readObject();
				System.out.println("[Response]: " + response.getStatus().toString());

				//Attempt to send our request
				sendMessage(request);

				//Read the response and log it
				response = (Response) in.readObject();
				System.out.println("[Response]: " + response.getStatus().toString());

				//Tell the server we're done
				sendMessage(closeConnection);
			}
			catch(ClassNotFoundException classNot) {
				System.err.println("![Error]: Data received in an unknown format");
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
