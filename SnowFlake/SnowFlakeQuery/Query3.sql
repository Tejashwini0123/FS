/*<!-- 
Query 3: Check if 'interests' Field Exists:  
> For each customer, check if the `interests` field exists in the `profile`
JSON. Return the customer `name` and a new column called `has_interests`, 
which should be `'Yes'` if `interests` exists and `'No'` if it does not.
*/
SELECT
  name,
  CASE 
    WHEN TRY_PARSE_JSON(profile):interests IS NOT NULL 
         AND ARRAY_SIZE(TRY_PARSE_JSON(profile):interests) > 0 THEN 'Yes'
    ELSE 'No'
  END AS has_interests
FROM customer_profiles;
