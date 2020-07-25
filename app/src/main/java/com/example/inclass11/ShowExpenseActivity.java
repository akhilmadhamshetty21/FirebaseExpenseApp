package com.example.inclass11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowExpenseActivity extends AppCompatActivity {
    TextView tv_name,tv_category,tv_amount,tv_date;
    Button btn_edit,btn_close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_expense);
        setTitle("Show Expense");
        tv_name=findViewById(R.id.tv_dname);
        tv_amount=findViewById(R.id.tv_damount);
        tv_category=findViewById(R.id.tv_dcategory);
        tv_date=findViewById(R.id.tv_ddate);
        btn_edit=findViewById(R.id.btn_editexpense);
        btn_close=findViewById(R.id.btn_close);
        final Bundle extras=getIntent().getExtras();
        tv_name.setText(extras.getString("name"));
        tv_category.setText(extras.getString("category"));
        String price=extras.getString("amount");
        tv_amount.setText(String.valueOf(price));
        tv_date.setText(extras.getString("date"));
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(ShowExpenseActivity.this,EditExpense.class);
                i1.putExtras(extras);
                startActivity(i1);
            }
        });

    }
}
