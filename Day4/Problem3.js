/*
You are tasked with managing an Employee Performance Tracker for an organization. The company 
has several employees, each belonging to a department and having a list of ratings for different
performance review periods.

Your goal is to perform the following operations on the list of employees:

- Remove a Specific Employee:
  Given an employee's name (e.g., "Emily"), find and remove this employee from the list of employees.

- Identify Low Performers:
  An employee is considered a low performer if any of their ratings are below 60.
  Output the names of the employees who have ratings below 60.

- Compute the Average Rating for Each Employee:
  Calculate the average rating for each employee by averaging all the ratings they received 
  during different review periods.

- Identify Top Performers:
  An employee is considered a top performer if their average rating is greater than 85.
  Output the names of the top performers, along with their average ratings, in descending 
  order of their average rating.

- Format the List of Top Performers:
  Format the top performers' list as a string in the following format: 
     name : averageRating (rounded to two decimal places).

- Display the Results:
  Print the names of the low performers.
  Print the names and average ratings of the top performers, 
  sorted in descending order of their average ratings.

Expected Output:
----------------

Low Performers: [ 'Mark', 'Paul' ]
Top Performers: [ 'Sophia : 92.33', 'John : 87.67', 'Olivia : 87.33' ]

*/

const employees = [
  { id: 1, name: "John", department: "HR", ratings: [85, 90, 88] },
  { id: 2, name: "Jane", department: "Engineering", ratings: [80, 82, 79] },
  { id: 3, name: "Mark", department: "Sales", ratings: [51, 94, 93] },
  { id: 4, name: "Paul", department: "HR", ratings: [55, 60, 58] },
  { id: 5, name: "Emily", department: "Engineering", ratings: [78, 85, 80] },
  { id: 6, name: "Sophia", department: "Marketing", ratings: [92, 95, 90] },
  { id: 7, name: "Olivia", department: "Sales", ratings: [87, 85, 90] },
  { id: 8, name: "Lucas", department: "Marketing", ratings: [72, 70, 75] }
];

let index=employees.findIndex(employee=>employee.name==="Emily");
let removedlist=employees.splice(index,1);
let lowPerformers = employees
  .filter(employee => employee.ratings.some(rating => rating < 60))
  .map(employee => employee.name);
employees.forEach(employee => {
employee.averageRating = employee.ratings.reduce((sum, r) => sum + r, 0) / employee.ratings.length;
});
let topPerformers = employees
  .filter(employee => employee.averageRating > 85)
  .sort((a, b) => b.averageRating - a.averageRating)
  .map(employee => `${employee.name} : ${employee.averageRating.toFixed(2)}`);
console.log("Low Performers:", lowPerformers);
console.log("Top Performers:", topPerformers);
