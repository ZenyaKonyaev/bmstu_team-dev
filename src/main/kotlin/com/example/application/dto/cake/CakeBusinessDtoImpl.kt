package com.example.application.dto.cake

import com.example.application.dto.cakePart.CakePartBusinessDto
import org.springframework.stereotype.Component

/**
 * Реализация CakeBusinessDto
 */
@Component
class CakeBusinessDtoImpl(
    private var id: Long = 0L,
    private var base: CakePartBusinessDto,
    private var filling: CakePartBusinessDto,
    private var cream: CakePartBusinessDto,
) : CakeBusinessDto {
    override fun getId() = id

    override fun getBase() = base

    override fun getCream() = cream

    override fun getFilling() = filling
}
