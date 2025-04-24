/*
<!--Query 2: Filter Customers Based on Age:  
> Write a query to list the `name` and `age` of customers whose age
(inside the `profile` JSON) is greater than 30. The result should only 
include customers matching this age condition.

Sample Output::
NAME	AGE
Charlie	35

-->
*/
SELECT
name,
profile:age AS age
FROM customer_profiles
WHERE profile:age > 30;
