package com.crowdfunding.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users", schema = "jointable")
@SequenceGenerator(
        name = "seq_generator_users",
        schema = "generator",
        allocationSize = 1,
        sequenceName = "seq_users",
        initialValue = 1)
@Component
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Users {

    @Id
    @Column(name = "id_user", nullable = false)
    @GeneratedValue(generator = "seq_generator_users")
    private Long iduser;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "occupation", length = 50)
    private String occupation;
    @Column(name = "email", length = 100)
    private String email;
    @Column(name = "passwordhash", nullable = false)
    private String passwordhash;
    @Column(name = "avatarfilename")
    private String avatarfilename;
    @Column(name = "role")
    private String role;
    @Column(name = "token")
    private String token;
    @Column(name = "create_at")
    private LocalDateTime create_at;
    @Column(name = "update_at")
    private LocalDateTime update_at;

//    @Fetch(FetchMode.SELECT) // Changing the fetch profile you can solve the problem
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @FieldNameConstants.Exclude
    @JsonIgnore // akan hidden object campaignList
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Campaign> campaignList = new ArrayList<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @FieldNameConstants.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transactions> transactionList = new ArrayList<>();

}
