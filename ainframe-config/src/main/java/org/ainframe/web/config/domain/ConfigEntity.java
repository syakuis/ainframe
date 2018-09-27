package org.ainframe.web.config.domain;

import lombok.*;
import org.ainframe.core.data.enums.YesOrNo;
import org.ainframe.web.config.model.Config;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 30.
 */
@Entity
@Table(name = "CONFIG")
@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigEntity implements Serializable {
    @SequenceGenerator(
        name = "CONFIG_IDX_GEN",
        sequenceName = "CONFIG_IDX_SEQ",
        allocationSize = 1
    )
    @GeneratedValue(
        generator = "CONFIG_IDX_GEN",
        strategy = GenerationType.SEQUENCE)
    @GenericGenerator(
        name = "CONFIG_IDX_GEN",
        strategy = "org.ainframe.data.jpa.StringSequenceIdentifier",
        parameters = {
            @org.hibernate.annotations.Parameter(
                name = "sequence_name", value = "CONFIG_IDX_SEQ"),
            @org.hibernate.annotations.Parameter(
                name = "sequence_prefix", value = "CONF"),
        }
    )
    @Id
    @Column(name = "CONFIG_IDX")
    private String configIdx;
    @Column(name = "MODULE_IDX")
    private String moduleIdx;
    @Column(name = "TITLE")
    private String browserTitle;
    @Enumerated(EnumType.STRING)
    @Column(name = "TITLE_OVERWRITE")
    private YesOrNo titleOverwrite;
    @Column(name = "INDEX_PAGE")
    private String indexUrl;
    @Column(name = "LAYOUT_IDX")
    private String layoutIdx;
    @Column(name = "BASIC_SKIN")
    private String basicSkin;
    @Column(name = "SKIN")
    private String skin;
    @Column(name = "STYLE_THEME")
    private String styleTheme;

    public static Config transfer(ConfigEntity configEntity) {
        return Config.builder()
            .basicSkin(configEntity.getBasicSkin())
            .skin(configEntity.getSkin())
            .browserTitle(configEntity.getBrowserTitle())
            .browserTitleOverwrite(configEntity.getTitleOverwrite().isValue())
            .indexUrl(configEntity.getIndexUrl())
            .layoutIdx(configEntity.getLayoutIdx())
            .styleTheme(configEntity.getStyleTheme())
            .build();
    }
}
