package com.example.Utils;


import java.util.HashMap;
import java.util.Map;

import com.example.Dto.ResponseModel;
import com.google.gson.Gson;



public class CustomMessages {
	public static final int INTERNAL_SERVER_ERROR = 500;
	public final static int GET_DATA_SUCCESS = 200;
	public final static int ALREADY_EXIST = 300;
	public final static int GET_DATA_ERROR = 400;
	public final static int NO_DATA_FOUND = 201;
	public final static int UNAUTHORIZED_ERROR = 401;
	public final static int OTP_SEND_SUCCESSFULLY = 1;
	public final static int UNSUPPORTED_MEDIA_TYPE = 415;

	public final static int MENU_ADD_SUCCESSFULLY = 202;
	public final static int INPUT_FIELD_REQUIRED = 101;
	public final static int MENU_CODE_UNIQUE = 102;

	public final static int ADD_SUCCESSFULLY = 301;
	public final static int UPDATE_SUCCESSFULLY = 302;
	public final static int DELETE_SUCCESSFULLY = 303;
	public final static int STATUS_UPDATED_SUCCESSFULLY = 304;

	public final static int FARMER_REGISTRY_ADD_SUCCESSFULLY = 310;

	public final static String SUCCESS = "success";

	public final static String FAILED = "failed";

	public final static String METHOD_GET = "GET";
	public final static String METHOD_POST = "POST";
	public final static String METHOD_PUT = "PUT";

	public final static String LOGIN_SUCCESS = "User login successfully.";
	public final static String LOGOUT_SUCCESS = "User logout successfully.";
	public final static String UPDATE_PASSWORD = "Password updated successfully.";
	public final static String FORGOT_SUCCESS = "Reset password successfully Temporary password will be provided through SMS.";
	public final static String INVALID_PASSWORD = "Invalid username or password.";
	public final static String INVALID_PASSWORD2 = "Invalid password.";
	public final static String NOT_FOUND = " not found.";

	public final static String USER_NOT_FOUND = "User not found.";
	public final static String USER_ID_NOT_FOUND = "User Id not found.";

	public final static String ROLE_NOT_FOUND = "Invalid role.";
	public final static String TOKEN_NOT_FOUND="Token Not Found";
	public final static String TOKEN_EXPIRED="Token Expired";

	public final static String USER_NAME_EXIST = "User Name already exist.";

	public final static String USER_MOBILE_NAME_EXIST = "User Mobile Number already exist.";
	public final static String USER_EMAIL_ADDRESS_EXIST = "User Email Address already exist.";

	public final static String USER_ADDED_SUCCESS = "User added successfully.";
	public final static String USER_UPDATED_SUCCESS = "User updated successfully.";

	public final static String ROLE_NAME_REQURIED = "Role name requried.";

	public final static String MENU_ADD_SUCCESS = "Menu added successfully.";

	public final static String FILE_UPLOAD_SUCCESS = "File uploaded successfully.";

	public final static String EMPTY_EXCEL = "Excel file is empty.";

	public final static String STATUS_UPDATED_SUCCESS = "Status updated successfully.";

	public final static String FAILURE = "Internal server error";

	public final static String INVALID_INPUT = "Invalid input.";
	public final static String GET_RECORD = "Get records.";

	public final static String TYPE_NOT_SUPPORTED = "Unsupported media type.";

	public final static String RECORD_ADD = " added successfully.";
	public final static String RECORD_UPDATE = " updated successfully.";
	public final static String GENERATED_SUCCESS = " generated successfully.";
	public final static String RECORD_DELETE = " deleted successfully.";
	public final static String RECORD_ALREADY_EXIST = " already exist.";

	public final static String GEOMETRY_ADD_ERROR = "Error while adding the geometry.";
	public final static String PLOT_NOT_AVAILABLE = "Plot with given Id is not present in the system.";

	public final static String INVALID_OTP = "Invalid OTP.";
	public static final int LOGIN_SUCCESSFULLY = 200;

	public final static int GEOMETRY_ADDED_SUCCESSFULLY = 4;
	public final static int GEOMETRY_UPDATED_SUCCESSFULLY = 5;

	public final static int GEOMETRY_OUTSIDE_INDIA = 6;
	public final static int GEOMETRY_ENTITY_NOT_FOUND = 7;
	public final static int PROJECT_ENTITY_MAPPING_NOT_FOUND = 8;
	public final static int GEOMETRY_NOT_FOUND = 9;
	public final static int GEOMETRY_TYPE_NOT_FOUND = 10;
	public final static int GEOMETRY_DELETED_SUCCESSFULLY = 11;

