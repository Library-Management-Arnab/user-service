INSERT INTO user_right (user_right_id, user_right_code, user_right_description, user_access_type) values ('GR00000001', 'A', 'System Administrators have full access to the system. They can view of modify any resource.', 'SYSTEM ADMINISTRATOR');
INSERT INTO user_right (user_right_id, user_right_code, user_right_description, user_access_type) values ('GR00000002', 'U', 'Basic Users have access to their own profile. They can update their own info only.', 'BASIC USER');

INSERT INTO USER_STATUS (status_code, status_description) values ('A', 'Active');
INSERT INTO USER_STATUS (status_code, status_description) values ('D', 'Deleted');
INSERT INTO USER_STATUS (status_code, status_description) values ('S', 'Suspended');
INSERT INTO USER_STATUS (status_code, status_description) values ('T', 'Temporarily Inactive');
INSERT INTO USER_STATUS (status_code, status_description) values ('I', 'Inactive');
