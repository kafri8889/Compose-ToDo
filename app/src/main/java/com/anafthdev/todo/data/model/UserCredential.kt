package com.anafthdev.todo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

/**
 * Class ini digunakan untuk menyimpan informasi login pengguna
 */
@Parcelize
@Serializable
data class UserCredential(
    val id: String,
    val name: String,
    val email: String,
    val password: String
): Parcelable, java.io.Serializable {

    /**
     * return true if all value not empty, false otherwise
     */
    val allNotEmpty: Boolean
        get() = isLoggedIn

    /**
     * return true if all value empty, false otherwise
     */
    val allEmpty: Boolean
        get() = id.isBlank() &&
                name.isBlank() &&
                email.isBlank() &&
                password.isBlank()

    val isLoggedIn: Boolean
        get() = id.isNotBlank() &&
                name.isNotBlank() &&
                email.isNotBlank() &&
                password.isNotBlank()

}
