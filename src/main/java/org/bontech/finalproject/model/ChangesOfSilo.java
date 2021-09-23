package org.bontech.finalproject.model;


import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class ChangesOfSilo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private Long amount;

    @LastModifiedDate
    private Date date;

    @ManyToOne(targetEntity = Silo.class, fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "silo_id")
    private Silo silo;
}
