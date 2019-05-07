package pe.com.ormdemo

import com.orm.SugarRecord

data class Person(
    val name: String,
    val email: String,
    val gender: Gender
) : SugarRecord<Person>() {
    constructor() : this ("", "", Gender())
}