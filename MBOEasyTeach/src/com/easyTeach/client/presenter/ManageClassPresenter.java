package com.easyTeach.client.presenter;

import java.util.UUID;

import com.easyTeach.client.network.EasyTeachClient;
import com.easyTeach.common.entity.Class;
import com.easyTeach.common.entity.Resource;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.network.Action;
import com.easyTeach.common.network.Action.ActionType;
import com.easyTeach.common.network.Request;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Session;

public class ManageClassPresenter {
	private boolean canAdd;
	private boolean canRemove;

	private DisplayTableModel enrolledStudents;
	private DisplayTableModel allStudents;

	private String[] tableColumnHeaders = {
			"Email", "First Name", "Last Name", "Date added" };

	public ManageClassPresenter() {
		enrolledStudents = new DisplayTableModel();
		allStudents = new DisplayTableModel();

		allStudents.setRowCount(1);
		allStudents.setColumnIdentifiers(this.tableColumnHeaders);

		enrolledStudents.setRowCount(1);
		enrolledStudents.setColumnIdentifiers(this.tableColumnHeaders);
	}

	public boolean canAdd() {
		return this.canAdd;
	}

	public boolean canRemove() {
		return this.canRemove;
	}

	public DisplayTableModel getEnrolledStudentsModel() {
		return enrolledStudents;
	}

	public DisplayTableModel getAllStudentsModel() {
		return allStudents;
	}

	public void save(String className, int classYear) {
		//Partial implementation, please fix
		String classId = UUID.randomUUID().toString();
		Resource classToRequest = new Class(classId, classYear, className);
		Action toDo = new Action(ActionType.CREATE); 
		Request out = new Request(Session.getInstance(), toDo, classToRequest);	
		
		EasyTeachClient client = new EasyTeachClient(out);
		client.run();
		Response in = client.getResponse();
		System.out.println(in.getStatus() + ": " + in.getResponseMessage());
	}

	public void filter() {

	}

	public void discard() {

	}

	public void add() {

	}

	public void remove() {

	}

}
