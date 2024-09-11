package com.aliza.alizaShop.utils

class NotFoundException(message: String) : RuntimeException(message)
class JwtTokenException(message: String) : Exception(message)