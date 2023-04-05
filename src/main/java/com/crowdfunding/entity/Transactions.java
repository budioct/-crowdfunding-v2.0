package com.crowdfunding.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "transaction", schema = "jointable")
@SequenceGenerator(
        name = "seq_generator_transaction",
        schema = "generator",
        allocationSize = 1,
        sequenceName = "seq_transaction",
        initialValue = 1)
public class Transactions {

    @Id
    @Column(name = "id_transaction", nullable = false)
    @GeneratedValue(generator = "seq_generator_transaction")
    private Long idtransaction;
    @Column(name = "amount")
    private int amount;
    @Column(name = "status")
    private String status;
    @Column(name = "code")
    private String code;
    @Column(name = "create_at")
    private LocalDateTime create_at;
    @Column(name = "update_at")
    private LocalDateTime update_at;

    @ManyToOne
    @JoinColumn(name= "id_user")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "id_campaign")
    private Campaign campaigns;

}
