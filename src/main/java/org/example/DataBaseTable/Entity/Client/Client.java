package org.example.DataBaseTable.Entity.Client;

public class Client{
    private long id;
    private String name;

    public Client(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Client(" +
                "id=" + id +
                ", name='" + name + '\'' +
                ')';
    }
}
