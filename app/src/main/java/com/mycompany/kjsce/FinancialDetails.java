package com.mycompany.kjsce;


import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FinancialDetails extends AppCompatActivity {
    DatabaseHelper myDb;
    DatabaseHelperCredit myDbc;
    DatabaseHelperAtm myDba;
    DatabaseHelperLocker myDbl;
    EditText bankac,bankpass,atmno,atmpass,creditbank,creditno,cvc,expiry,loc,locpass;
    String Bankac,Bankpass,Creditbank,Creditno,Cvc,Expiry,Locname,Loccom,Atmno,Atmpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_details);
        myDb = new DatabaseHelper(this);
        myDbc = new DatabaseHelperCredit(this);
        myDbl = new DatabaseHelperLocker(this);
        myDba = new DatabaseHelperAtm(this);
        bankac = (EditText) findViewById(R.id.bankac);
        bankpass = (EditText) findViewById(R.id.bankpass);
        creditbank = (EditText) findViewById(R.id.creditbank);
        creditno = (EditText) findViewById(R.id.creditno);
        cvc = (EditText) findViewById(R.id.cvc);
        expiry = (EditText) findViewById(R.id.creditexpiry);
        loc=(EditText)findViewById(R.id.lockername);
        locpass=(EditText)findViewById(R.id.lockercom);
        atmno=(EditText)findViewById(R.id.atmno);
        atmpass=(EditText)findViewById(R.id.atmpass);
    }

    public void getbank(View v)
    {
        viewall();
        viewallcredit();
        viewallatm();
        viewallloc();
    }

    public void Bank (View v)
    {
        Bankac=bankac.getText().toString();
        Bankpass=bankpass.getText().toString();
        Toast.makeText(getApplicationContext(),Bankpass,Toast.LENGTH_LONG).show();

        setdata();
    }

    public void setdata()
    {
        boolean isInserted=myDb.insertData(Bankac,Bankpass);
        if(isInserted==true)
            Toast.makeText(getApplicationContext(), "WELCOME", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"Data NotInserted",Toast.LENGTH_LONG).show();
    }

    public void viewall()
    {

        Cursor res=myDb.getAlldata();
        if(res.getCount()==0)
        {
            showMessage("Error","No Data found");
            return;
        }
        StringBuffer buffer =new StringBuffer();
        while(res.moveToNext())
        {
            buffer.append("Id:"+res.getString(0)+"\n");
            buffer.append("Bankac:"+res.getString(1)+"\n");
            buffer.append("Bankpass:"+res.getString(2)+"\n");
//            Table=res.getString(1);
            //Toast.makeText(getApplicationContext(),Table,Toast.LENGTH_LONG).show();
        }
        showMessage("Data", buffer.toString());
    }

    public void credit(View v)
    {
        Creditbank=creditbank.getText().toString();
        Creditno=creditno.getText().toString();
        Cvc=cvc.getText().toString();
        Expiry=expiry.getText().toString();
        setdatacredit();
    }

    public void setdatacredit()
    {
        boolean isInserted=myDbc.insertData(Creditno, Creditbank, Cvc, Expiry);
        if(isInserted==true)
            Toast.makeText(getApplicationContext(), "WELCOME", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"Data NotInserted",Toast.LENGTH_LONG).show();
    }

    public void viewallcredit()
    {
        Cursor res=myDbc.getAlldata();
        if(res.getCount()==0)
        {
            showMessage("Error","No Data found");
            return;
        }
        StringBuffer buffer =new StringBuffer();
        while(res.moveToNext())
        {
            buffer.append("Id:"+res.getString(0)+"\n");
            buffer.append("Credit Card No.:"+res.getString(1)+"\n");
            buffer.append("Bank:"+res.getString(2)+"\n");
            buffer.append("CVC:"+res.getString(3)+"\n");
            buffer.append("Expiry Date:"+res.getString(4)+"\n");
//            Table=res.getString(1);
            //Toast.makeText(getApplicationContext(),Table,Toast.LENGTH_LONG).show();
        }
        showMessage("Data", buffer.toString());
    }

    public void locker(View v)
    {
        Loccom=locpass.getText().toString();
        Locname=loc.getText().toString();
        setdataloc();
    }

    public void setdataloc()
    {
        boolean isInserted=myDbl.insertData(Locname,Loccom);
        if(isInserted==true)
            Toast.makeText(getApplicationContext(), "WELCOME", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"Data NotInserted",Toast.LENGTH_LONG).show();
    }

    public void viewallloc()
    {
        Cursor res=myDbl.getAlldata();
        if(res.getCount()==0)
        {
            showMessage("Error","No Data found");
            return;
        }

        StringBuffer buffer =new StringBuffer();
        while(res.moveToNext())
        {
            buffer.append("Id:"+res.getString(0)+"\n");
            buffer.append("Locker Name:"+res.getString(1)+"\n");
            buffer.append("Locker Combination:"+res.getString(2)+"\n");
//            Table=res.getString(1);
            //Toast.makeText(getApplicationContext(),Table,Toast.LENGTH_LONG).show();
        }
        showMessage("Data", buffer.toString());
    }

    public void atm(View v)
    {
        Atmpass=atmpass.getText().toString();
        Atmno=atmno.getText().toString();
        setdataatm();
    }

    public void setdataatm()
    {
        boolean isInserted=myDba.insertData(Atmno,Atmpass);
        if(isInserted==true)
            Toast.makeText(getApplicationContext(), "WELCOME", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"Data NotInserted",Toast.LENGTH_LONG).show();
    }

    public void viewallatm()
    {
        Cursor res=myDba.getAlldata();
        if(res.getCount()==0)
        {
            showMessage("Error","No Data found");
            return;
        }
        StringBuffer buffer =new StringBuffer();
        while(res.moveToNext())
        {
            buffer.append("Id:"+res.getString(0)+"\n");
            buffer.append("Atm No.:"+res.getString(1)+"\n");
            buffer.append("Atm Password:"+res.getString(2)+"\n");
//            Table=res.getString(1);
            //Toast.makeText(getApplicationContext(),Table,Toast.LENGTH_LONG).show();
        }
        showMessage("Data", buffer.toString());
    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}