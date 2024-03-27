package com.example.application.dto.order

import com.example.application.dto.cake.CakeBusinessDto
import com.example.application.dto.product.ProductBusinessDto
import com.example.application.dto.user.UserBusinessDto
import com.example.application.enumerations.OrderStatusCode
import org.springframework.stereotype.Component
import java.util.Date

@Component
class OrderBusinessDtoImpl(
    private var orderId: String = "",
    private var dateCreate: Date = Date(),
    private var dateExpiry: Date = Date(),
    private var addressToSend: String = "",
    private var description: String = "",
    private var statusCode: OrderStatusCode = OrderStatusCode.PREPARE,
    private var user: UserBusinessDto,
    private var products: List<ProductBusinessDto>,
    private var cakes: List<CakeBusinessDto>
): OrderBusinessDto {
    override fun getOrderId() = orderId
    override fun getUser() = user
    override fun getDateCreate() = dateCreate
    override fun getDateExpiry() = dateExpiry
    override fun getAddressToSend() = addressToSend
    override fun getDescription() = description
    override fun getStatusCode() = statusCode
    override fun getProducts() = products
    override fun getCakes() = cakes
}