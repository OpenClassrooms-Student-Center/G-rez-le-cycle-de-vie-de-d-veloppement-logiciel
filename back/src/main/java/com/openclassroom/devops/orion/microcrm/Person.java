package com.openclassroom.devops.orion.microcrm;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.PreRemove;

@Entity
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  public long getId() {
    return id;
  }

  public Person() {
  }

  public Person(String firstName, String lastName, String email) {
    this.setFirstName(firstName);
    this.setLastName(lastName);
    this.setEmail(email);
  }

  private String firstName;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  private String lastName;

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  private String email;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  private String phone;

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  private String bio;

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  @ManyToMany(mappedBy = "persons")
  private List<Organization> organizations;

  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  @UpdateTimestamp
  private Date updatedAt;

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  @PreRemove
  private void remoteFromOrganization() {
    for (Organization org : organizations) {
      org.removePerson(this);
    }
  }

}
