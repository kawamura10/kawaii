package atschool.co.jp.kawaii;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Top extends AppCompatActivity {

    //問題数（第●問目）
    private Common Total_Q;
    public int total_q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

  /*      Total_Q = (Common) this.getApplication();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        //TOTAL_Q の取得（firebase）
        DatabaseReference a_Total_Q = database.getReference("counts/a_Total_Q");
        a_Total_Q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                long a_total_q = (long) dataSnapshot.getValue();

                total_q = (int)a_total_q;
                Log.d("ABCDEYWYWYWYWYWYWYWYW", "" + total_q);

                Total_Q.setTotal_Q(total_q);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });*/



    }

    public void onClickmain(View view) {

        Intent intent = new Intent(this, MainActivity.class);

        //intentを発行 startActivity()
        startActivity(intent);

        }

}
