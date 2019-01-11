INSERT INTO public.oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('TEST_CLIENT', null, '$2a$10$0iwX7NpsNzhqvtf14z1DxOvsWQcDSJjRxsDKI6V93bkQ5TRewqh.C', 'create,read,update,delete', 'password,refresh_token', null, null, 43200, 2592000, null, null);

INSERT INTO public.tb_user (emplyr_id, password, emplyr_nm, regist_dt, updde_dt) VALUES ('misoboy', '$2a$10$T83FC4EI5ZMlk39wBfr5NOoujvY4wAnnWB0eutg0on4KM1h/37JvW', '홍길동', '2019-01-11 01:34:19.490000', null);

INSERT INTO public.tb_author (emplyr_id, author, regist_dt) VALUES ('misoboy', 'ROLE_USER', '2019-01-11 01:35:00.981000');
INSERT INTO public.tb_author (emplyr_id, author, regist_dt) VALUES ('misoboy', 'ROLE_ADMIN', '2019-01-11 01:37:48.592000');