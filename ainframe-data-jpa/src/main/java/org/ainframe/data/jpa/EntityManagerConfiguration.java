package org.ainframe.data.jpa;

import org.ainframe.data.jpa.config.JpaProperties;
import org.ainframe.data.jpa.config.SpringJpaProperties;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.Set;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 4. 14.
 */
//@Configuration
//@ConditionalOnProperty(name="spring.data.jpa.repositories.enabled", havingValue="true")
@EnableConfigurationProperties({JpaProperties.class, SpringJpaProperties.class})
@EnableTransactionManagement
@EnableJpaRepositories("org.ainframe")
public class EntityManagerConfiguration {

  @Autowired
  private DataSource dataSource;

  @Autowired
  private JpaProperties jpaProperties;

  @Autowired
  private SpringJpaProperties springJpaProperties;

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
    LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
    bean.setDataSource(dataSource);

    String packagesToScan[] = org.springframework.util.StringUtils.tokenizeToStringArray(
        jpaProperties.getDefaultPackageToScan(), ",");

    if (StringUtils.isNotEmpty(jpaProperties.getPackageToScan())) {
        packagesToScan = ArrayUtils.addAll(packagesToScan, org.springframework.util.StringUtils.tokenizeToStringArray(
            jpaProperties.getPackageToScan(), ","));
    }

    bean.setPackagesToScan(packagesToScan);
    bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    bean.setJpaProperties(hibernateProperties());

    return bean;
  }

  private Properties hibernateProperties() {
      Properties springJpa = springJpaProperties.getProperties();
    Properties result = new Properties();

    Set<String> names = springJpa.stringPropertyNames();
    for (String name : names) {
        if (StringUtils.startsWith(name, "hibernate.")) {
            result.setProperty(name, springJpa.getProperty(name));
        }
    }

    return result;
  }

//  @Bean
//  @Primary
//  public JpaTransactionManager transactionManager() {
//    JpaTransactionManager transactionManager = new JpaTransactionManager();
//    transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
//    return transactionManager;
//  }
}