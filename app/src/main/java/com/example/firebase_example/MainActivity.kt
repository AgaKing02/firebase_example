package com.example.firebase_example

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")

//        var James = Person("James", 30)
        var employee=Employee("James Bond","Android developer?XD",
            "123 South Street",51)
        myRef.setValue(employee)


//        myRef.setValue("Hello, From Android x Kotlin!")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.value as HashMap<String,Any>
                Log.d("VALUE: ", value.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("ERROR: ", error.message)
            }

        })
    }

    data class Employee(
        var name: String, var position: String,
        var homeAddress: String, var age: Int
    )
}