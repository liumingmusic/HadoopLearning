select
  firstname,
  lastname,
  address,
  employer,
  state
from
  bank_table
where
  gender = "M"
AND
  balance >= 30000