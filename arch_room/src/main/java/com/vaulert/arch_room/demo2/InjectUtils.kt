package com.vaulert.arch_room.demo2

import android.content.Context

object InjectUtils {

    fun userViewModelFactory(context: Context) =
//        MainViewModelFactory(MainRepository(UsersDatabase.getInstance(context)))
        UserViewModelFactory(UserRepository.getInstance(UsersDatabase.getInstance(context)))

}