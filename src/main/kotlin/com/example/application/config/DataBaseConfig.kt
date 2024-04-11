package com.example.application.config

import com.example.application.config.datasource.DataSourceRouting
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.*
import org.springframework.core.env.Environment
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.io.IOException
import java.util.*
import javax.annotation.Resource
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    basePackages = ["com.example.application.repository"],
    transactionManagerRef = "transcationManager",
    entityManagerFactoryRef = "entityManager",
)
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScan("com.example.application")
@DependsOn("dataSourceRouting")
class DataBaseConfig(
    private val dataSourceRouting: DataSourceRouting,
) {
    @Resource
    private lateinit var env: Environment

    @Bean
    @Primary
    fun dataSource(): DataSource? {
        return dataSourceRouting
    }

    @Bean(name = ["entityManager"])
    fun entityManagerFactoryBean(builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean? {
        return builder
            .dataSource(dataSource())
            .packages("com.example.application")
            .build().apply {
                jpaVendorAdapter = HibernateJpaVendorAdapter()
                setJpaProperties(getHibernateProperties())
            }
    }

    @Bean(name = ["transcationManager"])
    fun transactionManager(
        @Autowired @Qualifier("entityManager") entityManagerFactoryBean: LocalContainerEntityManagerFactoryBean,
    ): JpaTransactionManager {
        return JpaTransactionManager(entityManagerFactoryBean.getObject()!!)
    }

    private fun getHibernateProperties(): Properties {
        try {
            val prop = Properties()
            val inStr = javaClass.classLoader.getResourceAsStream("hibernate.properties")
            prop.load(inStr)

            return prop
        } catch (ex: IOException) {
            throw IllegalArgumentException("Cant find file hibernate.properties", ex)
        }
    }
}
