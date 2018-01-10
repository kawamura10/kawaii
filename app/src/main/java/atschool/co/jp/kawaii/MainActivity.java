package atschool.co.jp.kawaii;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    //●問目
    TextView countTextView;
    //問題文
    TextView contentTextView;
    //選択ボタン
    Button[] optionButtons;

    //int z;


    //List
    List<Quiz> quizList;

    public int[][] a = new int[10][5];
    public int[][] test_a = new int[10][5];

    //問題合計数
    private Common Total_Q;
    public int total_q;

    //問題数（第●問目）
    private Common CountNumber;
    public int countnumber;

    //問題番号
    private Common QuestionNumber;
    public int questionNumber;

    //問題番号
    private Common History;
    public int history[] = new int[100];

    //問題番号
    private Common Temp;
    public int temp;

    private Common Opt1;
    public int opt1;

    private Common Opt2;
    public int opt2;

    private Common Opt3;
    public int opt3;


    private Common Ans1;
    public String ans1;

    private Common Ans2;
    public String ans2;

    private Common Ans3;
    public String ans3;

    private Common S_Opt1;
    public String s_opt1;

    private Common S_Opt2;
    public String s_opt2;

    private Common S_Opt3;
    public String s_opt3;



    //得点
    private Common Point;
    public int point;

    //正解・不正解
    private Common Chk_Ans;
    public boolean chk_ans;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Total_Q = (Common) this.getApplication();


        QuestionNumber = (Common) this.getApplication();
        questionNumber = QuestionNumber.getQuestionNumner();

        CountNumber = (Common) this.getApplication();
        countnumber = CountNumber.getCountNumber();



        //History = (Common) this.getApplication();

        //Temp = (Common) this.getApplication();


        Point = (Common) this.getApplication();



        Opt1 = (Common) this.getApplication();
        Opt2 = (Common) this.getApplication();
        Opt3 = (Common) this.getApplication();

        Ans1 = (Common) this.getApplication();
        Ans2 = (Common) this.getApplication();
        Ans3 = (Common) this.getApplication();

        S_Opt1 = (Common) this.getApplication();
        S_Opt2 = (Common) this.getApplication();
        S_Opt3 = (Common) this.getApplication();

        Chk_Ans = (Common) this.getApplication();



        //②初期化
        countTextView = findViewById(R.id.countTextView);
        contentTextView = findViewById(R.id.contentTextView);

        optionButtons = new Button[3];
        optionButtons[0] = findViewById(R.id.optionButton1);
        optionButtons[1] = findViewById(R.id.optionButton2);
        optionButtons[2] = findViewById(R.id.optionButton3);


        for (Button button : optionButtons) {
            button.setOnClickListener(this);
        }






        FirebaseDatabase database = FirebaseDatabase.getInstance();

        //TOTAL_Q の取得（firebase）
        DatabaseReference a_Total_Q = database.getReference("counts/a_Total_Q");
        a_Total_Q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long a_total_q = (long) dataSnapshot.getValue();


                total_q = (int)a_total_q;



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });

        int questionNumber2 = questionNumber +1;

        DatabaseReference TTref = database.getReference("counts/question" + questionNumber2 + "/option" + 1 + "Count");
        TTref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long ttr = (long) dataSnapshot.getValue();

                opt1 = (int)ttr;
                Opt1.setOpt1(opt1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });

        DatabaseReference TTref2 = database.getReference("counts/question" + questionNumber2 + "/option" + 2 + "Count");
        TTref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long ttr = (long) dataSnapshot.getValue();


                opt2 = (int)ttr;
                Opt2.setOpt2((opt2));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });

        DatabaseReference TTref3 = database.getReference("counts/question" + questionNumber2 + "/option" + 3 + "Count");
        TTref3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long ttr = (long) dataSnapshot.getValue();


                opt3 = (int)ttr;
                Opt3.setOpt3(opt3);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });




        DatabaseReference myRef = database.getReference("counts/question" + 1 );

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {

                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError error) {
                                            // Failed to read value
                                            Log.w("s","Failed to read value.", error.toException());
                                        }
                                    }
        );