	public final static int GEOMETRY_ENTITY_NAME_IS_MISSING = 12;
	public final static int PROJECT_NAME_IS_MISSING = 13;
	public final static int GEOMETRY_TYPE_IS_MISSING = 14;
	public final static int GEOMETRY_ID_IS_MISSING = 15;
	public static final int CONFLICT = 0;

	public static ResponseModel makeResponseModel(Object data, String message, int code, String status) {
		ResponseModel responseModel = new ResponseModel();
		responseModel.setData(data);
		responseModel.setMessage(message);
		responseModel.setCode(code);
		responseModel.setStatus(status);
		return responseModel;
	}

	public static String getMessageWithData(int value, Object data) {
		Map<String, Object> msgWithData = new HashMap<String, Object>();
		switch (value) {

		case OTP_SEND_SUCCESSFULLY:
			msgWithData = new HashMap<String, Object>();
			msgWithData.put("code", 200);
			msgWithData.put("status", "success");
			msgWithData.put("data", data);
			msgWithData.put("message", "OTP Send Successfully.");
			return new Gson().toJson(msgWithData);
		case GET_DATA_SUCCESS:
			msgWithData = new HashMap<String, Object>();
			msgWithData.put("code", 200);
			msgWithData.put("status", "success");
			msgWithData.put("data", data);
			msgWithData.put("method", "GET");
			msgWithData.put("message", "Ok.");
			return new Gson().toJson(msgWithData);
		case NO_DATA_FOUND:
			msgWithData.put("code", 200);
			msgWithData.put("status", "success");
			msgWithData.put("method", "GET");
			msgWithData.put("message", "No data Found For value : " + data);
			return new Gson().toJson(msgWithData);
		case MENU_ADD_SUCCESSFULLY:
			msgWithData.put("code", 200);
			msgWithData.put("status", "success");
			msgWithData.put("method", "POST");
			msgWithData.put("data", data);
			msgWithData.put("message", "Menu added successfully");
			return new Gson().toJson(msgWithData);
		case INPUT_FIELD_REQUIRED:
			msgWithData.put("code", 200);
			msgWithData.put("status", "success");
			msgWithData.put("method", "POST");
			msgWithData.put("data", "field " + data + "must required");
			msgWithData.put("message", "Menu added successfully");
			return new Gson().toJson(msgWithData);
		case MENU_CODE_UNIQUE:
			msgWithData.put("code", 200);
			msgWithData.put("status", "success");
			msgWithData.put("method", "POST");
			msgWithData.put("data", "Menu Code " + data + " already exists.");
			msgWithData.put("message", "Menu added successfully");
			return new Gson().toJson(msgWithData);

		case ADD_SUCCESSFULLY:
			msgWithData.put("code", 200);
			msgWithData.put("status", "success");
			msgWithData.put("method", "POST");
			msgWithData.put("data", data);
			msgWithData.put("message", "Added successfully");
			return new Gson().toJson(msgWithData);
		case UPDATE_SUCCESSFULLY:
			msgWithData.put("code", 200);
			msgWithData.put("status", "success");
			msgWithData.put("method", "POST");
			msgWithData.put("data", data);
			msgWithData.put("message", "Updated successfully");
			return new Gson().toJson(msgWithData);
		case DELETE_SUCCESSFULLY:
			msgWithData.put("code", 200);
			msgWithData.put("status", "success");
			msgWithData.put("method", "POST");
			msgWithData.put("data", data);
			msgWithData.put("message", "Deleted successfully");
			return new Gson().toJson(msgWithData);
		case STATUS_UPDATED_SUCCESSFULLY:
			msgWithData.put("code", 200);
			msgWithData.put("status", "success");
			msgWithData.put("method", "POST");
			msgWithData.put("data", data);
			msgWithData.put("message", "Status Updated successfully");
			return new Gson().toJson(msgWithData);
		default:
			msgWithData.put("code", 200);
			msgWithData.put("status", "success");
			msgWithData.put("data", data);
			return new Gson().toJson(msgWithData);
		}

	}

	/**
	 * @author Hardik.Siroya
	 * @param updateSuccessfully
	 * @param success2
	 * @param object
	 * @param string
	 * @param methodPost
	 * @return
	 */
	public static ResponseModel makeResponseModel(Integer responseCode, String status, Object data, String responseMessage,
			String method) {
		ResponseModel responseModel = new ResponseModel();
		responseModel.setData(data);
		responseModel.setMessage(responseMessage);
		responseModel.setMethod(method);
		responseModel.setCode(responseCode);
		responseModel.setStatus(status);
		return responseModel;
	}
}