package org.bontech.finalproject.model;

import lombok.*;
import org.bontech.finalproject.model.Sensor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class IncreaseOrDecreaseFromSilo {
    @Id
    private Long id;

    private Long amount;

    @ManyToOne(targetEntity = Sensor.class, fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "sensor_id")
    private Sensor sensor;

    @UpdateTimestamp
    private LocalDate dateTime;
}
