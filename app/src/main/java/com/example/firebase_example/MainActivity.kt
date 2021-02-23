package com.example.firebase_example

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null;
    private var currentUser: FirebaseUser? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")

//        var James = Person("James", 30)
        var employee = Employee(
            "James Bond", "Android developer!!!",
            "123 South Street", 51
        )
//        myRef.setValue(employee)
        mAuth = FirebaseAuth.getInstance();

        mAuth!!.signInWithEmailAndPassword("aga@me.com", "agahan02")
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Signed in Successfull", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Signed in Unsuccessfull", Toast.LENGTH_LONG).show()

                }
            }


//        myRef.setValue("Hello, From Android x Kotlin!")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.value as HashMap<String, Any>
//                Log.d("VALUE: ", value.toString())
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

    override fun onStart() {
        super.onStart()
        currentUser = mAuth!!.currentUser
        if (currentUser != null) {
            Toast.makeText(this, "User is Logged", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "User is Out", Toast.LENGTH_LONG).show()

        }

    }
}