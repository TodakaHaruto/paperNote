CREATE TABLE users (
	user_id VARCHAR(50) PRIMARY KEY,
	password VARCHAR(30) NOT NULL,
	user_name VARCHAR(50) NOT NULL,
	birthday DATE NOT NULL,
	gender INT NOT NULL
)