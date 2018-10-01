package org.syaku.exemple.app.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 28.
 */
@Entity
@Table(name = "DEMO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DemoEntity implements Demo {
    @Id
    @Column(name = "DEMO_SRL")
    @SequenceGenerator(
        name = "DEMO_SRL_GEN",
        sequenceName = "DEMO_SRL_SEQ",
        allocationSize = 1)
    @GeneratedValue(generator = "DEMO_SRL_GEN", strategy = GenerationType.SEQUENCE)
    private long demoIdx;
    private String subject;
    @Lob
    private String contents;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @PrePersist
    public void onPrePersist() {
        this.creationDate = new Date();
    }
}
