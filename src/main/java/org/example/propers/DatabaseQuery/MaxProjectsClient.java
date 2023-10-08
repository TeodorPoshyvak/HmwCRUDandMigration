package org.example.propers.DatabaseQuery;

public class MaxProjectsClient {
    private String name;
    private int project_count;

    public MaxProjectsClient(String name, int project_count) {
        this.name = name;
        this.project_count = project_count;
    }

    public String getName() {
        return name;
    }

    public int getProject_count() {
        return project_count;
    }

    @Override
    public String toString() {
        return "MaxProjectectsClient{" +
                "name='" + name + '\'' +
                ", project_count=" + project_count +
                '}';
    }
}
