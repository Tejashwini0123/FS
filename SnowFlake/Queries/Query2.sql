/*
Write a query to rank employees in each department based on their hire date, 
such that the earliest hired employee gets the highest rank (1).


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
name    department      hire_date       seniority_rank                          
Grace   Engineering     2018-12-05      1                                       
Charlie Engineering     2019-06-01      2                                       

*/
use univ;
select name,department,hire_date,RANK() OVER (partition by department order by hire_date ) AS seniority_rank from employees;
