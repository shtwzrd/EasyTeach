package com.easyTeach.client.presenter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.UUID;

import javax.swing.DefaultComboBoxModel;

import com.easyTeach.client.network.EasyTeachClient;
import com.easyTeach.common.entity.Course;
import com.easyTeach.common.entity.Exercise;
import com.easyTeach.common.entity.ExerciseParameter;
import com.easyTeach.common.entity.Resource;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.network.Action;
import com.easyTeach.common.network.Action.ActionType;
import com.easyTeach.common.network.Request;
import com.easyTeach.common.network.Session;

/**
 * Presenter handling the communication between the ManageExerciseInfoUI and the
 * EasyTeachClient/Server.
 * 
 * <p>
 * The Listeners in the UI class call the relevant methods in the Presenter in
 * order to update and retrieve information from the domain logic. This is an
 * implementation of the Model View Presenter pattern.
 * </p>
 * 
 * @author Brandon Lucas
 * @version 1.0
 * @date 15 December, 2013
 * 
 */
public class ManageExerciseInfoPresenter {
	private Exercise exercise;
	private ExerciseParameter parameters;
	private ArrayList<Course> courses;
	private DefaultComboBoxModel<String> courseSelection;
	private String currentlySelectedCourse;
	private String timeLimit;

	public ManageExerciseInfoPresenter() {
		this.exercise = new Exercise();
		this.parameters = new ExerciseParameter();
		this.courses = getCourses();
		this.courseSelection = new DefaultComboBoxModel<>();

		ArrayList<String> cs = getAvailableCourses();
		for (String c : cs) {
			this.courseSelection.addElement(c);
		}
		this.timeLimit = new String();
	}

	/**
	 * Constructor used when passing an existing Exercise to Edit
	 * 
	 * @param exercise
	 *            Exercise to edit.
	 */
	public ManageExerciseInfoPresenter(Exercise exercise) {
		this.parameters = getParameters(exercise.getExerciseParameterNo());
		this.courses = getCourses();

		this.courseSelection = new DefaultComboBoxModel<>();
		ArrayList<String> cs = getAvailableCourses();
		for (String c : cs) {
			this.courseSelection.addElement(c);
		}
		this.timeLimit = new String();
		this.exercise = exercise;
	}

	public DefaultComboBoxModel<String> getCourseSelectionModel() {
		return this.courseSelection;
	}

	private static ExerciseParameter getParameters(String parameterNo) {
		ExerciseParameter param = new ExerciseParameter();
		param.setExerciseParameterNo(parameterNo);

		Action fetchParam = new Action(ActionType.READ);
		Request getParam = new Request(Session.getInstance(), fetchParam, param);
		EasyTeachClient client = new EasyTeachClient(getParam);
		client.run();
		return (ExerciseParameter) client.getResponse().getResponse();
	}

	public String getStartDate() {
		if (this.parameters.getAccessBegin() != null) {
			return this.parameters.getAccessBegin().toString();
		}
		return "";
	}

	public String getEndDate() {
		if (this.parameters.getAccessEnd() != null) {
			return this.parameters.getAccessEnd().toString();
		}
		return "";
	}

	/**
	 * If the isTest flag is set to true, it becomes false. If it is false, it
	 * becomes true.
	 */
	public void toggleIsTest() {
		if (this.parameters.getIsTest()) {
			this.parameters.setIsTest(false);
		} else {
			this.parameters.setIsTest(true);
		}
	}

	public String getPassword() {
		if (this.exercise.getPassword() != null) {
			return this.exercise.getPassword();
		}
		return "";
	}

	private static ArrayList<Course> getCourses() {
		Action fetchCourses = new Action(ActionType.READ, "all");
		Request getCourses = new Request(Session.getInstance(), fetchCourses,
				new Course());
		EasyTeachClient client = new EasyTeachClient(getCourses);
		client.run();
		ResourceSet courses = (ResourceSet) client.getResponse().getResponse();
		ArrayList<Course> out = new ArrayList<>();
		for (Resource r : courses) {
			Course c = (Course) r;
			out.add(c);
		}
		return out;
	}

	private static String getCurrentAuthor() {
		// Problem - no way to get authorNo when authorized as Teacher.
		// authorNo is a foreign key and cannot be null.
		// Hardcoding admi5678@kea.dk's UserNo for now, for testing purposes.
		return "f9a20868-5361-491b-8192-e221d83f9163";
	}

	public boolean getIsTest() {
		if (this.parameters != null) {
			return this.parameters.getIsTest();
		}
		return false;
	}

	public boolean getIsLocked() {
		if (this.parameters != null) {
			return this.parameters.getIsTest();
		}
		return false;
	}

