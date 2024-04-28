package com.example.grahstibackend.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity
public class User extends BaseEntity implements UserDetails {
    // @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    // @Column(nullable = false)
    // private Integer id;
    // @Id
    // @GeneratedValue(generator = "uuid2")
    // @GenericGenerator(name = "uuid2", strategy =
    // "org.hibernate.id.UUIDGenerator")
    // @Column(name = "id")
    // private UUID id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(unique = true, length = 10, nullable = true)
    private long mobileNumber;

    @Column(nullable = false)
    private String password;

    // @CreationTimestamp
    // @Column(updatable = false, name = "created_at")
    // private Date createdAt;

    // @UpdateTimestamp
    // @Column(name = "updated_at")
    // private Date updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return id.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullName() {
        return fullName;
    }

    public User setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public User setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
