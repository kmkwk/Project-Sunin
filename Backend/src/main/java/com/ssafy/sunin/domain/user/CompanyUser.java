package com.ssafy.sunin.domain.user;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

    @Column(length =45, unique = true)
    private String companyId;

    @Column(length =1000, nullable = false)
    private String companyPassword;

    @Column(length =45, nullable = false)
    private String companyName;

    @Column(length =45, nullable = false)
    private String companyNickname;

    @Column(length =45, nullable = false)
    private String companyTel;

    @Column(length =45, nullable = false)
    private String companyAddress;

    @Enumerated(EnumType.STRING)
    @Column(name="user_role")
    private Role role; //권한

    private LocalDateTime createdDatetime;

    @Column()
    private boolean approval;

    @PrePersist //디비에 insert 되기직전에 실행
    public void created_datetime() {
        this.createdDatetime = LocalDateTime.now();
    }


    //

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return companyPassword;
    }

    @Override
    public String getUsername() {
        return companyId;
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

}
