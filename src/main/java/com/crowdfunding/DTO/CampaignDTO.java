package com.crowdfunding.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Component
public class CampaignDTO {
//    private Long idcampaign;
    private String name;
    private String shortdescription;
    private String description;
    private int goalamount;
    private int currentamount;
    private String perks;
    private int backercount;
    private String slug;
    private LocalDateTime create_at;
    private LocalDateTime update_at;
    private Long iduser;

    private List<UsersDTO> usersDTOList;

}
