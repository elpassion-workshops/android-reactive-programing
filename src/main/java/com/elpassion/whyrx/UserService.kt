package com.elpassion.whyrx

class UserCallbackService(
        val api: UserCallbackApi,
        val view: UserDetailsView) {

    fun getUserDetails(userId: String) {
        api.getUser(userId, object : Callback<User> {
            override fun onSuccess(user: User) {
                view.showUserDetails(newUserDetails(user))
            }

            override fun onFailure(t: Throwable) {
                view.showError()
            }
        })
    }
}

class UserRxService(
        val api: UserRxApi,
        val view: UserDetailsView) {

    fun getUserDetails(userId: String) {
        api.getUser(userId)
                .subscribe({
                    view.showUserDetails(newUserDetails(it))
                }, {
                    view.showError()
                })
    }
}

private fun newUserDetails(user: User) = UserDetails(user.name, user.friendIds.size)
