create table users (
    id bigint auto_increment primary key,
    email varchar(255) not null unique,
    password_hash varchar(255) not null,
    full_name varchar(255) not null,
    status varchar(255) not null default "ACTIVE",
    created_at DATETIME not null DEFAULT CURRENT_TIMESTAMP
);

create table roles (
    id bigint auto_increment primary key,
    name varchar(255) not null unique
);

create table user_roles (
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint foreign key (user_id) references users(id),
    constraint foreign key (role_id) references roles(id)
);

create table topics (
    id bigint primary key auto_increment,
    title varchar(255) not null,
    description TEXT,
    lecturer_id bigint not null,
    max_students int not null default 1,
    status varchar(20) not null default "OPEN",
    created_at DATETIME not null default "CURRENT_TIMESTAMP",
    constraint fk_topics_lecturer foreign key (lecturer_id) references users(id)
);

create table topics_registrations (
    id bigint primary key auto_increment,
    topic_id bigint not null,
    student_id bigint not null,
    status varchar(20) not null default "PENDING",
    reason TEXT,
    created_at DATETIME not null default CURRENT_TIMESTAMP,
    decided_at DATETIME null,
    decided_at bigint null,
    constraint fk_reg_topic foreign key (topic_id) references topics(id),
    constraint fk_reg_student foreign key (student_id) references users(id),
    constraint fk_reg_decider foreign key (decider_id) references users(id)
);

create table milestones (
    id bigint primary key auto_increment,
    topic_id bigint not null,
    title varchar(255) not null,
    due_date DATE not null,
    weight int not null default 10,
    order_no not null default 1,
    constraint fk_ms_topic foreign key (topic_id) references topics(id)
);

create table progress_updates (
    id bigint primary key auto_increment,
    milestones_id bigint not null,
    student_id bigint not null,
    percent int not null,
    content TEXT not null,
    created_at DATETIME not null default CURRENT_TIMESTAMP,
    updated_at DATETIME null,
    constraint fk_pu_ms foreign key (milestones_id) references milestones(id),
    constraint fk_pu_student foreign key (student_id) references users(id)
);

create table comments (
    id bigint primary key auto_increment,
    topic_id bigint not null,
    progress_updates_id bigint null,
    content TEXT not null,
    created_at DATETIME not null default CURRENT_TIMESTAMP,
    constraint fk_c_topic foreign key (topic_id) references topics(id),
    constraint fk_c_pu foreign key (progress_updates_id) references progress_updates(id)
    constraint fk_c_author foreign key (author_id) references users(id)
);

create table notifications (
    id bigint primary key auto_increment,
    user_id bigint not null,
    type varchar(255) not null,
    title varchar(255) not null,
    body TEXT,
    is_read BOOLEAN not null default false,
    created_at DATETIME not null default CURRENT_TIMESTAMP,
    constraint fk_n_user foreign key (user_id) references users(id)
);

insert into roles(name) values('ROLE_ADMIN'),('ROLE_LECTURER'),('ROLE_STUDENT');