	public ArrayList<String> getAvailableCourses() {
		if (this.courses != null) {
			ArrayList<String> output = new ArrayList<>();
			for (Resource r : this.courses) {
				Course c = (Course) r;
				output.add(c.getCourseName());
			}

			return output;
		}
		return new ArrayList<>();
	}

	public void setCurrentlySelectedCourse(String courseName) {
		for (Resource r : this.courses) {
			Course c = (Course) r;
			if (c.getCourseName().equals(courseName)) {
				this.currentlySelectedCourse = c.getCourseNo();
				break;
			}
		}
	}

	public int getIndexOfCurrentlySelectedCourse() {
		for (int i = 0; i < this.courses.size(); i++) {
			if (this.courses.get(i).equals(this.currentlySelectedCourse)) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * Simplified procedure for saving, with only the single required parameter
	 * to be fulfilled.
	 * 
	 * @param name
	 *            The name to provide the Exercise
	 */
	public void save(String name) {
		save(name, false, null, null, null, null);
	}

	/**
	 * Procedure for saving an Exercise and its associated ExerciseParameter to
	 * the database
	 * 
	 * @param name
	 *            The name to provide the exercise
	 * @param locked
	 *            flag stating whether or not the exercise is locked
	 * @param accessBegin
	 *            date specifying the time at which the exercise becomes
	 *            available to take
	 * @param accessEnd
	 *            date specifying the time at which the exercise is no longer
	 *            available
	 * @param time
	 *            amount of time in minutes a student is alloted to complete the
	 *            exercise
	 * @param pwd
	 *            the password required to access the exercise, if any
	 */
	public void save(String name, boolean locked, String accessBegin,
			String accessEnd, String time, String pwd) {

		EasyTeachClient client;
		boolean parameterUpdate = false;
		boolean exerciseUpdate = false;
		String paramId;

		// Make the ExerciseParameter
		if (this.parameters.getExerciseParameterNo() != null) {
			paramId = this.parameters.getExerciseParameterNo();
			parameterUpdate = true;
		} else {
			paramId = UUID.randomUUID().toString();
		}
		SimpleDateFormat parser = new SimpleDateFormat("YYYY-MM-dd",
				Locale.ENGLISH);

		ExerciseParameter pOut = new ExerciseParameter();
		pOut.setExerciseParameterNo(paramId); // Required
		pOut.setIsTest(this.parameters.getIsTest()); // Required
		pOut.setIsLocked(locked);

		if (accessEnd != "" || accessBegin != "") {

			try {
				Date beginDate = parser.parse(accessBegin);
				Date closeDate = parser.parse(accessEnd);
				Timestamp begin = new Timestamp(beginDate.getTime());
				Timestamp end = new Timestamp(closeDate.getTime());
				pOut.setAccessBegin(begin);
				pOut.setAccessEnd(end);

			} catch (ParseException e) {
				//
			}

			if (time != null) {
				pOut.setTimeLimit(Integer.parseInt(time));
			}
		}

		Action mkParam;
		if (parameterUpdate) {
			mkParam = new Action(ActionType.UPDATE);
		} else {
			mkParam = new Action(ActionType.CREATE);
		}

		Request makeParam = new Request(Session.getInstance(), mkParam, pOut);
		client = new EasyTeachClient(makeParam);
		client.run();

		// Make Exercise
		String exerciseId;
		if (this.exercise.getExerciseNo() != null) {
			exerciseId = this.exercise.getExerciseNo();
			exerciseUpdate = true;
		} else {
			exerciseId = UUID.randomUUID().toString();
		}

		GregorianCalendar now = new GregorianCalendar();
		Exercise exOut = new Exercise();
		exOut.setExerciseNo(exerciseId);
		exOut.setExerciseParameterNo(paramId);
		exOut.setAuthor(getCurrentAuthor());
		exOut.setPassword(pwd);
		exOut.setCourseNo(this.currentlySelectedCourse);
		exOut.setExerciseName(name);
		exOut.setDateAdded(new Timestamp(now.getTimeInMillis()));

		Action mkExercise;
		if (exerciseUpdate) {
			mkExercise = new Action(ActionType.UPDATE);
		} else {
			mkExercise = new Action(ActionType.CREATE);
		}

		Request sendExercise = new Request(Session.getInstance(), mkExercise,
				exOut);
		client = new EasyTeachClient(sendExercise);
		client.run();
	}

	public String getExerciseName() {
		if (this.exercise.getExerciseName() != null) {
			return this.exercise.getExerciseName();
		}
		return "";
	}

	public String getTimeLimit() {
		if (this.parameters.getTimeLimit() > -1) {
			return Integer.toString(this.parameters.getTimeLimit());
		}
		return this.timeLimit;
	}

}
