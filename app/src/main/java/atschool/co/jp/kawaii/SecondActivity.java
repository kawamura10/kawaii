package atschool.co.jp.kawaii;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    TextView Chk_Ans_textView;

    TextView editText4;

    //問題数（第●問目）
    private Common CountNumber;
    public int countnumber;

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

    public int percentageOpt1;
    public int percentageOpt2;
    public int percentageOpt3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        CountNumber = (Common) this.getApplication();
        countnumber = CountNumber.getCountNumber();

        Point = (Common) this.getApplication();
        point = Point.getPoint();

        Opt1 = (Common) this.getApplication();
        opt1 = Opt1.getOpt1();

        Opt2 = (Common) this.getApplication();
        opt2 = Opt2.getOpt2();

        Opt3 = (Common) this.getApplication();
        opt3 = Opt3.getOpt3();


        Ans1 = (Common) this.getApplication();
        ans1 = Ans1.getAns1();

        Ans2 = (Common) this.getApplication();
        ans2 = Ans2.getAns2();

        Ans3 = (Common) this.getApplication();
        ans3 = Ans3.getAns3();

        S_Opt1 = (Common) this.getApplication();
        s_opt1 = S_Opt1.getS_Opt1();
        S_Opt2 = (Common) this.getApplication();
        s_opt2 = S_Opt2.getS_Opt2();
        S_Opt3 = (Common) this.getApplication();
        s_opt3 = S_Opt3.getS_Opt3();


        Chk_Ans = (Common) this.getApplication();
        chk_ans = Chk_Ans.getChk_Ans();


        Chk_Ans_textView = findViewById(atschool.co.jp.kawaii.R.id.Chk_Ans_textView);

        editText4 = findViewById(atschool.co.jp.kawaii.R.id.editText4);



        if (chk_ans == true) {

            Chk_Ans_textView.setText("正解");

        } else {

            Chk_Ans_textView.setText("残念");

        }

        editText4.setText("１番かわいいのは " + ans1 + ans2 + " でした！");


        //Log.d("ABCDEEEE", "CountNumber = " + CountNum);
        Log.d("ABCDEEEE", "OPT3 = " + opt3);
        Log.d("ABCDEEEE", "BOOLEAN = " + chk_ans);


        createPieChart();
    }
//}


    //createPieChart();
    public void onClickTest(View view) {


        if (countnumber == 3){

            Intent intent = new Intent(this, ResultActivity.class);

            //intentを発行 startActivity()
            startActivity(intent);


        }

        else {

            Intent intent = new Intent(this, MainActivity.class);

            //intentを発行 startActivity()
            startActivity(intent);

        }

    }


    private void createPieChart() {

        PieChart pieChart = (PieChart) findViewById(atschool.co.jp.kawaii.R.id.pie_chart);

        pieChart.setDrawHoleEnabled(true); // 真ん中に穴を空けるかどうか
        pieChart.setHoleRadius(50f);       // 真ん中の穴の大きさ(%指定)
        //pieChart.setHoleColorTransparent(true);
        //pieChart.setHoleColor(150);

        pieChart.setTransparentCircleRadius(55f);
        pieChart.setRotationAngle(270);          // 開始位置の調整
        pieChart.setRotationEnabled(true);       // 回転可能かどうか
        pieChart.getLegend().setEnabled(true);   //
        pieChart.setDescription("一番かわいい");
        pieChart.setData(createPieChartData());

        // 更新
        pieChart.invalidate();
        // アニメーション
        pieChart.animateXY(2000, 2000); // 表示アニメーション
    }

    // pieChartのデータ設定
    private PieData createPieChartData() {

        percentageOpt1 = opt1 * 100 / (opt1 + opt2 + opt3);
        percentageOpt2 = opt2 * 100 / (opt1 + opt2 + opt3);
        percentageOpt3 = opt3 * 100 / (opt1 + opt2 + opt3);

        //Log.d("ABCDYYYYYYYYYYYYYYYYY", "" + percentageOpt1 + " " + percentageOpt2 + " " + percentageOpt3);
        //ArrayList<Entry> yVals = new ArrayList<>();
        ArrayList<Entry> yVals = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();

        xVals.add(s_opt1 + "");
        xVals.add(s_opt2 + "");
        xVals.add(s_opt3 + "");

        //yVals.add(new Entry(20, 0));
        //yVals.add(new Entry(30, 1));
        //yVals.add(new Entry(50, 2));

        yVals.add(new Entry(percentageOpt1, 0));
        yVals.add(new Entry(percentageOpt2, 1));
        yVals.add(new Entry(percentageOpt3, 2));

        PieDataSet dataSet = new PieDataSet(yVals, "Data");
        dataSet.setSliceSpace(5f);
        dataSet.setSelectionShift(1f);

        // 色の設定
        colors.add(ColorTemplate.COLORFUL_COLORS[0]);
        colors.add(ColorTemplate.COLORFUL_COLORS[1]);
        colors.add(ColorTemplate.COLORFUL_COLORS[2]);
        dataSet.setColors(colors);
        dataSet.setDrawValues(true);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());

        // テキストの設定
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.WHITE);
        return data;
    }
}
