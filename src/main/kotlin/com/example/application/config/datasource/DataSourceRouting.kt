package com.example.application.config.datasource
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.stereotype.Component
import java.io.IOException
import java.util.*
import javax.sql.DataSource
import kotlin.collections.HashMap

@Component
class DataSourceRouting(
    private val dataSourceContextHolder: DataSourceContextHolder,
) : AbstractRoutingDataSource() {
    init {
        val dataSourceMap: MutableMap<Any, Any> = HashMap()
        val properties = getSecurityProperties()
        dataSourceMap[DataSourceEnum.DATA_SOURCE_UNKNOWN] = dataSourceUnknownConfig(properties)
        dataSourceMap[DataSourceEnum.DATA_SOURCE_AUTH] = dataSourceAuthConfig(properties)
        setTargetDataSources(dataSourceMap)
        setDefaultTargetDataSource(dataSourceUnknownConfig(properties))
    }

    override fun determineCurrentLookupKey(): Any? {
        return dataSourceContextHolder.getContext()
    }

    fun dataSourceUnknownConfig(properties: Properties): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.url = properties.getProperty("db.unknown.url")
        dataSource.username = properties.getProperty("db.unknown.login")
        dataSource.password = properties.getProperty("db.unknown.password")
        return dataSource.also { dataSourceCommonConfig(it) }
    }

    fun dataSourceAuthConfig(properties: Properties): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.url = properties.getProperty("db.auth.url")
        dataSource.username = properties.getProperty("db.auth.login")
        dataSource.password = properties.getProperty("db.auth.password")
        return dataSource.also { dataSourceCommonConfig(it) }
    }

    fun dataSourceCommonConfig(ds: DriverManagerDataSource) =
        ds.apply {
            setDriverClassName("org.postgresql.Driver")
            catalog = "postgres"
            schema = "public"
        }

    private fun getSecurityProperties(): Properties {
        try {
            val prop = Properties()
            val inStr = javaClass.classLoader.getResourceAsStream("dbsecurity.properties")
            prop.load(inStr)

            return prop
        } catch (ex: IOException) {
            throw IllegalArgumentException("Cant find file dbsecurity.properties", ex)
        }
    }
}
