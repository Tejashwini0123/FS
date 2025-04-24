/*<!-- 
  Query 1: Extract JSON Fields as Columns:  
> Retrieve the `name`, `age`, and `location` for each customer. The `age`
and `location` fields are stored inside the `profile` JSON column. Return
them as separate columns in the result.

Sample Output::

NAME	AGE	LOCATION
Alice	30	"New York"
Bob	25	"San Francisco"
Charlie	35	"Chicago"

 -->
 */
SELECT
name,
profile:age AS age,
profile:location AS location
FROM customer_profiles;
