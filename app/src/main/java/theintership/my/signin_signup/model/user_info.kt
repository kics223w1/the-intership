package theintership.my.signin_signup.model

import androidx.room.PrimaryKey

data class user_info(
    @PrimaryKey(autoGenerate = true)
    var fullname : String? = null ,
    var firstname : String? = null,
    var lastname : String? = null,
    var email : String? = null,
    var sex : String? = null ,
    var pronoun : String? = null,
    var create_at : String? = null,
    var last_login : String? = null,
    var gender : String? = null,
    var age : Int,
    var birthday : String? = null,
    var verify_email : Boolean,
) {

}