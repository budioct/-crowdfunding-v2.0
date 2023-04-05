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
@Table(name = "campaignimages", schema = "jointable")
@SequenceGenerator(
        name = "seq_generator_campaignimages",
        schema = "generator",
        allocationSize = 1,
        sequenceName = "seq_campaignimages",
        initialValue = 1)
public class CampaignImages {

    @Id
    @Column(name = "id_campaignimages", nullable = false)
    @GeneratedValue(generator = "seq_generator_campaignimages")
    private Long idcampaignimages;
    @Column(name = "filename")
    private String filename;
    @Column(name = "isprimary")
    private String isprimary; // (tinyint) 1= true 0=false
    @Column(name = "create_at")
    private LocalDateTime create_at;
    @Column(name = "update_at")
    private LocalDateTime update_at;

    @ManyToOne
    @JoinColumn(name = "id_campaigns")
    private Campaign campaigns;


}
