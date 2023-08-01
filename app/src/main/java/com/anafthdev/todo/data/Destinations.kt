package com.anafthdev.todo.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NamedNavArgument

object DestinationRoute {
    const val SIGN_IN = "sign_up"
    const val SIGN_UP = "sign_in"
}

/**
 * Key for argument
 */
object DestinationArgument {

}

data class TopLevelDestination(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList(),
    @StringRes val name: Int? = null,
    @DrawableRes val icon: Int? = null
) {
    /**
     * @param value {key: value}
     */
    fun createRoute(vararg value: Pair<Any, Any?>): TopLevelDestination {
        var mRoute = route

        value.forEach { (key, value) ->
            mRoute = mRoute.replace("{$key}", value.toString())
        }

        return TopLevelDestination(mRoute, arguments)
    }
}

object TopLevelDestinations {

    object SignInOrSignUp {
        const val ROUTE = "sign_in_or_sign_up"

        val signIn = TopLevelDestination(
            route = DestinationRoute.SIGN_IN
        )

        val signUp = TopLevelDestination(
            route = DestinationRoute.SIGN_UP
        )
    }

}