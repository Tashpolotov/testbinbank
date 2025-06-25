package kg.aza.testbinbank.core

interface DTOMapper<T> {
    fun toDomain(): T
}

fun <T> List<DTOMapper<T>>.toDomain() = map { it.toDomain() }
