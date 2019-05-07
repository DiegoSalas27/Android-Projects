package pe.com.ormdemo

import com.orm.SugarRecord

data class Gender(
    val name: String
) : SugarRecord<Gender>() {
    constructor() : this("")
    fun getPeople(): MutableList<Person>? {
        return find(Person::class.java, "gender = ?", this.getId().toString())
    }
}