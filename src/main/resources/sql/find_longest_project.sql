select 'PROJECT A' as project_name,
       (date_part('year',finish_date) - date_part('year', start_date))*12+
       (date_part('month',finish_date) - date_part('month', start_date))
                   as count_mounth
 from project
 where (date_part('year',finish_date) - date_part('year', start_date))*12+
      (date_part('month',finish_date) - date_part('month', start_date)) =
      (select max((date_part('year',finish_date) - date_part('year', start_date))*12+
                  (date_part('month',finish_date) - date_part('month', start_date))) from project)