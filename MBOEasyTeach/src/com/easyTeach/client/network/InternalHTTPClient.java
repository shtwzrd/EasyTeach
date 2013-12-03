package com.easyTeach.client.network;

import java.io.IOException;

import java.util.Scanner;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class InternalHTTPClient {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Input the EasyTeach IP address: ");
    String ip = input.next();
    input.close();

    try {
      new ClientResource(ip).get().write(System.out);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ResourceException e) {
      e.printStackTrace();
    }
  }
}
