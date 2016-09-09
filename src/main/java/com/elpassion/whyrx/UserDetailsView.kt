package com.elpassion.whyrx

interface UserDetailsView {
    fun showUserDetails(userDetails: UserDetails)

    fun showError()
}