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
public class SenseHistory {
    @Id
    private Long id;

    private Long amount;

    @ManyToOne(targetEntity = Sensor.class, fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "sensor_id")
    private Sensor sensor;

    @LastModifiedDate
    private Date date;
}
