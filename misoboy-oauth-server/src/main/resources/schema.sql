create table if not exists oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

create table if not exists oauth_client_token (
  token_id VARCHAR(256),
  token bytea,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

create table if not exists oauth_access_token (
  token_id VARCHAR(256),
  token bytea,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication bytea,
  refresh_token VARCHAR(256)
);

create table if not exists oauth_refresh_token (
  token_id VARCHAR(256),
  token bytea,
  authentication bytea
);

create table if not exists oauth_code (
  code VARCHAR(256), authentication bytea
);

create table if not exists oauth_approvals (
  userId VARCHAR(256),
  clientId VARCHAR(256),
  scope VARCHAR(256),
  status VARCHAR(10),
  expiresAt TIMESTAMP,
  lastModifiedAt TIMESTAMP
);

-- 사용자정보
create table if not exists tb_user
(
	"emplyr_id" varchar(50)  not null, -- 사용자id
	"password"  varchar(250) not null, -- 비밀번호
	"emplyr_nm" varchar(20)  not null, -- 사용자이름
	"regist_dt" timestamp    not null, -- 등록일시
	"updde_dt"  timestamp    null      -- 수정일시
);

-- 사용자정보 기본키
create unique index if not exists "pk_tb_user"
	on "public"."tb_user"
	( -- 사용자정보
		"emplyr_id" asc -- 사용자id
	)
;
-- 사용자정보
alter table "public"."tb_user"
	add constraint "pk_tb_user"
		 -- 사용자정보 기본키
	primary key
	using index "pk_tb_user";

-- 사용자권한
create table if not exists tb_author
(
	"emplyr_id" varchar(50) not null, -- 사용자id
	"author"    varchar(20) not null, -- 권한
	"regist_dt" timestamp   not null  -- 등록일시
);

-- 사용자권한 기본키
create unique index if not exists "pk_tb_author"
	on "public"."tb_author"
	( -- 사용자권한
		"emplyr_id" asc, -- 사용자id
		"author" asc -- 권한
	)
;
-- 사용자권한
alter table "public"."tb_author"
	add constraint "pk_tb_author"
		 -- 사용자권한 기본키
	primary key
	using index "pk_tb_author";

-- 사용자권한
alter table "public"."tb_author"
	add constraint "fk_tb_user_to_tb_author"
	 -- 사용자정보 -> 사용자권한
		foreign key (
			"emplyr_id" -- 사용자id
		)
		references "public"."tb_user" ( -- 사용자정보
			"emplyr_id" -- 사용자id
		);