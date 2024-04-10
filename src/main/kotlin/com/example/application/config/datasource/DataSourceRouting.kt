package com.example.application.config.datasource
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class DataSourceRouting(
    private val dataSourceContextHolder: DataSourceContextHolder,
) : AbstractRoutingDataSource() {
    init {
        val dataSourceMap: MutableMap<Any, Any> = HashMap()
        dataSourceMap[DataSourceEnum.DATA_SOURCE_UNKNOWN] = dataSourceUnknownConfig()
        dataSourceMap[DataSourceEnum.DATA_SOURCE_AUTH] = dataSourceAuthConfig()
        setTargetDataSources(dataSourceMap)
        setDefaultTargetDataSource(dataSourceUnknownConfig())
    }

    override fun determineCurrentLookupKey(): Any? {
        return dataSourceContextHolder.getContext()
    }

    fun dataSourceUnknownConfig(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.url = "jdbc:postgresql://localhost:32768/postgres"
        dataSource.username = "guest"
        dataSource.password = "guest"
        return dataSource.also { dataSourceCommonConfig(it) }
    }

    fun dataSourceAuthConfig(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.url = "jdbc:postgresql://localhost:32768/postgres"
        dataSource.username = "authorized"
        dataSource.password = "authorized"
        return dataSource.also { dataSourceCommonConfig(it) }
    }

    fun dataSourceCommonConfig(ds: DriverManagerDataSource) =
        ds.apply {
            setDriverClassName("org.postgresql.Driver")
            catalog = "postgres"
            schema = "public"
        }
}
