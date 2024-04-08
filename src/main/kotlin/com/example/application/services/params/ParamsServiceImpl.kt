package com.example.application.services.params

import org.springframework.stereotype.Service

@Service
class ParamsServiceImpl: ParamsService {
    override fun isTechBreakEnabled(): Boolean {
        return false
    }
}