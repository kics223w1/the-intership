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
import theintership.my.databinding.FragSignupEmailBinding
import theintership.my.activity.Signup1Activity
import theintership.my.signin_signup.dialog.dialog_stop_signup
import theintership.my.signin_signup.shareViewModel


class frag_signup_email : Fragment(R.layout.frag_signup_email) {

    private var _binding: FragSignupEmailBinding? = null
    private val binding get() = _binding!!
    private lateinit var signup1activity: Signup1Activity
    private val shareViewModel: shareViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragSignupEmailBinding.inflate(inflater, container, false)
        signup1activity = activity as Signup1Activity

        binding.btnSignupEmailBack.setOnClickListener {
            signup1activity.supportFragmentManager.popBackStack()
        }

        binding.btnSignupEmailGo.setOnClickListener {
            val email = binding.edtSignupEmail.text.toString()
            hide_soft_key_board(signup1activity, binding.btnSignupEmailGo)

            if (!check_email(email)) {
                return@setOnClickListener
            }
            goto_frag_account(email)
        }

        binding.edtSignupEmail.setOnEditorActionListener { textview, i, keyevent ->
            val email = binding.edtSignupEmail.text.toString()
            hide_soft_key_board(signup1activity, binding.edtSignupEmail)
            if (!check_email(email)) {
                false
            } else {
                goto_frag_account(email)
                true
            }
        }

        binding.btnSignupEmailBack.setOnClickListener {
            hide_soft_key_board(signup1activity, binding.btnSignupEmailBack)
            val dialog = dialog_stop_signup(signup1activity)
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

    private fun check_email(email: String): Boolean {
        if (email == "") {
            set_error_text_view("Please enter email")
            return false
        }
        if (!email.contains("@gmail.com")) {
            set_error_text_view("Email must be the format @gmail.com\n Example: huyhuy@gmail.com")
            return false
        }
        //if email has @gmail.com so it length is >= 10
        //Make sure email.length >= 10 for continuing
        val check_fomat = email.subSequence(email.length - 10, email.length)
        if (check_fomat != "@gmail.com") {
            set_error_text_view("Email must be the format @gmail.com\n Example: huyhuy@gmail.com")
            return false
        }
        for (i in 0 until email.length - 10) {
            val it = email[i]
            if (it !in 'a'..'z' && it !in 'A'..'Z' && it !in '0'..'9') {
                set_error_text_view("Email can not contain $it \nor space , just contain characters")
                return false
            }
        }
        val list_email_address = shareViewModel.list_email_address
        if (list_email_address.contains(email)) {
            set_error_text_view("Email address already use by another user")
            return false
        }
        move_error_text_view()
        return true
    }

    private fun goto_frag_account(email: String) {
        shareViewModel.set_user_info_email(email = email)
        replacefrag(
            "frag_signup_account",
            frag_signup_account(),
            signup1activity.supportFragmentManager
        )
    }

    private fun set_error_text_view(str: String) {
        binding.tvSignupEmailInfo.text = str
        binding.tvSignupEmailInfo.setTextColor(resources.getColor(R.color.error, null))
    }

    private fun move_error_text_view() {
        binding.tvSignupEmailInfo.text =
            "Adding an email to keep your account secure, find friends and more"
        binding.tvSignupEmailInfo.setTextColor(resources.getColor(R.color.light_grey, null))
    }

}