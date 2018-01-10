package atschool.co.jp.kawaii;

import android.app.Application;

/**
 * Created by nttr on 2017/12/27.
 */

public class Common extends Application {

    private Integer TOTAL_Q=3;
    private Integer QUESTIONNUMBER = 0;//仮
    private Integer COUNTNUMBER = 0;
    private Integer HISTORY_Q[] = new Integer[100];
    private Integer TEMP;
    private Integer POINT = 0;
    private Integer OPT1 = 0;
    private Integer OPT2 = 0;
    private Integer OPT3 =0;
    private String ANS1 = "";
    private String ANS2 = "";
    private String ANS3 = "";
    private String S_OPT1 = "";
    private String S_OPT2 = "";
    private String S_OPT3 = "";
    private boolean CHK_ANS = true;
    //private gCounts testString3 = new gCounts(0,0,0)

    @Override
    public void onCreate() {
        super.onCreate();
/*        HISTORY_Q[1] = 0;
        HISTORY_Q[2] = 0;
        HISTORY_Q[3] = 0;*/
    }

    //HISTORY_Q
    public Integer getHistory_Q() {
        //HISTORY_Q[3] = 6;
        return HISTORY_Q[TEMP];}

    public void setHistory_Q(Integer History_Q) {HISTORY_Q[TEMP] = History_Q; }

    //HISTORY_Q
    public Integer getTemp() {return TEMP;}

    public void setTemp(Integer Temp) {TEMP = Temp; }


    //TOTAL_Q
    public Integer getTotal_Q() {return TOTAL_Q;}

    public void setTotal_Q(Integer Total_Q) {TOTAL_Q = Total_Q; }

    //QUESTIONNUMBER
    public Integer getQuestionNumner() {return QUESTIONNUMBER;}

    public void setQuestionNumber(Integer QuestionNumber) {QUESTIONNUMBER = QuestionNumber; }

    //COUNT_NUMBER
    public Integer getCountNumber() {return COUNTNUMBER; }

    public void setCountNumber(Integer CountNumber) {COUNTNUMBER = CountNumber; }

    //POINT
    public Integer getPoint() {return POINT;}

    public void setPoint(Integer Point) {POINT = Point; }


    //OPTION
    public Integer getOpt1() {
        return OPT1;
    }

    public void setOpt1(Integer Opt1) {OPT1 = Opt1;}

    public Integer getOpt2() {
        return OPT2;
    }

    public void setOpt2(Integer Opt2) {OPT2 = Opt2;}

    public Integer getOpt3() {
        return OPT3;
    }

    public void setOpt3(Integer Opt3) {OPT3 = Opt3;}

    //ANS
    public String getAns1() {
        return ANS1;
    }

    public void setAns1(String Ans1) {ANS1 = Ans1;}

    public String getAns2() {
        return ANS2;
    }

    public void setAns2(String Ans2) {ANS2 = Ans2;}

    public String getAns3() {
        return ANS3;
    }

    public void setAns3(String Ans3) {ANS3 = Ans3;}

    //S_OPT
    public String getS_Opt1() {return S_OPT1;}

    public void setS_Opt1(String S_Opt1) {S_OPT1 = S_Opt1;}

    public String getS_Opt2() {
        return S_OPT2;
    }

    public void setS_Opt2(String S_Opt2) {S_OPT2 = S_Opt2;}

    public String getS_Opt3() {
        return S_OPT3;
    }

    public void setS_Opt3(String S_Opt3) {S_OPT3 = S_Opt3;}


    //CHK_ANS
    public boolean getChk_Ans() {return CHK_ANS;}

    public void setChk_Ans(boolean Chk_Ans) {CHK_ANS = Chk_Ans; }


}
