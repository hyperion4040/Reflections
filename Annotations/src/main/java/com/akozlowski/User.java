package com.akozlowski;

@Entity("user")
public class User {
    @Field(columnName = "id",isKey = true)
    private Long id;
    @Field
    private String name;

    @Field
    private String password;

//    private int age;

    public User(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
