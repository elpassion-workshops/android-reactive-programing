package com.elpassion.whyrx

data class User(
        val id: String,
        val name: String,
        val friendIds: List<String>)

data class UserDetails(
        val name: String,
        val friendsCount: Int,
        val realFriends: Int)
