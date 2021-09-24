package org.bontech.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Sensor{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Long amount; // last estimated amount

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Silo.class, mappedBy = "sensor")
    private Silo silo;

    @JsonIgnore
    @OneToMany(targetEntity = SenseHistory.class,fetch = FetchType.LAZY,mappedBy = "sensor")
    List<SenseHistory> history;

}
