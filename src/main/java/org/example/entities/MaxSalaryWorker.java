package org.example.entities;

public class MaxSalaryWorker {
    private String name;
    private int SALARY;

    public MaxSalaryWorker(String name, int SALARY) {
        this.name = name;
        this.SALARY = SALARY;
    }

    @Override
    public String toString() {
        return "MaxSalaryWorker{" +
                "name='" + name + '\'' +
                ", SALARY=" + SALARY +
                '}';
    }
}
