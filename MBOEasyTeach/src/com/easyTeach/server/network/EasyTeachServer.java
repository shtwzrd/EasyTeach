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
		response = new Response(ResponseStatus.SUCCESS);

		try {
			providerSocket = new ServerSocket(8111, 10);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void run() {
		try {
			System.out.println("Waiting for connection");
			connection = providerSocket.accept();
			System.out.println("Connection received from "
					+ connection.getInetAddress().getHostName());

			// Initialize the input and output streams in preparation for
			// communication with the client
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());

			// Acknowledge the client's connection.
			sendMessage(response);

			do {
				try {
					// Read the Request and log it
					request = (Request) in.readObject();
					System.out.println("[Request]: "
							+ request.getAction().getType().toString());

					// Throw out Requests with no credentials
					if (request.getSession() != null) {
						response = Authenticator.authenticateUser(request);
					} else {
						response = new Response(ResponseStatus.FAILURE);
					}

					if (request.getAction().getType() != ActionType.CLOSE) {
						sendMessage(response);
					}
				} catch (ClassNotFoundException classnot) {
					System.err.println("Data received in unknown format");
				}
			} while (request.getAction().getType() != ActionType.CLOSE);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			// Cleanup
			try {
				in.close();
				out.close();
				// providerSocket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	public void sendMessage(Response res) {
		try {
			out.writeObject(res);
			out.flush();
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