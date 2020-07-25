/*
    Assignment: Inclass11
    Team members:
    Akhil Madhamshetty:801165622
    Tarun thota:801164383
 */
package com.example.inclass11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements ExpenseAdapter.InteractMainActivity {
    private FirebaseFirestore db;
    ImageView iv_add;
    TextView tv_current;
    RecyclerView recyclerView;
    ArrayList<Expense> expenses=new ArrayList<>();
    RecyclerView.LayoutManager rv_layoutManager;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Expense App");
        db = FirebaseFirestore.getInstance();
        iv_add=findViewById(R.id.imageView);
        tv_current=findViewById(R.id.tv_expenses);
        recyclerView=findViewById(R.id.expenselist);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        rv_layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rv_layoutManager);
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(MainActivity.this,ExpensesActivity.class);
                startActivity(i1);
            }
        });



//        Expense expense=new Expense("Walmart",67.00,"groceries",new Date());
//        HashMap<String,Object> toSave=expense.toHashMap();
//        CollectionReference cities = db.collection("expenses");
//        db.collection("expenses")
//                .add(toSave)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d("demo","OnSuccess:Succesful");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.d("demo","onFailure");
//                    }
//                });
        db.collection("expenses")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot documentSnapshot:queryDocumentSnapshots){

                            Expense expense=documentSnapshot.toObject(Expense.class);
                            expense.setDocumentId(documentSnapshot.getId());
                            Log.d("expense:",expense.getDocumentId());
                            expenses.add(expense);
                        }
                        if(expenses.size()!=0)
                            tv_current.setVisibility(View.INVISIBLE);
                        adapter=new ExpenseAdapter(expenses,MainActivity.this);
                        recyclerView.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("test","Failure");
                    }
                });


//        db.collection("expense").document("zzdadSXZEu26Tdcu0Faf")
//                .delete()
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Log.d("delete:","Succesful");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.d("delete:","Unsuccesful");
//                    }
//                });

    }
    @Override
    public void deleteitem(final int position, final String documentID) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete an item?")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.collection("expenses").document(documentID)
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("test", "Delete succeeded!");
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.e("test", "Delete unsuccessful: "+e.getMessage());
                                    }
                                });
                                expenses.remove(position);
                                if(expenses.size()==0)
                                    tv_current.setVisibility(View.VISIBLE);
                                adapter.notifyDataSetChanged();
                            }
                        }
                ).show();

    }
}
