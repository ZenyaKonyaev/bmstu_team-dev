package com.example.application.dao.cake

import com.example.application.converters.cake.cakeEntityAndBusinessConverter.CakeEntityAndBusinessConverter
import com.example.application.converters.cakePart.cakePartEntityAndBusinessConverter.CakePartEntityAndBusinessConverter
import com.example.application.dto.cake.CakeBusinessDto
import com.example.application.dto.cakePart.CakePartBusinessDto
import com.example.application.entity.cake_part.CakePartEntity
import com.example.application.entity.custom_cake.CakeEntity
import com.example.application.exception.DataBaseException
import com.example.application.exception.UnavailableTechnicalException
import com.example.application.repository.CakePartRepository
import com.example.application.repository.CakeRepository
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Repository

@Repository
class CakeDaoImpl: CakeDao {
    @Autowired
    private lateinit var cakeRepository: CakeRepository

    @Autowired
    private lateinit var cakePartRepository: CakePartRepository

    @Autowired
    private lateinit var cakeConverter: CakeEntityAndBusinessConverter

    @Autowired
    private lateinit var cakePartConverter: CakePartEntityAndBusinessConverter

    private val logger = LogManager.getLogger(this::class.java)

    override fun getCakeBaseParts(): List<CakePartBusinessDto> {
        val cakes: List<CakePartEntity>
        try {
            cakes = cakePartRepository.getCakeBaseParts()
        } catch (ex: DataAccessException) {
            logger.error("Cant get all cakes from cakeRepository.\n${ex.message}\n${ex.cause}")
            throw UnavailableTechnicalException(ex.stackTraceToString())
        }

        return cakes.map { cakePartConverter.convert(it) }
    }

    override fun getCakeFillingParts(): List<CakePartBusinessDto> {
        val cakes: List<CakePartEntity>
        try {
            cakes = cakePartRepository.getCakeFillingParts()
        } catch (ex: DataAccessException) {
            logger.error("Cant get all cakes from cakeRepository.\n" +
                    "${ex.message}\n" +
                    "${ex.cause}")
            throw UnavailableTechnicalException(ex.stackTraceToString())
        }

        return cakes.map { cakePartConverter.convert(it) }
    }

    override fun getCakeCreamParts(): List<CakePartBusinessDto> {
        val cakes: List<CakePartEntity>
        try {
            cakes = cakePartRepository.getCakeCreamParts()
        } catch (ex: DataAccessException) {
            logger.error("Cant get all cakes from cakeRepository.\n" +
                    "${ex.message}\n" +
                    "${ex.cause}")
            throw UnavailableTechnicalException(ex.stackTraceToString())
        }

        return cakes.map { cakePartConverter.convert(it) }
    }

    override fun getCakePartById(id: Long): CakePartBusinessDto {
        val cakePart: CakePartEntity
        try {
            cakePart = cakeRepository.getCakePartById(id)
        } catch (ex: EmptyResultDataAccessException) {
            logger.error("Cant find cake part by id=$id")
            throw DataBaseException("Cant find cake part by id=$id")
        } catch (ex: DataAccessException) {
            logger.error("Cant getCakePartById by unknown DataAccessException. id = $id.\n" +
                    "${ex.message}\n" +
                    "${ex.cause}")
            throw UnavailableTechnicalException(ex.stackTraceToString())
        }

        return cakePartConverter.convert(cakePart)
    }

    override fun getCakeByPartIds(idBase: Long, idFilling: Long, idCream: Long): CakeBusinessDto {
        val cake = cakeRepository.getAllCakes().find {
            it.basePart.id == idBase && it.fillingPart.id == idFilling && it.creamPart.id == idCream
        }?.let { cakeConverter.convert(it) }

        if (cake == null) {
            logger.error("Cant find cake with part ids: (base=$idBase, filling=$idFilling, cream=$idCream)")
            throw DataBaseException("Cant find cake with part ids: (base=$idBase, filling=$idFilling, cream=$idCream)")
        }

        return cake
    }

    override fun getAllCakes(): List<CakeBusinessDto> {
        return cakeRepository.getAllCakes().map { cakeConverter.convert(it) }
    }

    override fun addCakePart(dto: CakePartBusinessDto) {
        val entity: CakePartEntity

        try {
            entity = cakePartConverter.convert(dto)
        } catch (ex: Exception) {
            logger.error("cant convert cakePart business dto to Entity.\n${ex.message}\n${ex.cause}")
            throw UnavailableTechnicalException(ex.stackTraceToString())
        }

        logger.debug("Entity cakePart before save: $entity")
        try {
            cakePartRepository.save(entity)
        } catch (ex: DataAccessException) {
            logger.error("Cant save cakePartEntity to database.\n${ex.message}\n${ex.cause}")
            throw UnavailableTechnicalException(ex.stackTraceToString())
        }
    }

    override fun deleteCakePart(id: Long) {
        logger.debug("deleting cakePartEntity with id=$id")

        try {
            cakePartRepository.delete(cakePartRepository.getCakePartById(id))
        } catch (ex: DataAccessException) {
            logger.error("Cant delete cakePartEntity with id=$id.\n ${ex.message}\n${ex.cause}")
            throw UnavailableTechnicalException(ex.stackTraceToString())
        }
    }

    override fun addCake(dto: CakeBusinessDto) {
        val entity: CakeEntity

        try {
            entity = cakeConverter.convert(dto)
        } catch (ex: Exception) {
            logger.error("cant convert Cake business dto to Entity.\n${ex.message}\n${ex.cause}")
            throw UnavailableTechnicalException(ex.stackTraceToString())
        }

        logger.debug("Entity Cake before save: $entity")
        try {
            cakeRepository.save(entity)
        } catch (ex: DataAccessException) {
            logger.error("Cant save CakeEntity to database.\n${ex.message}\n${ex.cause}")
            throw UnavailableTechnicalException(ex.stackTraceToString())
        }
    }

    override fun deleteCake(id: Long) {
        logger.debug("deleting cake with id=$id")

        try {
            cakeRepository.delete(cakeRepository.getCakeById(id))
        } catch (ex: DataAccessException) {
            logger.error("Cant delete cake with id=$id.\n ${ex.message}\n${ex.cause}")
            throw UnavailableTechnicalException(ex.stackTraceToString())
        }
    }
}