INSERT INTO test_volvo.permission(
            id, name, description)
    VALUES (1, 'adm', 'adm');
INSERT INTO test_volvo.permission(
            id, name, description)
    VALUES (2, 'others', 'others');
    
INSERT INTO test_volvo.department(
            id, name, description)
    VALUES (1, 'it', 'it');
INSERT INTO test_volvo.department(
            id, name, description)
    VALUES (2, 'hr', 'hr');

INSERT INTO test_volvo.app_user(
        id, id_department, name, description)
	VALUES (1, 1, 'will', 'willian');
INSERT INTO test_volvo.app_user(
        id, id_department, name, description)
	VALUES (2, 2, 'cris', 'cristiane');

INSERT INTO test_volvo.permissionuser(
            id, id_user, id_permission)
    VALUES (1, 1, 1);
INSERT INTO test_volvo.permissionuser(
            id, id_user, id_permission)
    VALUES (2, 1, 2);
INSERT INTO test_volvo.permissionuser(
            id, id_user, id_permission)
    VALUES (3, 2, 2);