package com.easyTeach.server.domainLogic;

import com.easyTeach.common.entity.Answer;
import com.easyTeach.common.entity.Class;
import com.easyTeach.common.entity.Course;
import com.easyTeach.common.entity.Exercise;
import com.easyTeach.common.entity.ExerciseParameter;
import com.easyTeach.common.entity.Question;
import com.easyTeach.common.entity.ExerciseQuestionRelation;
import com.easyTeach.common.entity.UserTestResult;
import com.easyTeach.common.entity.Resource;
import com.easyTeach.common.entity.ResourceSet;
import com.easyTeach.common.entity.QuestionTagRelation;
import com.easyTeach.common.entity.Tag;
import com.easyTeach.common.entity.User;
import com.easyTeach.common.network.Action;
import com.easyTeach.common.network.Request;
import com.easyTeach.common.network.Response;
import com.easyTeach.common.network.Response.ResponseStatus;
import com.easyTeach.server.domainLogic.RoleResource.Role;

/**
 * Class responsible for understanding requests from the client and routing it
 * to the relevant domain logic. For example, if a user wanted to insert a new
 * class it would be routed to ClassRules' addClass method.
 * 
 * <p>
 * The class works as a mediator between different parts of the system.
 * </p>
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 12. December, 2013
 */
public final class Router {

    private Router() {
    }

