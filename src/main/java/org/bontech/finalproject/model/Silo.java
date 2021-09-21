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
public class Silo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Long maximumCapacity;

    private Long currentWeight;

    @ManyToOne(targetEntity = Warehouse.class,fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "warehouse_id")
    private Warehouse warehouse;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Sensor.class)
    private Sensor sensor;

    @OneToMany(targetEntity = ChangesOfSilo.class, fetch = FetchType.LAZY , mappedBy = "silo")
    List<ChangesOfSilo> changesHistory;

}
