select
  a1.gender,
  a1.all_count as all_count,
  a2.age_count_28 as age_count_28,
  a3.age_count_38 as age_count_38
from
(
  select
    gender,
    count(1) as all_count
  from
    bank_table
  group by
    gender
) as a1
left join
(
  select
    gender,
    count(1) as age_count_28
  from
    bank_table
  where
    age >= 28
  group by
    gender
) as a2
on a1.gender = a2.gender
left join
(
  select
    gender,
    count(1) as age_count_38
  from
    bank_table
  where
    age >= 38
  group by
    gender
) as a3
on a1.gender = a3.gender
