package br.com.sdr.forum.`interface`

interface Mapper<I, O> {
    fun map(input : I) : O
}
