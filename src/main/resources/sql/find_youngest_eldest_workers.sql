select 'YONGEST' as TYPE,
       NAME,
       BIRTHDAY
 from worker
 where birthday = (select max(birthday) from worker)
 union
 select 'OLDEST' as TYPE,
       NAME,
       BIRTHDAY
 from worker
 where birthday = (select min(birthday) from worker)