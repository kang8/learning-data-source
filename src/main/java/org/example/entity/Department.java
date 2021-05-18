package org.example.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class Department implements Serializable {

    private Long id;

    private String name;

    private String tel;

    private Set<User> users;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", users=" + users +
                '}';
    }

    public Department() {
    }

    public Department(Long id, String name, String tel, Set<User> users) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.users = users;
    }

    public Department(Long id, String name, String tel) {
        this.id = id;
        this.name = name;
        this.tel = tel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(tel, that.tel) && Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tel, users);
    }
}
