package com.example.database;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    EditText id, fName, lName, marks;
    Button bAdd, bView, bUpdate, bDelete;
    Databasehelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new Databasehelper(this);
        bAdd = findViewById(R.id.button);
        bView = findViewById(R.id.button2);
        bUpdate = findViewById(R.id.button3);
        bDelete = findViewById(R.id.button4);
        id = findViewById(R.id.editTextTextPersonName);
        fName = findViewById(R.id.editTextTextPersonName2);
        lName = findViewById(R.id.editTextTextPersonName3);
        marks = findViewById(R.id.editTextNumberDecimal);
        AddData();
        ViewAll();
        updateData();
        deleteData();
    }

    private void updateData() {
        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate =
                        myDB.updateData(id.getText().toString(), fName.getText().toString(),
                                lName.getText().toString(), marks.getText().toString());
                if (isUpdate) {
                    Toast.makeText(MainActivity.this, "Data updated",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data not updated",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void ViewAll() {
        bView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.getAllData();
                if (res.getCount() == 0) {
                    showMessage("Error", "No data found .....");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID : " + res.getString(0) + "\n");
                    buffer.append("First Name : " + res.getString(1) + "\n");
                    buffer.append("Last Name : " + res.getString(2) + "\n");
                    buffer.append("Marks : " + res.getString(3) + "\n\n");
                }
                showMessage("Data ", buffer.toString());
            }
        });
    }

    private void showMessage(String title, String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(s);
        builder.show();
    }

    private void AddData() {
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted =
                        myDB.inserData(fName.getText().toString(), lName.getText().toString(), marks.getText
                                ().toString());
                if (isInserted) {
                    Toast.makeText(MainActivity.this, "Data inserted",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data not inserted",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void deleteData() {
        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteRows = myDB.deleteData(id.getText().toString());
                if (deleteRows > 0) {
                    Toast.makeText(MainActivity.this, "Data deleted",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data not deleted",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}