package atschool.co.jp.kawaii;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    //問題数（第●問目）
    private Common CountNumber;
    public int countnumber;

    //問題番号
    private Common QuestionNumber;
    public int questionNumber;

    //得点
    private Common Point;
    public int point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        QuestionNumber= (Common) this.getApplication();
        questionNumber = QuestionNumber.getQuestionNumner();

        CountNumber = (Common) this.getApplication();
        countnumber = CountNumber.getCountNumber();

        Point = (Common) this.getApplication();
        point = Point.getPoint();


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("結果");

        if (point == 2) {
            builder.setMessage("" +3+ "問中、" + point + "問正解でした。!!");
        } else if (point == 1) {
            builder.setMessage("3問中、" + point + "問正解でした。!");
        } else {
            builder.setMessage("3問中、" + point + "問正解でした。");
        }


        builder.setPositiveButton("リトライ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //問題数・ポイントリセット
                questionNumber = 0;
                countnumber = 0;
                point = 0;

                QuestionNumber.setQuestionNumber(questionNumber);
                CountNumber.setCountNumber(countnumber);
                Point.setPoint(point);

                // もう一度クイズをやり直す
                restart();
            }

        });
        builder.show();



    }

    public void restart(){


        Intent intent = new Intent(this, MainActivity.class);

        //intentを発行 startActivity()
        startActivity(intent);

    }


}
