package kr.co.tjoeun.daily10minute_20200719

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_sign_up.*
import kr.co.tjoeun.daily10minute_20200719.utils.ServerUtil
import org.json.JSONObject

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        회원가입 버튼 누르면 -> 빈 입력값이 있는지 검사하고
//        => 괜찮으면 실제로 서버에 가입 요청

        okBtn.setOnClickListener {
            val inputEmail = emailEdt.text.toString()

            if(inputEmail.isEmpty()){
                Toast.makeText(mContext, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()

//                가입절차 강제 종료
                return@setOnClickListener
            }else if(!inputEmail.contains("@")){//inputEmail이 @를 끼고있지 않나요?
//                @가 없다면, 이메일 양식이 아닌걸로 간주하자
                Toast.makeText(mContext,"이메일 양식으로 입력해주세요.", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }

//            이메일 검사는 모두 통과한 상황
//            비번 길이가 8자 이상인지
            val inputPw = pwEdt.text.toString()

            if(inputPw.length < 8){
                Toast.makeText(mContext, "비밀번호가 짧습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            닉네임은 입력 했는지만 검사
            val inputNickName = nickEdt.text.toString()
            if(inputNickName.isEmpty()){
                Toast.makeText(mContext, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            이메일 / 비번 / 닉네임 검사를 모두 통과한 상황
//            서버에 실제로 가입 신청.

            ServerUtil.putRequestSignUp(mContext, inputEmail, inputPw, inputNickName, object : ServerUtil.JsonReponseHandler{
                override fun onResponse(json: JSONObject) {

                }

            })

        }

//        EditText (비번입력칸) 에 글자를 타이핑하는 이벤트 체크

        pwEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                비번 확인 로직 실행
                checkPasswords()
            }

        })
    }

//    비밀번호 + 비밀번호 확인 동시에 검사하는 함수

    fun checkPasswords(){

//        입력한 비밀번호
        val inputPw = pwEdt.text.toString()

//        글자 수 => 0자 : 비밀번호를 입력해주세요.
//        1~7자 : 비밀번호가 너무 짧습니다.
//        8자 이상 : 사용해도 좋은 비밀번호 입니다.

        if (inputPw.isEmpty()) {
            pwCheckResultTxt.text = "비밀번호를 입력해주세요."
        }else if(inputPw.length < 8) {
            pwCheckResultTxt.text = "비밀번호가 너무 짧습니다."
        }else{
            pwCheckResultTxt.text = "사용해도 좋은 비밀번호입니다."
        }
    }

    override fun setValues() {

    }
}