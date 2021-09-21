package org.bontech.finalproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Silo.class, mappedBy = "sensor")
    private Silo silo;

    @OneToMany(targetEntity = SenseHistory.class,fetch = FetchType.LAZY,mappedBy = "sensor")
    List<SenseHistory> history;

}