    public static Response getResponse(Request toRoute) {
        Action action = toRoute.getAction();
        Resource resource = toRoute.getResource();
        Role role = toRoute.getRole();

        switch (role.toString()) {

        // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
        // ADMIN

        case "ADMIN":
            switch (resource.getName()) {
            case "Class":
                switch (action.getType().toString()) {
                case "CREATE":
                    return ClassRules.addClass((Class) resource);
                case "READ":
                    switch (action.getAttribute()) {
                    case "all":
                        return ClassRules.getClasses();
                    case "students":
                        return UserRules.getStudents((Class) resource);
                    default:
                        return ClassRules.getClass((Class) resource);
                    }
                case "UPDATE":
                    return ClassRules.updateClass((Class) resource);
                case "DELETE":
                    return ClassRules.deleteClass((Class) resource);
                }

            case "ClassUserRelation":
                switch (action.getType().toString()) {
                case "CREATE":
                    return ClassUserRelationRules
                            .addRelations((ResourceSet) resource);
                case "UPDATE":
                    switch (action.getAttribute()) {
                    case "class":
                        return ClassUserRelationRules
                                .updateRelationsByClass((ResourceSet) resource);
                    case "user":
                        return ClassUserRelationRules
                                .updateRelationsByUser((ResourceSet) resource);
                    }
                }

            case "ClassCourseRelation":
                switch (action.getType().toString()) {
                case "CREATE":
                    return ClassCourseRelationRules
                            .addRelations((ResourceSet) resource);
                case "UPDATE":
                    switch (action.getAttribute()) {
                    case "class":
                        return ClassCourseRelationRules
                                .updateRelationByClass((ResourceSet) resource);
                    case "course":
                        return ClassCourseRelationRules
                                .updateRelationByCourse((ResourceSet) resource);
                    }
                }

            case "Course":
                switch (action.getType().toString()) {
                case "CREATE":
                    return CourseRules.addCourse((Course) resource);
                case "READ":
                    switch (action.getAttribute()) {
                    case "all":
                        return CourseRules.getCourses();
                    case "classes":
                        return ClassRules
                                .getClassesWithCourseNo((Course) resource);
                    default:
                        return CourseRules.getCourse((Course) resource);
                    }
                case "UPDATE":
                    return CourseRules.updateCourse((Course) resource);
                case "DELETE":
                    return CourseRules.deleteCourse((Course) resource);
                }

            case "User":
                switch (action.getType().toString()) {
                case "CREATE":
                    return UserRules.addUser((User) resource);
                case "READ":
                    switch (action.getAttribute()) {
                    case "students":
                        return UserRules.getStudents();
                    case "teachers":
                        return UserRules.getTeachers();
                    case "admins":
                        return UserRules.getAdmins();
                    case "classes":
                        return UserRules.getClasses((User) resource);
                    default:
                        return UserRules.getUser((User) resource);
                    }
<<<<<<< HEAD
                case "UPDATE":
                    return UserRules.updateUser((User) resource);
                case "DELETE":
                    return UserRules.deleteUser((User) resource);
                }
            }

            // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
            // -- -- -- --
            // TEACHER

        case "TEACHER":
            switch (resource.getName()) {
            case "Answer":
=======
			    }
			    
			case "ClassUserRelationSet":
>>>>>>> 23ec6d36580bb361a5d7cca754103a0b858a4b2e
                switch (action.getType().toString()) {
                case "CREATE":
                    return AnswerRules.addAnswer((Answer) resource);
                case "UPDATE":
                    return AnswerRules.updateAnswer((Answer) resource);
                case "DELETE":
                    return AnswerRules.deleteAnswer((Answer) resource);
                }

            case "Class":
                switch (action.getType().toString()) {
                case "CREATE":
                    return ClassRules.addClass((Class) resource);
                case "READ":
                    switch (action.getAttribute()) {
                    case "all":
                        return ClassRules.getClasses();
                    case "students":
                        return UserRules.getStudents((Class) resource);
                    default:
                        return ClassRules.getClass((Class) resource);
                    }
                }

            case "Course":
                switch (action.getType().toString()) {
                case "READ":
                    switch (action.getAttribute()) {
                    case "all":
                        return CourseRules.getCourses();
                    case "exercises":
                        return ExerciseRules
                                .getExercisesWithCourseNo((Course) resource);
                    default:
                        return CourseRules.getCourse((Course) resource);
                    }
                }

            case "Exercise":
                switch (action.getType().toString()) {
                case "CREATE":
                    return ExerciseRules.addExercise((Exercise) resource);
                case "READ":
                    switch (action.getAttribute()) {
                    case "all":
                        return ExerciseRules.getExercises();
                    default:
                        return ExerciseRules.getExercise((Exercise) resource);
                    }
                case "UPDATE":
                    return ExerciseRules.updateExercise((Exercise) resource);
                case "DELETE":
                    return ExerciseRules.deleteExercise((Exercise) resource);
                }

            case "ExerciseParameter":
                switch (action.getType().toString()) {
                case "CREATE":
                    return ExerciseParameterRules
                            .addExerciseParameter((ExerciseParameter) resource);
                case "READ":
                    switch (action.getAttribute()) {
                    case "all":
                        return ExerciseParameterRules.getExerciseParameters();
                    default:
                        return ExerciseParameterRules
                                .getExerciseParameter((ExerciseParameter) resource);
                    }
                case "UPDATE":
                    return ExerciseParameterRules
                            .updateExerciseParameter((ExerciseParameter) resource);
                case "DELETE":
                    return ExerciseParameterRules
                            .deleteExerciseParameter((ExerciseParameter) resource);
                }

            case "ExerciseQuestionRelation":
                switch (action.getType().toString()) {
                case "CREATE":
                    return ExerciseQuestionRelationRules
                            .addExerciseQuestionRelation((ExerciseQuestionRelation) resource);
                case "DELETE":
                    return ExerciseQuestionRelationRules
                            .deleteExerciseQuestionRelation((ExerciseQuestionRelation) resource);
                }

            case "Question":
                switch (action.getType().toString()) {
                case "CREATE":
                    return QuestionRules.addQuestion((Question) resource);
                case "READ":
                    switch (action.getAttribute()) {
                    case "all":
                        return QuestionRules.getQuestions();
                    case "answer":
                        return AnswerRules.getAnswers((Question) resource);
                    case "correct":
                        return AnswerRules
                                .getCorrectAnswer((Question) resource);
                    default:
                        return QuestionRules.getQuestion((Question) resource);
                    }
                case "UPDATE":
                    return QuestionRules.updateQuestion((Question) resource);
                case "DELETE":
                    return QuestionRules.deleteQuestion((Question) resource);
                }

            case "QuestionTagRelation":
                switch (action.getType().toString()) {
                case "CREATE":
                    return QuestionTagRelationRules
                            .addQuestionTagRelation((QuestionTagRelation) resource);
                case "UPDATE":
                    return QuestionTagRelationRules
                            .updateQuestionTagRelationByQuestionNo((ResourceSet) resource);
                case "DELETE":
                    return QuestionTagRelationRules
                            .deleteQuestionTagRelation((QuestionTagRelation) resource);
                }

            case "Tag":
                switch (action.getType().toString()) {
                case "CREATE":
                    return TagRules.addTag((Tag) resource);
                case "READ":
                    switch (action.getAttribute()) {
                    case "all":
                        return TagRules.getAllTags();
                    case "exercises":
                        return ExerciseRules
                                .getExercisesWithTag((Tag) resource);
                    case "questions":
                        return QuestionRules
                                .getQuestionRowsWithTagNo((Tag) resource);
                    default:
                        return TagRules.getTag((Tag) resource);
                    }
                case "UPDATE":
                    return TagRules.updateTag((Tag) resource);
                case "DELETE":
                    return TagRules.deleteTag((Tag) resource);
                }

            case "User":
                switch (action.getType().toString()) {
                case "READ":
                    switch (action.getAttribute()) {
                    case "students":
                        return UserRules.getStudents();
                    }
                }
            }

            // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

        case "STUDENT":
            switch (resource.getName()) {
            case "Course":
                switch (action.getType().toString()) {
                case "READ":
                    return ExerciseRules
                            .getExercisesWithCourseNo((Course) resource);
                }

            case "Exercise":
                switch (action.getType().toString()) {
                case "READ":
                    return ExerciseRules.getExercises();
                }

            case "Question":
                switch (action.getType().toString()) {
                case "READ":
                    return QuestionRules.getQuestion((Question) resource);
                }

            case "Tag":
                switch (action.getType().toString()) {
                case "READ":
                    switch (action.getAttribute()) {
                    case "exercises":
                        return ExerciseRules
                                .getExercisesWithTag((Tag) resource);
                    }
                }

            case "User":
                switch (action.getType().toString()) {
                case "READ":
                    return CourseRules.getCoursesWithUserNo((User) resource);
                }

            case "UserTestResult":
                switch (action.getType().toString()) {
                case "CREATE":
                    return UserTestResultRules
                            .addUserTestResult((UserTestResult) resource);
                }

            }

            // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

        default:
            return new Response(ResponseStatus.FAILURE,
                    "Unknown ActionType or Action attribute");
        }
    }

}
