package theintership.my.all_class

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import theintership.my.R
import java.lang.Math.ceil
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.Temporal
import java.util.*

class MyMethod {
    companion object {
        fun String.showToastShort(context: Context) {
            Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
        }

        fun String.showToastLong(context: Context) {
            Toast.makeText(context, this, Toast.LENGTH_LONG).show()
        }


        fun blackout_char(text: String, list: MutableList<String>): SpannableString {
            var span = SpannableString(text)
            for (word in list) {
                val startIndex = text.indexOf(word)
                val endIndex = startIndex + word.length
                if (startIndex != -1) {
                    span.setSpan(
                        StyleSpan(Typeface.BOLD),
                        startIndex,
                        endIndex,
                        Spannable.SPAN_INCLUSIVE_INCLUSIVE
                    )
                }
            }
            return span
        }

        fun isWifi(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {

                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {

                    return true
                }
            }
            return false
        }

        fun check_wifi(context: Context): Boolean {
            if (!isWifi(context)) {
                val s = "Please connect wifi to continue"
                s.showToastLong(context)
                return false
            }
            return true
        }

        fun replacefrag(tag: String, frag: Fragment, fm: FragmentManager) {
            fm.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .addToBackStack(tag)
                .replace(R.id.layout_Signup1Activity, frag)
                .commit()
        }

        fun replacefrag_in_main_interface_with_slide_in_left(
            tag: String,
            frag: Fragment,
            fm: FragmentManager
        ) {
            fm.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                .addToBackStack(tag)
                .replace(R.id.layout_main_interface, frag)
                .commit()
        }

        fun not_implement(context: Context) {
            val s = "Not implement"
            s.showToastShort(context)
        }

        fun replacefrag_in_main_interface(tag: String, frag: Fragment, fm: FragmentManager) {
            fm.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .addToBackStack(tag)
                .replace(R.id.layout_main_interface, frag)
                .commit()
        }

        fun replacefrag_in_main_interface_with_bundle(
            tag: String,
            frag: Fragment,
            fm: FragmentManager,
            arg: Bundle
        ) {
            if (!arg.isEmpty) {
                frag.arguments = arg
            }
            fm.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .addToBackStack(tag)
                .replace(R.id.layout_main_interface, frag)
                .commit()
        }

        fun addfrag(tag: String, frag: Fragment, fm: FragmentManager) {
            fm.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .addToBackStack(tag)
                .add(R.id.layout_Signup1Activity, frag, tag)
                .commit()
        }

        fun replacefrag_by_silde_in_left(tag: String, frag: Fragment, fm: FragmentManager) {
            fm.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                .addToBackStack(tag)
                .replace(R.id.layout_Signup1Activity, frag)
                .commit()
        }

        fun hide_soft_key_board(context: Context, view: View) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }

        fun set_up_image_by_glide(link: String, img: ImageView, context: Context) {
            Glide.with(context).load(link).placeholder(R.drawable.icon_loading_image).dontAnimate()
                .error(R.drawable.error_image).into(img)
        }

        fun set_today(): String {
            var today = Calendar.getInstance().time
            val DateFormat = SimpleDateFormat("MM/dd/yyyy")
            return DateFormat.format(today)
        }

        fun get_hour(): String {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("HH")
            return formatter.format(current).toString()
        }

        fun get_minutes(): String {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("mm")
            return formatter.format(current).toString()
        }

        fun get_full(): String {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("HH//mm/ss")
            return formatter.format(current).toString()
        }


        fun get_AM_or_PM(): String {
            val hour = get_hour()
            if (hour in "0".."12") {
                return "AM"
            } else {
                return "PM"
            }
        }

        fun get_day_of_week(): String {
            val today = Calendar.getInstance()
            var day = ""
            when (today.get(Calendar.DAY_OF_WEEK)) {
                Calendar.MONDAY -> day = "Monday"
                Calendar.TUESDAY -> day = "Tuesday"
                Calendar.WEDNESDAY -> day = "Wednesday"
                Calendar.THURSDAY -> day = "Thursday"
                Calendar.FRIDAY -> day = "Friday"
                Calendar.SATURDAY -> day = "Saturday"
                Calendar.SUNDAY -> day = "Sunday"
            }
            return day
        }

        fun count_days(start: String, end: String): Int {
            val DateFormat = SimpleDateFormat("MM/dd/yyyy")

            val start_day = DateFormat.parse(start)
            val end_day = DateFormat.parse(end)

            val mDifference = kotlin.math.abs(start_day.time - end_day.time)

            val DifferenceDates = mDifference / (24 * 60 * 60 * 1000)
            return DifferenceDates.toInt()
        }

        fun count_hour(start: String): Int {
            var hour_start = start
            if (start[0] == '0') {
                hour_start = start[1].toString()
            }
            var current_hour = get_hour()
            if (current_hour[0] == '0') {
                current_hour = current_hour[1].toString()
            }
            val s1 = hour_start.toInt()
            val s2 = current_hour.toInt()
            if (s2 - s1 <= 0) {
                return 1
            }
            return s2 - s1
        }

        fun Context.copyToClipboard(text: CharSequence) {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", text)
            clipboard.setPrimaryClip(clip)
        }

        fun setup_ref_chat_between_2_person(from: String, to: String): String {
            if (from.length > to.length) {
                return "$from $to"
            }
            if (to.length > from.length) {
                return "$to $from"
            }
            for (i in 0 until from.length) {
                if (from[i] > to[i]) {
                    return "$from $to"
                }
                if (to[i] > from[i]) {
                    return "$to $from"
                }
            }
            //Account ref is unique , so code will never go to this.
            return ""
        }

    }
}