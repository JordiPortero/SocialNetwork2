package com.company;

import java.util.Comparator;

public class Persona implements Comparable<Persona> {
    private Long id;
    private String name;
    private String surname;



    public Persona(String name, String surname, Long id) {
        this.name = name;
        this.id = id;
        this. surname = surname;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Persona persona = (Persona) o;

        if (getId() != persona.getId()) return false;
        if (!getName().equals(persona.getName())) return false;
        return getSurname().equals(persona.getSurname());

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getName().hashCode();
        result = 31 * result + getSurname().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public int compareTo(Persona persona) {
        return this.getId().compareTo(persona.getId());
    }
}
