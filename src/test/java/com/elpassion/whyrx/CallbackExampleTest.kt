package com.elpassion.whyrx

import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import rx.Observable

class UserServiceTest {

    private val view = mock<UserDetailsView>()

    private val callbackApi = mock<UserCallbackApi>()
    private val callbackService = UserCallbackService(callbackApi, view)

    private val rxApi = mock<UserRxApi>()
    private val rxService = UserRxService(rxApi, view)

    private val friendId = "friendId"
    private val user = User("userId", "User name", listOf(friendId))
    private val friend = User(friendId, "Friend name", listOf())
    private val userDetails = UserDetails("User name", 1)

    @Test
    fun callbackUserServiceShouldShowUserDetailsOnSuccess() {
        callbackApi.stubFor(user, friend)
        callbackService.getUserDetails(user.id)
        verify(view).showUserDetails(userDetails)
    }

    @Test
    fun callbackUserServiceShouldShowErrorOnFailure() {
        callbackApi.stubForError()
        callbackService.getUserDetails(user.id)
        verify(view).showError()
    }

    @Test
    fun rxUserServiceShouldShowUserDetailsOnSuccess() {
        rxApi.stubFor(user, friend)
        rxService.getUserDetails(user.id)
        verify(view).showUserDetails(userDetails)
    }

    @Test
    fun rxkUserServiceShouldShowErrorOnFailure() {
        rxApi.stubForError()
        rxService.getUserDetails(user.id)
        verify(view).showError()
    }
}

fun UserCallbackApi.stubFor(vararg users: User) =
        users.forEach { user ->
            whenever(getUser(eq(user.id), any()))
                    .thenAnswer { it -> (it.arguments[1] as Callback<User>).onSuccess(user) }
        }

fun UserCallbackApi.stubForError() {
    whenever(getUser(any(), any()))
            .thenAnswer { it -> (it.arguments[1] as Callback<*>).onFailure(RuntimeException()) }
}

fun UserRxApi.stubFor(vararg users: User) =
        users.forEach { user ->
            whenever(getUser(user.id)).thenReturn(Observable.just(user))
        }


fun UserRxApi.stubForError() =
        whenever(getUser(any())).thenReturn(Observable.error(RuntimeException()))


