package org.example.DataBaseTable.Entity;

import java.time.LocalDate;

public class Worker {
    private int id;
    private String name;
    private LocalDate birthday;
    private String level;
    private int salary;

    public Worker(int id, String name, LocalDate birthday, String level, int salary) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.level = level;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getLevel() {
        return level;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Worker(" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", level='" + level + '\'' +
                ", salary=" + salary +
                ')';
    }
}
