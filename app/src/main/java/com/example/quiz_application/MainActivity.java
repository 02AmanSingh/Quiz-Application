package com.example.quiz_application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    TextView totalQuesnTV;
    TextView quesnTV;
    Button AnsA,AnsB,AnsC,AnsD;
    Button SubmitBtn;

    int score=0;
    int totalQuesn=Question_Answer.question.length;
    int currentQuesnIndex=0;
    String selectedAns="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuesnTV =findViewById(R.id.Total_question);
        quesnTV =findViewById(R.id.Question);
        AnsA =findViewById(R.id.A);
        AnsB =findViewById(R.id.B);
        AnsC =findViewById(R.id.C);
        AnsD =findViewById(R.id.D);
        SubmitBtn=findViewById(R.id.Submit_btn);

        AnsA.setOnClickListener(this);
        AnsB.setOnClickListener(this);
        AnsC.setOnClickListener(this);
        AnsD.setOnClickListener(this);
        SubmitBtn.setOnClickListener(this);
        
        totalQuesnTV.setText("Total Question:"+totalQuesn);
        
        loadNewQuestion();
    }


    @Override
    public void onClick(View view) {

        AnsA.setBackgroundColor(Color.WHITE);
        AnsB.setBackgroundColor(Color.WHITE);
        AnsC.setBackgroundColor(Color.WHITE);
        AnsD.setBackgroundColor(Color.WHITE);

        Button clickedBtn=(Button) view;
        if(clickedBtn.getId()==R.id.Submit_btn){
            if(selectedAns.equals(Question_Answer.correctAnswer[currentQuesnIndex])){
                score++;
            }
            currentQuesnIndex++;
            loadNewQuestion();

        }
        else{
            selectedAns=clickedBtn.getText().toString();
            clickedBtn.setBackgroundColor(Color.RED);
        }

    }
    void loadNewQuestion(){
        if(currentQuesnIndex==totalQuesn){
            finishQuiz();
            return;
        }
        quesnTV.setText(Question_Answer.question[currentQuesnIndex]);
        AnsA.setText(Question_Answer.choices[currentQuesnIndex][0]);
        AnsB.setText(Question_Answer.choices[currentQuesnIndex][1]);
        AnsC.setText(Question_Answer.choices[currentQuesnIndex][2]);
        AnsD.setText(Question_Answer.choices[currentQuesnIndex][3]);
    }

    void finishQuiz(){
        String PassStatus="";
        if(score>totalQuesn*0.60){
            PassStatus="Yayy U Passed it..";
        }
        else {
            PassStatus = "Sorry,Try Again..";
        }

        new AlertDialog.Builder(this)
                .setTitle(PassStatus)
                .setMessage("Score is"+score+"out of"+totalQuesn)
                .setPositiveButton("Restart",(dialogInterface,i)-> restartQuiz())
                //.setNegativeButton("End",(dialogInterface,k) -> endQuiz())
                .setCancelable(false)
                .show();
    }
    void restartQuiz(){
        score=0;
        currentQuesnIndex=0;
        loadNewQuestion();
    }
}