/*
        do{
            Total_Q.setTotal_Q(total_q);
        }while (total_q>0);*/



        createQuizList();


        updateQuiz();


        ImageView resultImage = findViewById(R.id.imageView);



        if (questionNumber == 1){

            resultImage.setImageResource(R.mipmap.pct1);
        }

        else if (questionNumber == 2){

            resultImage.setImageResource(R.mipmap.pct2);
        }

        else if (questionNumber == 3){

            resultImage.setImageResource(R.mipmap.pct3);
        }

        else{

        }
    }

   //option1 選択のカウントアップ
    private void submit1() {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("counts/question" + questionNumber + "/option1Count");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long value =(long) dataSnapshot.getValue();
                value = value + 1;
                a[questionNumber][1] = (int)value;
                test_a[1][1] = (int)value;
                opt1 = (int)value;
                Opt1.setOpt1(opt1);



                dataSnapshot.getRef().setValue(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference TTRref = database.getReference("counts/question" + questionNumber + "/option" + 2 + "Count");
        TTRref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long ttrr = (long) dataSnapshot.getValue();
                //ttr = ttr + 1;

                a[questionNumber][2] = (int)ttrr;
                opt2 = (int)ttrr;
                Opt2.setOpt2(opt2);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }


        });

        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference TTRRref = database.getReference("counts/question" + questionNumber + "/option" + 3 + "Count");
        TTRRref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long ttrr = (long) dataSnapshot.getValue();
                //ttr = ttr + 1;

                a[questionNumber][3] = (int)ttrr;
                opt3 = (int)ttrr;
                Opt3.setOpt3(opt3);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }


        });


    }

    //option2 選択のカウントアップ
    private void submit2() {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("counts/question" + questionNumber + "/option2Count");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long value =(long) dataSnapshot.getValue();
                value = value + 1;
                a[questionNumber][2] = (int)value;
                opt2 = (int)value;
                Opt2.setOpt2(opt2);


                dataSnapshot.getRef().setValue(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference TTRref = database.getReference("counts/question" + questionNumber + "/option" + 1 + "Count");
        TTRref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long ttrr = (long) dataSnapshot.getValue();

                a[questionNumber][1] = (int)ttrr;
                opt1 = (int)ttrr;
                Opt1.setOpt1(opt1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }


        });

        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference TTRRref = database.getReference("counts/question" + questionNumber + "/option" + 3 + "Count");
        TTRRref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long ttrr = (long) dataSnapshot.getValue();
                //ttr = ttr + 1;

                a[questionNumber][3] = (int)ttrr;
                opt3 = (int)ttrr;
                Opt3.setOpt3(opt3);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }


        });

    }

    //option３ 選択のカウントアップ
    private void submit3() {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("counts/question" + questionNumber + "/option3Count");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long value =(long) dataSnapshot.getValue();
                value = value + 1;
                a[questionNumber][3] = (int)value;
                opt3 = (int)value;
                Opt3.setOpt3(opt3);

                dataSnapshot.getRef().setValue(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference TTRref = database.getReference("counts/question" + questionNumber + "/option" + 1 + "Count");
        TTRref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long ttrr = (long) dataSnapshot.getValue();

                a[questionNumber][1] = (int)ttrr;
                opt1 = (int)ttrr;
                Opt1.setOpt1(opt1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }


        });

        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference TTRRref = database.getReference("counts/question" + questionNumber + "/option" + 2 + "Count");
        TTRRref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long ttrr = (long) dataSnapshot.getValue();
                //ttr = ttr + 1;

                a[questionNumber][2] = (int)ttrr;
                opt2 = (int)ttrr;
                Opt2.setOpt2(opt2);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });


    }


    //クイズリスト作成
    void createQuizList() {

        //クイズリスト（仮）
        Quiz quiz1 = new Quiz("１番かわいいのは、どれでしょう？", "  りんご（左）", "  キウイ（中）", "  みかん（右）", "");
        Quiz quiz2 = new Quiz("１番かわいいのは、どれでしょう？", "  花柄のポニー（左）", "  縞々のポニー（中）", "  ダイヤのポニー（右）", "");
        Quiz quiz3 = new Quiz("１番かわいいのは、どれでしょう？", "  花火柄のくじら(左)", "  クローバーのくま(中)", "  ハートのライオン(右)", "");
        //Quiz quiz4 = new Quiz("１番かわいいのは、どれでしょう？", "  コーヒー", "  コーラ", "  オレンジジュース", "");

        //
        quizList = new ArrayList<>();

        quizList.add(quiz1);
        quizList.add(quiz1);
        quizList.add(quiz2);
        quizList.add(quiz3);
        //quizList.add(quiz4);


        //temp();
        //detAnswer();

        //Collections.shuffle(quizList);
    }


    //Answer の決定
    public void detAnswer(){

        //for (int i =0; i<2; i++) {

        Quiz quiz = quizList.get(questionNumber);

        if (opt1==opt2&&opt2==opt3){
            ans1 = "";
            Ans1.setAns1(ans1);
            ans2 = "";
            Ans2.setAns2(ans2);
        }

        else if(opt1==opt2&&opt2>opt3){
            ans1 = quiz.option1;
            Ans1.setAns1(ans1);
            ans2 = "と" + quiz.option2;
            Ans2.setAns2(ans2);
        }

        else if(opt2==opt3&&opt3>opt1){
            ans1 = quiz.option2;
            Ans1.setAns1(ans1);
            ans2 = "と" + quiz.option3;
            Ans2.setAns2(ans2);
        }

        else if(opt1==opt3&&opt1>opt2){
            ans1 = quiz.option1;
            Ans1.setAns1(ans1);
            ans2 = "と" + quiz.option3;
            Ans2.setAns2(ans2);
        }



        else if (opt1 < opt2) {
            a[questionNumber][0] = a[questionNumber][2];
            quiz.answer = quiz.option2;
            ans1 = quiz.option2;
            Ans1.setAns1(ans1);
            ans2 = "";
            Ans2.setAns2(ans2);


            if (opt2 < opt3) {

                a[questionNumber][0] = a[questionNumber][3];
                quiz.answer = quiz.option3;
                ans1 = quiz.option3;
                Ans1.setAns1(ans1);
                ans2 = "";
                Ans2.setAns2(ans2);

            }

        } else {
            a[questionNumber][0] = a[questionNumber][1];
            quiz.answer = quiz.option1;
            ans1 = quiz.option1;
            Ans1.setAns1(ans1);
            ans2 = "";
            Ans2.setAns2(ans2);


            if (opt1 < opt3) {

                a[questionNumber][0] = a[questionNumber][3];
                quiz.answer = quiz.option3;
                ans1 = quiz.option3;
                Ans1.setAns1(ans1);
                ans2 = "";
                Ans2.setAns2(ans2);

            }
        }


    }


   //クイズの決定
