package theintership.my.model

import androidx.room.PrimaryKey

data class limit_auth_phone(
    @PrimaryKey
    val day : String,
    var number: Int
) {
}