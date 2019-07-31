package com.lms.us.rest.transformer;

import com.lms.us.rest.model.db.UserStatus;

public final class UserStatusTransformer {
	private UserStatusTransformer() {
	}

	public static UserStatus statusDescriptionToUserStatus(String description) {
		return UserStatusConstants.getUserStatusFromDescription(description);
	}

	public static String userStatusToDescription(UserStatus userStatus) {
		return UserStatusConstants.getDescriptionFromUserStatus(userStatus);
	}

	private enum UserStatusConstants {
		SYSTEM_ADMINISTRATOR("A", "System Administrator"), BASIC_USER("U", "Basic User");

		private String description;
		private String statusCode;

		private UserStatusConstants(String statusCode, String description) {
			this.statusCode = statusCode;
			this.description = description;
		}

		static UserStatus getUserStatusFromDescription(String description) {
			UserStatus userStatus = new UserStatus();
			for (UserStatusConstants usc : UserStatusConstants.values()) {
				if (usc.description.equalsIgnoreCase(description)) {
					userStatus.setStatusCode(usc.statusCode);
					break;
				}
			}
			return userStatus;
		}

		static String getDescriptionFromUserStatus(UserStatus userStatus) {
			String description = null;
			for (UserStatusConstants usc : UserStatusConstants.values()) {
				if (usc.statusCode.equals(userStatus.getStatusCode())) {
					description = usc.description;
					break;
				}
			}
			return description;
		}
	}
}
