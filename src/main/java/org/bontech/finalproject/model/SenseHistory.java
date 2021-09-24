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
public class SenseHistory extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long amount;

    @ManyToOne(targetEntity = Sensor.class, fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "sensor_id")
    private Sensor sensor;

}
