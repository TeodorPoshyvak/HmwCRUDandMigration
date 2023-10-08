select client."name", count(project.id) as project_count from client
                                                                  join
                                                              project on client.id = project.client_id
 group by client."name"
having max(project.id) = (select max(project.id) from project)