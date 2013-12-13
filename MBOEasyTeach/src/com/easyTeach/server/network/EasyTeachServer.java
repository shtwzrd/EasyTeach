package com.easyTeach.server.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.easyTeach.client.network.EasyTeachClient;
import com.easyTeach.common.entity.Resource;
import com.easyTeach.common.network.Action.ActionType;
import com.easyTeach.common.network.Request;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.common.network.Session;
import com.easyTeach.server.domainLogic.Authenticator;
import com.easyTeach.server.ui.ServerUI;

/**
 * Application server, implemented with Sockets sending serialized Java objects
 * over TCP/IP.
 * 
 * <p>
 * The EasyTeachServer communicates with Clients by receiving Request objects
 * and returning Response objects. The server discards incoming Requests lacking
 * a Session, and sends all other Requests through to the Authenticator. The
 * Authenticator communicates with the business logic and returns a Response to
 * the Server, which the Server sends back to the Client, completing the
 * communication.
 * </p>
 * 
 * @author Brandon Lucas, Morten Faarkrog
 * @version 1.5
 * @date 13. December, 2013
 * 
 * @see Request
 * @see Response
 * @see Authenticator
 * @see Session
 * @see EasyTeachClient
 */

public class EasyTeachServer {
	private ServerSocket providerSocket;
	private Socket connection;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Response response;
	private Request request;
	private ServerUI serverUI;

	public EasyTeachServer() {
	    serverUI = new ServerUI();
	    
		this.response = new Response(ResponseStatus.SUCCESS);

		try {
			this.providerSocket = new ServerSocket(8111, 10);
		} catch (IOException e) {
		    serverUI.updateTextArea("[Error] : " + e.getMessage());
		}
	}

	private void run() {
		try {
		    serverUI.updateTextArea("[Waiting for connection]");
			this.connection = this.providerSocket.accept();
			serverUI.updateTextArea("[Connection Received] : " + this.connection.getInetAddress().getHostAddress());

			// Initialize the input and output streams in preparation for
			// communication with the client
			this.out = new ObjectOutputStream(this.connection.getOutputStream());
			this.out.flush();
			this.in = new ObjectInputStream(this.connection.getInputStream());

			// Acknowledge the client's connection.
			sendMessage(this.response);

			do {
				try {
					// Read the Request and log it
					this.request = (Request) this.in.readObject();
					serverUI.updateTextArea("[Request] : " + this.request.getAction().getType().toString());

					if (this.request.getAction().getType() != ActionType.CLOSE) {
						// Throw out Requests with no credentials
						if (this.request.getSession() != null) {
							this.response = Authenticator.authenticateUser(this.request);
						} else {
							this.response = new Response(ResponseStatus.FAILURE);
						}

						if (this.request.getAction().getType() != ActionType.CLOSE) {
							sendMessage(this.response);
						}
					} 

				} catch (ClassNotFoundException classnot) {
				    serverUI.updateTextArea("[Error] : " + "Data received in unknown format");
				}
			} while (this.request.getAction().getType() != ActionType.CLOSE);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			// Cleanup
			try {
				this.in.close();
				this.out.close();
			} catch (IOException ioException) {
			    serverUI.updateTextArea("[Error] : " + ioException.getMessage());
			}
		}
	}

	public void sendMessage(Response res) {
		try {
			this.out.writeObject(res);
			this.out.flush();
			Resource responseResource = res.getResponse();
			if (responseResource != null) {
			    serverUI.updateTextArea("[Response] : " + responseResource.getName());			    
			}
		} catch (IOException ioException) {
		    serverUI.updateTextArea("[Error] : " + ioException.getMessage());
		}
	}

	/**
	 * Server-side entry point.
	 */
	public static void main(String args[]) {
		EasyTeachServer server = new EasyTeachServer();
		while (true) {
			server.run();
		}
	}
}