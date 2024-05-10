-- -- insert data into permission table
-- INSERT INTO app.tbl_permissions (id, created_by, created_date, modified_by, modified_date, name, permission_key, status)
-- VALUES (uuid_generate_v4(), 'system', now(), 'system', now(), 'Permission 1', 'PERMISSION_1', 'ACTIVE');
-- -- insert data into role table
-- INSERT INTO app.tbl_roles (id, created_by, created_date, modified_by, modified_date, name, role_key, status)
-- VALUES (uuid_generate_v4(), 'system', now(), 'system', now(), 'Admin', 'ADMIN','ACTIVE');
-- INSERT INTO app.tbl_roles (id, created_by, created_date, modified_by, modified_date, name, role_key, status)
-- VALUES (uuid_generate_v4(), 'system', now(), 'system', now(), 'Role 1','ROLE_1', 'ACTIVE');
-- -- insert data into user_sys table
-- INSERT INTO app.tbl_users(id, created_by, created_date, modified_by, modified_date, status, email,
--                          firstname, lastname, password, username)
-- VALUES (uuid_generate_v4(), 'system', now(), 'system', now(), 'ACTIVE', 'admin@admin.com',
--         'admin', 'admin', '$2a$12$0TEL3hXlgoJ7KIUh2JM2ju5zfgnGmpzGbgHv5Pl9rOrJZFGhNg9Gu', 'admin')

-- INSERT INTO app.tbl_courses (id, created_by, created_date, modified_by, modified_date,
--                             status, description, duration, end_date, name, start_date, tuition_fee)
-- VALUES (uuid_generate_v4(), 'system', now(), 'system', now(), 'ACTIVE', 'description',
--         generate_random_duration(), now(), 'course', now(), 1000);

INSERT INTO app.tbl_users(id, created_by, created_date, modified_by, modified_date, status, dob,
                         email, password, username)
VALUES (uuid_generate_v4(), 'system', now(), 'system', now(), 'ACTIVE', now(), 'user3@admin.com',
        '$2a$12$0TEL3hXlgoJ7KIUh2JM2ju5zfgnGmpzGbgHv5Pl9rOrJZFGhNg9Gu', 'user3')