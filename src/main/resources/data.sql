INSERT INTO user_right (user_right_id, user_right_code, user_right_description, user_access_type) values ('AA', 'FULL_ADMIN', 'System Administrators have full access to the system. They can view of modify any resource.', 'SYSTEM ADMINISTRATOR');
INSERT INTO user_right (user_right_id, user_right_code, user_right_description, user_access_type) values ('LA', 'LIB_ADMIN', 'Library Administrator', 'LIBRARY ADMINISTRATOR');
INSERT INTO user_right (user_right_id, user_right_code, user_right_description, user_access_type) values ('LR', 'LIB_READ_ONLY', 'Read only access to the library resources', 'LIBRARY BASIC USER');
INSERT INTO user_right (user_right_id, user_right_code, user_right_description, user_access_type) values ('BA', 'BOOK_ADMIN', 'Administrator of Book service. They can view and modify any book resource but can only modify their own inforrmation', 'BOOK ADMINISTRATOR');

INSERT INTO USER_STATUS (status_code, status_description) values ('A', 'Active');
INSERT INTO USER_STATUS (status_code, status_description) values ('D', 'Deleted');
INSERT INTO USER_STATUS (status_code, status_description) values ('S', 'Suspended');
INSERT INTO USER_STATUS (status_code, status_description) values ('T', 'Temporarily Inactive');
INSERT INTO USER_STATUS (status_code, status_description) values ('I', 'Inactive');
