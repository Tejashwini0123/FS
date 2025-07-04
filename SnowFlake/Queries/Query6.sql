/*
Write a query to calculate the running total of employee salaries ordered by 
their hire date.


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
name    hire_date       salary  running_salary_total                            
Grace   2018-12-05      69000   69000                                           
Charlie 2019-06-01      70000   139000  

*/
use univ;
select name,hire_date,salary,SUM(salary) OVER (order by hire_date ) AS running_salary_total from employees;
