package com.example.application.converters.order.orderEntityAndBusinessConverter

import com.example.application.converters.cake.cakeEntityAndBusinessConverter.CakeEntityAndBusinessConverter
import com.example.application.converters.product.productEntityAndBusinessConverter.ProductEntityAndBusinessConverter
import com.example.application.converters.user.userEntityAndBusinessConverter.UserEntityAndBusinessConverter
import com.example.application.dto.order.OrderBusinessDto
import com.example.application.dto.order.OrderBusinessDtoImpl
import com.example.application.entity.order.OrderEntity
import com.example.application.enumerations.OrderStatusCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.sql.Date
import java.sql.Timestamp

@Component
class OrderEntityAndBusinessConverterImpl : OrderEntityAndBusinessConverter {
    @Autowired
    private lateinit var userConverter: UserEntityAndBusinessConverter

    @Autowired
    private lateinit var productConverter: ProductEntityAndBusinessConverter

    @Autowired
    private lateinit var cakeConverter: CakeEntityAndBusinessConverter

    override fun convert(dto: OrderBusinessDto): OrderEntity {
        return OrderEntity(
            id = dto.getOrderId(),
            user = userConverter.convert(dto.getUser()),
            products = dto.getProducts().map { productConverter.convert(it) },
            customCakes = dto.getCakes().map { cakeConverter.convert(it) },
            dateCreate = Timestamp(dto.getDateCreate().time),
            dateExpiry = Date(dto.getDateExpiry().time),
            addressToSend = dto.getAddressToSend(),
            description = dto.getDescription(),
            statusCode = dto.getStatusCode().statusCode,
        )
    }

    override fun convert(dto: OrderEntity): OrderBusinessDto {
        return OrderBusinessDtoImpl(
            orderId = dto.id,
            user = userConverter.convert(dto.user),
            products = dto.products.map { productConverter.convert(it) },
            cakes = dto.customCakes.map { cakeConverter.convert(it) },
            dateCreate = dto.dateCreate,
            dateExpiry = Date(dto.dateExpiry?.time ?: 0),
            addressToSend = dto.addressToSend,
            description = dto.description ?: "",
            statusCode = OrderStatusCode.getStatusByCode(dto.statusCode),
        )
    }
}
