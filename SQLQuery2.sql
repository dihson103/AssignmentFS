create database EmployeeAssignment

CREATE TABLE Employee(
	employee_id		INT				PRIMARY KEY IDENTITY (1, 1),
	first_name		VARCHAR(255)	NOT NULL,
	last_name		VARCHAR(255)	NOT NULL,
	gender			INT				NOT NULL,
	date_of_birth	DATE			NOT NULL,
	phone			VARCHAR(20)		NOT NULL,
	[address]		VARCHAR(255),
	department_name VARCHAR(255)	NOT NULL,
	remark			VARCHAR(255)
)

CREATE TABLE Account (
	account_id	INT				PRIMARY KEY IDENTITY(1, 1),
	account		VARCHAR(255)	UNIQUE NOT NULL,
	email		VARCHAR(255)	UNIQUE NOT NULL,
	[password]	VARCHAR(255)	NOT NULL,
	[status]	INT,
	employee_id INT NOT NULL FOREIGN KEY REFERENCES Employee(employee_id)
)

INSERT INTO Employee (first_name, last_name, gender, date_of_birth, phone, [address], department_name, remark)
	VALUES ('Nguyen', 'Dinh Son', 1, '2001-03-10', '0976099351', 'ha noi', 'Fsoft Academy', null)
INSERT INTO Employee (first_name, last_name, gender, date_of_birth, phone, [address], department_name, remark)
	VALUES ('Chu', 'Thuy Linh', 0, '2002-01-20', '0976099431', 'ha noi', 'Fsoft Marketing', null)
INSERT INTO Employee (first_name, last_name, gender, date_of_birth, phone, [address], department_name, remark)
	VALUES ('Nguyen', 'Van Son', 1, '2001-03-10', '0955099351', 'ha noi', 'Fsoft Dev', null)

INSERT INTO Account (account, email, [password], [status], employee_id)
	VALUES ('dinhson103', 'dinhson1032001@gmail.com', '12345', 1, 1)
INSERT INTO Account (account, email, [password], [status], employee_id)
	VALUES ('chuthuylinh', 'chuthuylinh@gmail.com', '12345', 1, 2)
INSERT INTO Account (account, email, [password], [status], employee_id)
	VALUES ('vankien', 'vankien@gmail.com', '12345', 1, 3)