package com.example.application.controller

import com.example.application.config.datasource.DataSourceContextHolder
import com.example.application.config.datasource.DataSourceEnum
import com.example.application.converters.cakePart.cakePartUiAndBusinessConverter.CakePartUiAndBusinessConverter
import com.example.application.converters.product.productUiAndBusinessConverter.ProductUiAndBusinessConverter
import com.example.application.converters.user.userUiAndBusinessConverter.UserUiAndBusinessConverter
import com.example.application.dto.rs.CakesFieldMainPageRs
import com.example.application.dto.rs.MainPageRs
import com.example.application.services.product.ProductService
import com.example.application.services.user.UserService
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView


@RestController
class MainPageController {
    @Autowired
    private lateinit var productService: ProductService

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var productConverter: ProductUiAndBusinessConverter

    @Autowired
    private lateinit var cakePartConverter: CakePartUiAndBusinessConverter

    @Autowired
    private lateinit var userConverter: UserUiAndBusinessConverter

    @Autowired
    private lateinit var dataSourceContextHolder: DataSourceContextHolder

    private val logger = LogManager.getLogger(MainPageController::class.java)

    @GetMapping("/index", "/")
    fun getIndexPageData(
        @AuthenticationPrincipal user: User?
    ): ModelAndView {

        user?.let { dataSourceContextHolder.setContext(DataSourceEnum.DATA_SOURCE_AUTH) } ?:
        dataSourceContextHolder.setContext(DataSourceEnum.DATA_SOURCE_UNKNOWN)

        logger.info(user?.let { "User with login ${user.username} enter to /index" } ?: "Unknown user enter to /index")

         val response = MainPageRs(
            productService.getProductCatalog().map { productConverter.convert(it) },
             CakesFieldMainPageRs(
                productService.getCakeBaseParts().map { cakePartConverter.convert(it) },
                productService.getCakeFillingParts().map { cakePartConverter.convert(it) },
                productService.getCakeCreamParts().map { cakePartConverter.convert(it) }
            ),
             user?.let {
                 userService.getUser(it.username)!!.let { userConverter.convert(it) }
             }
         )

        logger.debug("Returning MainPageResponse: $response")

        dataSourceContextHolder.clearContext()

        return ModelAndView().apply {
            viewName = "home"
            addObject("responseData", response)
        }
    }
}