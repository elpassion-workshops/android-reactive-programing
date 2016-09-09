package com.elpassion.whyrx

import rx.Observable

class UserCallbackService(
        val api: UserCallbackApi,
        val view: UserDetailsView) {

    fun getUserDetails(userId: String) {
        api.getUser(userId, object : Callback<User> {
            override fun onSuccess(user: User) {
                onUserResponse(user)
            }

            override fun onFailure(t: Throwable) {
                view.showError()
            }
        })
    }

    private fun onUserResponse(user: User) {
        var finishedCalls = 0
        var realFriends = 0
        user.friendIds.forEach { friendId ->
            api.getUser(friendId, object : Callback<User> {
                override fun onSuccess(friend: User) {
                    if (friend.friendIds.contains(user.id)) {
                        realFriends++
                    }
                    if (++finishedCalls == user.friendIds.size) {
                        view.showUserDetails(newUserDetails(user, realFriends))
                    }
                }

                override fun onFailure(t: Throwable) {
                    view.showError()
                }
            })
        }
    }
}

class UserRxService(
        val api: UserRxApi,
        val view: UserDetailsView) {

    fun getUserDetails(userId: String) {
        api.getUser(userId)
                .flatMap { zipWithRealFriendsCount(it) }
                .subscribe({
                    view.showUserDetails(it)
                }, {
                    view.showError()
                })
    }

    private fun zipWithRealFriendsCount(user: User): Observable<UserDetails> {
        return Observable.zip(
                Observable.just(user),
                getRealFriendsCount(user),
                { user: User, realFriends: Int -> newUserDetails(user, realFriends) })
    }

   private fun getRealFriendsCount(user: User): Observable<Int> {
        return Observable.from(user.friendIds)
                .flatMap { api.getUser(it) }
                .filter { it.friendIds.contains(user.id) }
                .count()
    }
}

private fun newUserDetails(user: User, realFriends: Int) = UserDetails(user.name, user.friendIds.size, realFriends)
