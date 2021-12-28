package com.polytech.codev.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(	name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "profile_metropolises",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "metropolis_id"))
    private Set<Metropolis> categories = new HashSet<>();

    public Profile() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Metropolis> getCategories() {
        return categories;
    }

    public void setCategories(Set<Metropolis> categories) {
        this.categories = categories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
