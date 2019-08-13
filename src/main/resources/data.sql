INSERT INTO user_role (user_role_id, user_role_code, user_role_description, user_role_name) values ('AA', 'FULL_ADMIN', 'System Administrators have full access to the system. They can view of modify any resource.', 'SYSTEM ADMINISTRATOR');
INSERT INTO user_role (user_role_id, user_role_code, user_role_description, user_role_name) values ('LA', 'LIB_ADMIN', 'Library Administrator', 'LIBRARY ADMINISTRATOR');
INSERT INTO user_role (user_role_id, user_role_code, user_role_description, user_role_name) values ('LR', 'LIB_READ_ONLY', 'Read only access to the library resources', 'LIBRARY BASIC USER');
INSERT INTO user_role (user_role_id, user_role_code, user_role_description, user_role_name) values ('BA', 'BOOK_ADMIN', 'Administrator of Book service. They can view and modify any book resource but can only modify their own inforrmation', 'BOOK ADMINISTRATOR');

INSERT INTO USER_STATUS (status_code, status_description) values ('A', 'Active');
INSERT INTO USER_STATUS (status_code, status_description) values ('D', 'Deleted');
INSERT INTO USER_STATUS (status_code, status_description) values ('S', 'Suspended');
INSERT INTO USER_STATUS (status_code, status_description) values ('T', 'Temporarily Inactive');
INSERT INTO USER_STATUS (status_code, status_description) values ('I', 'Inactive');


INSERT INTO auth_scope (scope_id, scope_name) VALUES ('SCO0000001', 'READ_ONLY');
INSERT INTO auth_scope (scope_id, scope_name) VALUES ('SCO0000002', 'WRITE_ONLY');
INSERT INTO auth_scope (scope_id, scope_name) VALUES ('SCO0000003', 'READ_AND_WRITE');
