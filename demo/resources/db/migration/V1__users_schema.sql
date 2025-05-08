CREATE SCHEMA IF NOT EXISTS list;
CREATE TABLE list.t_users (
                                id SERIAL PRIMARY KEY,
                                c_name VARCHAR(50) NOT NULL CHECK (length(trim(c_name))>=2),
                                c_email VARCHAR(50),
                                c_age INTEGER
);