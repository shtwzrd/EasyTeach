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
 * @author Brandon Lucas
 * @version 1.1
 * @date 3. December, 2013
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

	public EasyTeachServer() {
		this.response = new Response(ResponseStatus.SUCCESS);

		try {
			this.providerSocket = new ServerSocket(8111, 10);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void run() {
		try {
			System.out.println("Waiting for connection");
			this.connection = this.providerSocket.accept();
			System.out.println("Connection received from "
					+ this.connection.getInetAddress().getHostName());

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
					System.out.println("[Request]: "
							+ this.request.getAction().getType().toString());

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
					System.err.println("Data received in unknown format");
				}
			} while (this.request.getAction().getType() != ActionType.CLOSE);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			// Cleanup
			try {
				this.in.close();
				this.out.close();
				// providerSocket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	public void sendMessage(Response res) {
		try {
			this.out.writeObject(res);
			this.out.flush();
			System.out.println(res.getResponse());
		} catch (IOException ioException) {
			ioException.printStackTrace();
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