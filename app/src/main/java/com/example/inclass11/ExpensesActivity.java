package com.example.inclass11;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class ExpensesActivity extends AppCompatActivity {
    EditText et_expense,et_amount;
    Spinner spinner;
    Button btn_add,btn_cancel;
    String category;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        setTitle("Add Expense");
        et_expense=findViewById(R.id.et_ename);
        et_amount=findViewById(R.id.et_Amount);
        spinner=findViewById(R.id.spinner);
        btn_add=findViewById(R.id.btn_add);
        btn_cancel=findViewById(R.id.btn_cancel);
        db = FirebaseFirestore.getInstance();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ExpensesActivity.this,
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

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_expense.getText().toString().equals(""))
                    et_expense.setError("It can't be empty");
                else if(et_amount.getText().toString().equals(""))
                    et_amount.setError("It can't be empty");
                else {
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    Date dateobj = new Date();
                    Expense expense = new Expense(et_expense.getText().toString(), et_amount.getText().toString(), category, df.format(dateobj));
                    HashMap<String, Object> toSave = expense.toHashMap();
                    db.collection("expenses")
                            .add(toSave)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("demo", "OnSuccess:Succesful");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("demo", "onFailure");
                                }
                            });
                    Intent i1 = new Intent(ExpensesActivity.this, MainActivity.class);
                    startActivity(i1);
                }
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
