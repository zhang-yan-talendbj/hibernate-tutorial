package org.hibernate.tutorial.domain;

import java.util.Date;

/**
 * Represents an event that we want to store in the database
 * 
 */
public class Event {

    private Long id;
    private String title;
    private Date date;

    public Long getId() {
        return id;
    }

    /**
     * We usually do not manipulate the identity of an object, hence the setter method should be
     * private. Only Hibernate will assign identifies when an object is saved. Hibernate can access
     * public, private, and protected accessor methods, as well as public, private and protected
     * fields directly.
     * 
     * @param id
     */
    @SuppressWarnings("unused")
    private void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
