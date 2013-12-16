package com.easyTeach.client.presenter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.UUID;

import com.easyTeach.client.network.EasyTeachClient;
import com.easyTeach.common.entity.Course;
import com.easyTeach.common.entity.Exercise;
import com.easyTeach.common.entity.ExerciseParameter;
import com.easyTeach.common.entity.Resource;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.entity.User;
import com.easyTeach.common.network.Action;
import com.easyTeach.common.network.Action.ActionType;
import com.easyTeach.common.network.Request;
import com.easyTeach.common.network.Session;

public class ManageExerciseInfoPresenter {
	private Exercise exercise;
	private ExerciseParameter parameters;
	private ResourceSet courses;
	private String currentlySelectedCourse;
	private String timeLimit;

	public ManageExerciseInfoPresenter() {
		this.exercise = new Exercise();
		this.parameters = new ExerciseParameter();
		this.courses = getCourses();
		this.timeLimit = new String();
	}

	public ManageExerciseInfoPresenter(Exercise exercise) {
		this.parameters = getParameters(exercise.getExerciseParameterNo());
		this.courses = getCourses();
		this.timeLimit = new String();
		this.exercise = exercise;
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

	private static ResourceSet getCourses() {
		Action fetchCourses = new Action(ActionType.READ, "all");
		Request getCourses = new Request(Session.getInstance(), fetchCourses,
				new Course());
		EasyTeachClient client = new EasyTeachClient(getCourses);
		client.run();
		return (ResourceSet) client.getResponse().getResponse();
	}

	private static String getCurrentAuthor() {
		Action fetchTeachers = new Action(ActionType.READ, "teachers");
		Request getCourses = new Request(Session.getInstance(), fetchTeachers,
				new User());
		EasyTeachClient client = new EasyTeachClient(getCourses);
		client.run();

		ResourceSet teachers = (ResourceSet) client.getResponse().getResponse();
		for (Resource r : teachers) {
			User u = (User) r;
			if (u.getEmail().equals(Session.getInstance().getUsername())) {
				return u.getUserNo();
			}
		}
		// This should never happen...
		return UUID.randomUUID().toString();
	}

	public boolean getIsTest() {
		//if(this.parameters != null) {
		//	return this.parameters.getIsTest();
		//}
		return false;
	}

	public boolean getIsLocked() {
		if(this.parameters != null) {
			return this.parameters.getIsTest();
		}
		return false;
	}

	public ArrayList<String> getAvailableCourses() {
		ArrayList<String> output = new ArrayList<>();
		for (Resource r : this.courses) {
			Course c = (Course) r;
			output.add(c.getCourseName());
		}
		return output;
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

	public void save(String name, boolean test) {
		save(name, test, false, null, null, null, null, getCurrentAuthor());
	}

	public void save(String name, boolean test, boolean locked,
			String accessBegin, String accessEnd, String time, String pwd,
			String author) {

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
		pOut.setIsTest(test); // Required
		pOut.setIsLocked(locked);

		if (accessEnd != null || accessBegin != null) {

			try {
				Date beginDate = parser.parse(accessBegin);
				Date closeDate = parser.parse(accessEnd);
				Timestamp begin = new Timestamp(beginDate.getTime());
				Timestamp end = new Timestamp(closeDate.getTime());
				pOut.setAccessBegin(begin);
				pOut.setAccessEnd(end);

			} catch (ParseException e) {
				e.printStackTrace();
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
		exOut.setAuthor(author);
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
