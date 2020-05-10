package smartherd.com.simpleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val already = findViewById<TextView>(R.id.already_registed)
        already.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnsignup.setOnClickListener {
            val email = tvusername.text.toString()
            val pwd2 = tvpassword.text.toString()


            if (email.isEmpty() || pwd2.isEmpty()) {
                Toast.makeText(this, "please Enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            Log.d("MainActivity", "Email id is : $email")
            Log.d("MainActivity", "Password id is : $pwd2")

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pwd2)
                .addOnCompleteListener {
                    if (!it.isSuccessful) {
                        return@addOnCompleteListener
                    } else if (it.isSuccessful) {
                        Log.d("Main", "Successfully created user with uid: ${it.result?.user?.uid}")
                        Toast.makeText(this, "your are successfully registered", Toast.LENGTH_SHORT)
                            .show()
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to Create", Toast.LENGTH_SHORT).show()
                    Log.d("Main", "Failed to create User")
                }


        }


    }
}