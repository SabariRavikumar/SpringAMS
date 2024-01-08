package org.sabframeworks.ams.appuser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Entity
public class AppUser implements UserDetails {


    @SequenceGenerator(
            name = "user_sequence",
            sequenceName="user_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )

    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String passWord;
    private String country;
    private String state;
    @Enumerated
    private AppUserRole userRole;
    private Boolean locked = false;
    private Boolean enabled = false;

    public AppUser() {
    }

    public AppUser(String firstName, String lastName, String emailId, String passWord, String country, String state, AppUserRole userRole, Boolean locked, Boolean enabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.passWord = passWord;
        this.country = country;
        this.state = state;
        this.userRole = userRole;
        this.locked = locked;
        this.enabled = enabled;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(id, appUser.id) && Objects.equals(firstName, appUser.firstName) && Objects.equals(lastName, appUser.lastName) && Objects.equals(emailId, appUser.emailId) && Objects.equals(passWord, appUser.passWord) && Objects.equals(country, appUser.country) && Objects.equals(state, appUser.state) && userRole == appUser.userRole && Objects.equals(locked, appUser.locked) && Objects.equals(enabled, appUser.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, emailId, passWord, country, state, userRole, locked, enabled);
    }

    public AppUser(String firstName, String lastName, String emailId, String passWord, String country, String state, AppUserRole userRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.passWord = passWord;
        this.country = country;
        this.state = state;
        this.userRole = userRole;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singleton(authority);
    }

    public String getPassword() {
        return passWord;
    }

    public String getUsername() {
        return firstName;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return !locked;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return enabled;
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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public AppUserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(AppUserRole userRole) {
        this.userRole = userRole;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
