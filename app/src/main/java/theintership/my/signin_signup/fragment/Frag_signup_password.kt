package theintership.my.signin_signup.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import theintership.my.activity.MainActivity
import theintership.my.all_class.MyMethod.Companion.hide_soft_key_board
import theintership.my.all_class.MyMethod.Companion.replacefrag
import theintership.my.R
import theintership.my.databinding.FragSignupPasswordBinding
import theintership.my.activity.Signup1Activity
import theintership.my.signin_signup.dialog.dialog_stop_signup
import theintership.my.signin_signup.shareViewModel

class frag_signup_password : Fragment(R.layout.frag_signup_password) {

    private var _binding: FragSignupPasswordBinding? = null
    private val binding get() = _binding!!
    private lateinit var signup1Activity: Signup1Activity
    private val viewmodel: shareViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragSignupPasswordBinding.inflate(inflater, container, false)
        signup1Activity = activity as Signup1Activity


        binding.btnSignupPasswordGo.setOnClickListener {
            val password = binding.edtSignupPassword.text.toString()
            hide_soft_key_board(signup1Activity , binding.btnSignupPasswordGo)
            if (!valid_password(password)) {
                val s = "Your password must has at least 6 characters " +
                        "number or symbol (! and %)."
                set_error_edittext(s)
                return@setOnClickListener
            }

            if (is_same_password(password)) {
                val s = "Please chose a more secure password. " +
                        "It should be longer than 6 characters " +
                        "unique to you, and difficult to other to guess."
                set_error_edittext(s)
                return@setOnClickListener
            }

            move_error_edittext()
            goto_frag_signup_done(password)
        }

        binding.edtSignupPassword.setOnEditorActionListener { textView, i, keyEvent ->
            val password = binding.edtSignupPassword.text.toString()
            hide_soft_key_board(signup1Activity , binding.edtSignupPassword)
            if (!valid_password(password)) {
                val s = "Your password must has at least 6 characters " +
                        "number or symbol (like ! and % ) or space."
                set_error_edittext(s)
                false
            } else if (is_same_password(password)) {
                val s = "Please chose a mode secure password. " +
                        "It should be longer than 6 characters " +
                        "unique to you, and difficult to other to guess."
                set_error_edittext(s)
                false
            } else {
                move_error_edittext()
                goto_frag_signup_done(password)
                true
            }
        }

        binding.btnSignupPaswordBack.setOnClickListener {
            hide_soft_key_board(signup1Activity , binding.btnSignupPaswordBack)
            val dialog = dialog_stop_signup(signup1Activity)
            dialog.show()
            dialog.btn_cancel.setOnClickListener {
                startActivity(Intent(activity, MainActivity::class.java))
                activity?.overridePendingTransition(
                    R.anim.slide_in_left,
                    R.anim.slide_out_right
                )
                activity?.finish()
                dialog.dismiss()
            }
        }

        return binding.root
    }

    fun valid_password(password: String): Boolean {
        if (password.length < 6){
            return false
        }
        for (i in 0 until password.length) {
            if (password[i] in 'a'..'z' || password[i] in 'A'..'Z' || password[i] in '0'..'9'
                || password[i] == '!' || password[i] == '%' || password[i] == ' ') {
                continue
            } else {
                return false
            }
        }
        return true
    }

    fun is_same_password(password: String): Boolean {
        for (i in 1 until password.length) {
            if (password[i] != password[i - 1]) {
                return false
            }
        }
        return true
    }

    fun set_error_edittext(s: String) {
        binding.layoutEdtSignupPassword.isErrorEnabled = true
        binding.layoutEdtSignupPassword.error = "ok"
        binding.layoutEdtSignupPassword.errorIconDrawable = null
        binding.tvSignupPasswordInfo.text = s
        binding.tvSignupPasswordInfo.setTextColor(resources.getColor(R.color.error, null))
    }

    fun goto_frag_signup_done(password: String) {
        replacefrag(
            "frag_signup_done",
            frag_signup_done(),
            signup1Activity.supportFragmentManager
        )
        viewmodel.password_user = password
    }

    fun move_error_edittext() {
        binding.tvSignupPasswordInfo.text =
            "Your password must has at least 6 characters. " +
                    "It should be something other couldn't guess."
        binding.tvSignupPasswordInfo.setTextColor(resources.getColor(R.color.light_blue, null))
    }

}