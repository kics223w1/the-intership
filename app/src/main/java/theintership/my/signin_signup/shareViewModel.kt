package theintership.my.signin_signup

import androidx.core.graphics.createBitmap
import androidx.lifecycle.ViewModel
import theintership.my.signin_signup.model.user_info

class shareViewModel : ViewModel() {

    var user_info: user_info = user_info(
        email = "",
        fullname = "",
        sex = "",
        pronoun = "",
        gender = "",
        age = 1,
        birthday = "",
        create_at = "",
        last_login = "",
        lastname = "",
        firstname = "",
        verify_email = false,
    )
    var account_user = ""
    var password_user = ""

    var photo_user  = createBitmap(1000 , 1000)
    var photo_user_null = true
    var image_is_local_or_bitmap = ""
    var image_path_from_local = ""

    var is_email_address_change = false

    var list_account = mutableListOf<String>()
    var list_email_address = mutableListOf<String>()

    fun set_user_info_fullname(fullname: String) {
        user_info.fullname = fullname
    }

    fun set_user_info_lastname(lastname: String) {
        user_info.lastname = lastname
    }

    fun set_user_info_firstname(firstname: String) {
        user_info.firstname = firstname
    }

    fun set_user_info_create_at(create_at: String) {
        user_info.create_at = create_at
    }

    fun set_user_info_email(email: String) {
        user_info.email = email
    }

    fun set_user_info_sex(sex: String) {
        user_info.sex = sex
    }

    fun set_user_info_age(age: Int) {
        user_info.age = age
    }

    fun set_user_info_birthday(birthday: String) {
        user_info.birthday = birthday
    }

    fun set_user_info_pronoun(pronoun: String) {
        user_info.pronoun = pronoun
    }

    fun set_user_info_gender(gender: String) {
        user_info.gender = gender
    }

    fun set_user_verify_email(ok : Boolean){
        user_info.verify_email = ok
    }

}