package cn.huanzi.qch.baseadmin.config.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryPrimary",
        transactionManagerRef="transactionManagerPrimary",
        basePackages= { "cn.huanzi.qch.baseadmin.sys.sysauthority.pojo","cn.huanzi.qch.baseadmin.sys.sysmenu.pojo","cn.huanzi.qch.baseadmin.sys.syssetting.pojo",
        "cn.huanzi.qch.baseadmin.sys.sysshortcutmenu.pojo","cn.huanzi.qch.baseadmin.sys.sysuser.pojo",
        "cn.huanzi.qch.baseadmin.sys.sysuserauthority.pojo","cn.huanzi.qch.baseadmin.sys.sysusermenu.pojo","cn.huanzi.qch.baseadmin.common.repository"}) //设置Repository所在位置

public class PrimaryConfig {

    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;

    @Autowired
    @Qualifier("vendorProperties")
    private Map<String, Object> vendorProperties;

    @Primary
    @Bean(name = "entityManagerPrimary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactoryPrimary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(primaryDataSource)
                .properties(vendorProperties)
                .packages("cn.huanzi.qch.baseadmin.sys.sysauthority.pojo","cn.huanzi.qch.baseadmin.sys.sysmenu.pojo","cn.huanzi.qch.baseadmin.sys.syssetting.pojo",
                        "cn.huanzi.qch.baseadmin.sys.sysshortcutmenu.pojo","cn.huanzi.qch.baseadmin.sys.sysuser.pojo",
                        "cn.huanzi.qch.baseadmin.sys.sysuserauthority.pojo","cn.huanzi.qch.baseadmin.sys.sysusermenu.pojo","cn.huanzi.qch.baseadmin.common.repository") //设置实体类所在位置
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }


    @Primary
    @Bean(name = "transactionManagerPrimary")
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }
}