/*    void selectQuestion(){



        //int history[] = new int [total_q];

        //history[1] = 2;

        boolean flag = false;

        total_q = 3;


        history[countnumber] = (int)(Math.random() * total_q + 1);


        do {
            flag = false;
            for (int i = countnumber -1; i >= 1; i--) { //本当は、　int i = countnumber -1;

                temp = i;

                Temp.setTemp(temp);


                history[i] = History.getHistory_Q();



                if (history[countnumber] == history[i]) {
                    flag = true;
                    history[countnumber] = (int) (Math.random() * total_q + 1);

                }
            }


        } while (flag == true);


        temp = countnumber;

        Temp.setTemp(temp);

        //History[countnumber] = (Common) this.getApplication();

        History.setHistory_Q(history[countnumber]);

        questionNumber = history[countnumber];


    }*/



    //クイズの表示
    void showQuiz() {
        countTextView.setText((countnumber) + "問目");

        Quiz quiz = quizList.get(questionNumber);
        contentTextView.setText(quiz.content);
        optionButtons[0].setText(quiz.option1);
        optionButtons[1].setText(quiz.option2);
        optionButtons[2].setText(quiz.option3);

    }



    //次のクイズにアップデート
    void updateQuiz() {
        //submit();

        countnumber++;
        questionNumber++;
        //selectQuestion();
        QuestionNumber.setQuestionNumber(questionNumber);
        CountNumber.setCountNumber(countnumber);

        //if (countnumber < quizList.size()) {
            createQuizList();

            showQuiz();

        //} else {


        //    result();

        //}

    }


    //⑦正解・不正解
    @Override
    public void onClick(View view) {

        Button clickedButton = (Button) view;

        Quiz quiz = quizList.get(questionNumber);

        if (TextUtils.equals(clickedButton.getText(), quiz.option1)) {
            //a[questionNumber][1]++;
            //test_a[questionNumber][1]++;
            submit1();

        } else if (TextUtils.equals(clickedButton.getText(), quiz.option2)) {
            //a[questionNumber][2]++;
            //test_a[questionNumber][2]++;
            submit2();

        } else if (TextUtils.equals(clickedButton.getText(), quiz.option3)) {
            //a[questionNumber][3]++;
            //test_a[questionNumber][3]++;
            submit3();

        } else {

        }

        //temp();
        detAnswer();


        s_opt1 = quiz.option1;
        S_Opt1.setS_Opt1(s_opt1);
        s_opt2 = quiz.option2;
        S_Opt2.setS_Opt2(s_opt2);
        s_opt3 = quiz.option3;
        S_Opt3.setS_Opt3(s_opt3);



        if (TextUtils.equals(clickedButton.getText(), quiz.answer)) {
            chk_ans = true;
            Chk_Ans.setChk_Ans(chk_ans);
            point++;
            Point.setPoint(point);

            Toast.makeText(this, "正解", Toast.LENGTH_SHORT).show();

        }
        else {
            chk_ans = false;
            Chk_Ans.setChk_Ans(chk_ans);
            Toast.makeText(this, "はずれ", Toast.LENGTH_SHORT).show();

        }

        //updateQuiz();
        check(view);


    }

    //SecondActivity(グラフ画面)
    public void check(View view){
        Intent intent = new Intent(this, SecondActivity.class);

        //intentを発行 startActivity()
        startActivity(intent);
    }

    //ResultActivity(最終結果画面)へ
    public void result(){
        Intent intent = new Intent(this, ResultActivity.class);

        //intentを発行 startActivity()
        startActivity(intent);
    }

}
