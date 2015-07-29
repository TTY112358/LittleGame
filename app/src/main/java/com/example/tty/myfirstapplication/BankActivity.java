package com.example.tty.myfirstapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class BankActivity extends ActionBarActivity {
    TextView tv1, tv2, tv3;
    Button bt1, bt2, bt3;
    java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        tv1 = (TextView)findViewById(R.id.bank_textView2);
        tv2 = (TextView)findViewById(R.id.bank_textView4);
        tv3 = (TextView)findViewById(R.id.bank_textView6);
        bt1 = (Button)findViewById(R.id.bank_button1);
        bt2 = (Button)findViewById(R.id.bank_button2);
        bt3 = (Button)findViewById(R.id.bank_button3);
        tv1.setText(df.format(Data.money));
        tv2.setText(df.format(Bank.getDeposit()));
        tv3.setText(df.format(Bank.getRate() * 100) + "%");
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final View view = inflater.inflate(R.layout.double_getter, null);
                Button bt_max = (Button) view.findViewById(R.id.bt_double_getter);
                bt_max.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((EditText) view.findViewById(R.id.et_double_getter)).setText("" + df.format(Data.money));
                    }
                });
                AlertDialog.Builder builder = new AlertDialog.Builder(BankActivity.this);
                builder.setTitle("PUT IN").setView(view).
                        setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).setPositiveButton("PUT IN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tempStr = ((EditText) view.findViewById(R.id.et_double_getter)).getText().toString();
                        try {
                            double num = Double.parseDouble(df.format(Double.parseDouble(tempStr)));
                            if (Bank.putin(num)) {
                                tv1.setText(df.format(Data.money));
                                tv2.setText(df.format(Bank.getDeposit()));
                                tv3.setText(df.format(Bank.getRate() * 100) + "%");
                            } else {
                                Toast toast = Toast.makeText(BankActivity.this, "Fail to put in:" + df.format(num), Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        } catch (Exception ex) {
                            Toast toast = Toast.makeText(BankActivity.this, "Fail when put in.", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                }).create().show();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final View view = inflater.inflate(R.layout.double_getter, null);
                Button bt_max = (Button)view.findViewById(R.id.bt_double_getter);
                bt_max.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((EditText) view.findViewById(R.id.et_double_getter)).setText("" + df.format(Bank.getDeposit()));
                    }
                });
                AlertDialog.Builder builder = new AlertDialog.Builder(BankActivity.this);
                builder.setTitle("WITHDRAW").setView(view).
                        setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).setPositiveButton("WITHDRAW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tempStr = ((EditText) view.findViewById(R.id.et_double_getter)).getText().toString();
                        try {
                            double num = Double.parseDouble(df.format(Double.parseDouble(tempStr)));
                            if (Bank.withdraw(num)) {
                                tv1.setText(df.format(Data.money));
                                tv2.setText(df.format(Bank.getDeposit()));
                                tv3.setText(df.format(Bank.getRate() * 100) + "%");
                            } else {
                                Toast toast = Toast.makeText(BankActivity.this, "Fail to withdraw:" + df.format(num), Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        } catch (Exception ex) {
                            Toast toast = Toast.makeText(BankActivity.this, "Fail when withdraw.", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                }).create().show();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bank.nextRound();
                tv1.setText(df.format(Data.money));
                tv2.setText(df.format(Bank.getDeposit()));
                tv3.setText(df.format(Bank.getRate() * 100) + "%");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bank, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
