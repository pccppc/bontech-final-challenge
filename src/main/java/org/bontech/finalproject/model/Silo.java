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
public class Silo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Long maximumCapacity;

    private Long currentWeight;

    @JsonIgnore
    @ManyToOne(targetEntity = Storage.class,fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "storage_id")
    private Storage storage;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, targetEntity = Sensor.class)
    private Sensor sensor;

    @JsonIgnore
    @OneToMany(targetEntity = ChangesOfSilo.class, fetch = FetchType.LAZY , mappedBy = "silo")
    List<ChangesOfSilo> changesHistory;

}
