package de.marshal.hw20230815.entity;

public class Person implements Comparable<Person> {
    public static final int LEGAL_AGE = 18;
    private String name;
    private int age;
    private String address;
    private String email;

    public Person(String name, int age, String address, String email) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.email = email;
    }

    public String getSummary() {
        return toString();
    }

    public boolean isAdult() {
        return age >= LEGAL_AGE;
    }

    public void sendEmail(String message) {

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return name.compareTo(o.getName());
    }
}
