select project.id, sum(worker.salary * (date_part('year',finish_date) - date_part('year', start_date))*12+
                       (date_part('month',finish_date) - date_part('month', start_date)))
from project
         join
     project_worker on project.id = project_worker.project_id
         join
     worker on project_worker.worker_id = worker.id
group by project.id
order by id;