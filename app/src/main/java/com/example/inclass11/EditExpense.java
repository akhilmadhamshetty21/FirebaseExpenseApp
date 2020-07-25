package com.example.inclass11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditExpense extends AppCompatActivity {
    EditText et_expense,et_amount;
    Spinner spinner;
    Button btn_add,btn_cancel;
    String category;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expense);
        setTitle("Edit Expense");
        et_expense=findViewById(R.id.et_eenmae);
        et_amount=findViewById(R.id.et_eamount);
        spinner=findViewById(R.id.editspinner);
        btn_add=findViewById(R.id.btn_save);
        btn_cancel=findViewById(R.id.btn_ecancel);
        db = FirebaseFirestore.getInstance();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditExpense.this,
                R.array.categories_array, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (getIntent()!=null){
            Bundle extras=getIntent().getExtras();
            et_expense.setText(extras.getString("name"));
            et_amount.setText(String.valueOf(extras.getString("amount")));
            int position=adapter.getPosition(extras.getString("category"));
            spinner.setSelection(position);
        }
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_expense.getText().toString().equals(""))
                    et_expense.setError("It can't be empty");
                else if (et_amount.getText().toString().equals(""))
                    et_amount.setError("It can't be empty");
                else {
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    Date dateobj = new Date();
                    db.collection("expenses")
                            .document(getIntent().getExtras().getString("DocumentID"))
                            .update("cost", et_amount.getText().toString(), "title", et_expense.getText().toString(), "category", category, "date", df.format(dateobj))
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("Success", "Update Success");
                                }
                            });
                    Intent i1 = new Intent(EditExpense.this, MainActivity.class);
                    startActivity(i1);
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(EditExpense.this, MainActivity.class);
                startActivity(i2);
            }
        });


    }

}
