package theintership.my.main_interface.notifications.viewModel

import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import theintership.my.all_class.Dowload_image_from_Firebase_by_dowloadURL
import theintership.my.all_class.MyMethod.Companion.count_days
import theintership.my.all_class.MyMethod.Companion.set_today
import theintership.my.main_interface.notifications.model.Notifications
import java.net.URI
import java.net.URL


class ViewModelFragNotifications() : ViewModel() {
    val new_notis_live_data: MutableLiveData<MutableList<Notifications>> =
        MutableLiveData()
    val earlier_notis_live_data: MutableLiveData<MutableList<Notifications>> =
        MutableLiveData()
    private val new_notis: MutableList<Notifications> = mutableListOf()
    private val earlier_notis: MutableList<Notifications> = mutableListOf()
    private val database = Firebase.database.reference

    fun submitList_NewNoti_LiveData() {
        new_notis_live_data.value = new_notis
    }

    fun submitList_EarlierNoti_LiveData() {
        earlier_notis_live_data.value = earlier_notis
    }

    fun getList_NewNoti_LiveData(): MutableLiveData<MutableList<Notifications>> {
        return new_notis_live_data
    }

    fun getList_OldNoti_LiveData(): MutableLiveData<MutableList<Notifications>> {
        return earlier_notis_live_data
    }

    fun setup_noti(snapshot: DataSnapshot): Notifications {
        val it = snapshot
        val to_person = it.child("to_person").getValue().toString()
        val _readed = it.child("_readed").getValue().toString()
        val content = it.child("content").getValue().toString()
        val day_create = it.child("day_create").getValue().toString()
        val group = it.child("group").getValue().toString()
        val icon = it.child("icon").getValue().toString()
        val kind_of_noti = it.child("kind_of_noti").getValue().toString()
        val link_avatar_person = it.child("link_avatar_person").getValue().toString()
        val from_person = it.child("from_person").getValue().toString()
        val day_and_time = it.child("day_and_time").getValue().toString()
        val link_post = it.child("link_post").getValue().toString()
        val mId_comment = it.child("id_comment").getValue().toString()
        var id_comment = -1
        if (!mId_comment.isEmpty()) {
            id_comment = mId_comment.toInt()
        }
        val is_readed = if (_readed == "true") true else false
        val noti = Notifications(
            to_person = to_person,
            day_create = day_create,
            from_person = from_person,
            day_and_time = day_and_time,
            content = content,
            kind_of_noti = kind_of_noti,
            group = group,
            icon = icon,
            link_post = link_post,
            id_comment = id_comment,
            link_avatar_person = link_avatar_person,
            is_readed = is_readed
        )
        return noti
    }

}








