package org.bontech.finalproject.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class ScheduledSense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private LocalTime period;

    @OneToOne(targetEntity = Sensor.class, fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "sensor_id")
    private Sensor sensor;
}
