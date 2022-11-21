package com.mirim.hey_dude

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.hey_dude.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private val reference = FirebaseDatabase.getInstance().getReference("users")
private val user = Firebase.auth.currentUser

class Fragment4 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.activity_fragment4, container, false)
        // Inflate the layout for this fragment
        val btnSet = view.findViewById<ImageButton>(R.id.btnSet)
        val txtNickname = view.findViewById<TextView>(R.id.txtNickName)
        val txtEmail = view.findViewById<TextView>(R.id.txtEmail)
        val txtMess = view.findViewById<TextView>(R.id.txtMess)

        user?.let {
            // Name, email address, and profile photo Url
            val uid = user.uid //각 사용자를 구별하는 uid
            reference.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val nickname = snapshot.child("nickname").getValue().toString()
                        txtNickname.setText(nickname)
                        val message = snapshot.child("mess").getValue().toString()
                        txtMess.setText(message)
                    }
                    override fun onCancelled(error: DatabaseError) {}
                }
            )

            val email = user.email
//            val nickname =
            txtEmail.setText(email.toString())
        }
        btnSet.setOnClickListener {
            val intent = Intent(this.context, SettingActivity::class.java)  // 인텐트를 생성해줌,
            startActivity(intent)  // 화면 전환을 시켜줌
//            overridePendingTransition(R.anim.slide_up_enter, R.anim.slide_down_exit);
        }

        return view


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment4().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}