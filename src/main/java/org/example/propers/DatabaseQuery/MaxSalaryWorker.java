package org.example.propers.DatabaseQuery;

public class MaxSalaryWorker {
    private String name;
    private int SALARY;

    public MaxSalaryWorker(String name, int SALARY) {
        this.name = name;
        this.SALARY = SALARY;
    }

    public String getName() {
        return name;
    }

    public int getSALARY() {
        return SALARY;
    }

    @Override
    public String toString() {
        return "MaxSalaryWorker{" +
                "name='" + name + '\'' +
                ", SALARY=" + SALARY +
                '}';
    }
}
