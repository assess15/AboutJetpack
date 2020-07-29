package com.assess15.arch_room.demo2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.assess15.arch_room.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    var name: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.IO) {
            val usersDatabase =
                UsersDatabase.getInstance(this@MainActivity)
            usersDatabase.userDao().insertUser(
                User(
                    "001",
                    "ju",
                    "10"
                )
            )
            usersDatabase.userDao().insertUser(
                User(
                    "002",
                    "ju002",
                    "20"
                )
            )
            usersDatabase.userDao().insertUser(
                User(
                    "003",
                    "ju003",
                    "30"
                )
            )

            val queryUser = usersDatabase.userDao().queryUser()
            queryUser.forEachIndexed { index, it ->
                if (index ==1){
                    name = it.userName
                }
            }

            withContext(Dispatchers.Main) {
                tvName.text = name
            }
        }
    }

}
