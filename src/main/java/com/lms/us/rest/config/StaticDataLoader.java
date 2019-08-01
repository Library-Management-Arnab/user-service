package com.lms.us.rest.config;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lms.svc.common.config.BaseDataLoader;
import com.lms.us.rest.model.db.UserRight;
import com.lms.us.rest.model.db.UserStatus;
import com.lms.us.rest.repository.UserRightRepository;
import com.lms.us.rest.repository.UserStatusRepository;

@Component
public class StaticDataLoader extends BaseDataLoader {
	private static final Logger LOG = LoggerFactory.getLogger(StaticDataLoader.class);

	private UserRightRepository userRightRepository;
	private UserStatusRepository userStatusRepository;

	public StaticDataLoader(UserRightRepository userRightRepository, UserStatusRepository userStatusRepository) {
		this.userRightRepository = userRightRepository;
		this.userStatusRepository = userStatusRepository;
	}

	private List<UserRight> userRights;
	private List<String> allRights;
	private List<UserStatus> userStatuses;
	private List<String> allStatuses;

	@PostConstruct
	public void populateStaticData() {
		LOG.info("Populating static data");
		userRights = userRightRepository.findAll();
		userStatuses = userStatusRepository.findAll();

		allRights = userRights.stream().map(UserRight::getRight).collect(Collectors.toList());

		allStatuses = userStatuses.stream().map(UserStatus::getStatusDescription).collect(Collectors.toList());
	}

	public UserRight getUserRightFromAccessType(String accessType) {
		Predicate<UserRight> userRightPredicate = right -> right.getRight().equalsIgnoreCase(accessType);
		return returnOrThrow(userRights, userRightPredicate, accessType, allRights, "UserRight");
	}

	public String getAccessTypeFromUserRight(UserRight userRight) {
		return getClientString(userRights, userRight, UserRight::getRight);
	}

	public UserStatus getUserStatusFromDescription(String description) {
		Predicate<UserStatus> userStatusPredicate = userStatus -> userStatus.getStatusDescription()
				.equalsIgnoreCase(description);
		return returnOrThrow(userStatuses, userStatusPredicate, description, allStatuses, "UserStatus");
	}

	public String getDescriptionFromUserStatus(UserStatus userStatus) {
		return getClientString(userStatuses, userStatus, UserStatus::getStatusDescription);
	}

}
