/*
Write a query that numbers employees across the company based on descending 
salary (highest salary = row number 1).


---------------
Database : univ
---------------

employees (
    employee_id INT PRIMARY KEY,
    name VARCHAR(50),
    department VARCHAR(50),
    salary INT,
    hire_date DATE
);

projects (
    project_id INT PRIMARY KEY,
    project_name VARCHAR(100),
    department VARCHAR(50)
);

employee_projects (
    employee_id INT,
    project_id INT,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
    FOREIGN KEY (project_id) REFERENCES projects(project_id)
);


Sample Output:
--------------
name    department      salary  salary_position                                 
Diana   Engineering     71000   1                                               
Charlie Engineering     70000   2                                               


*/
use univ;
select name,department,salary,RANK() OVER ( order by salary desc) AS salary_position from employees;
