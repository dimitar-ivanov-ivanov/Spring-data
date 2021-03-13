package bookshop.system.services;

import bookshop.system.models.Author;

public class AuthorWithBookCount implements Comparable<AuthorWithBookCount> {

    private String firstName;
    private String lastName;
    private int bookCount;

    public AuthorWithBookCount(String firstName, String lastName, int bookCount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookCount = bookCount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    @Override
    public String toString() {
        return "AuthorWithBookCount{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bookCount=" + bookCount +
                '}';
    }

    @Override
    public int compareTo(AuthorWithBookCount other) {
        return Integer.compare(this.getBookCount(), other.getBookCount());
    }
}
