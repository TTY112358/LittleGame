package com.example.tty.myfirstapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class StocklistActivity extends ActionBarActivity {
    ListView lv;
    Button bt1;
    TextView tv1;
    java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocklist);
        StockMarket.addStock(600123, "Stock_1");
        StockMarket.addStock(600478, "Stock_2");
        StockMarket.addStock(600009, "Stock_3");
        StockMarket.addStock(600467, "Stock_4");
        StockMarket.addStock(600914, "Stock_5");
        StockMarket.addStock(600868, "Stock_6");
        StockMarket.addStock(600576, "Stock_7");
        StockMarket.addStock(600975, "Stock_8");
        StockMarket.addStock(600483, "Stock_9");
        StockMarket.addStock(600726, "Stock_10");
        StockMarket.addStock(600477, "Stock_11");
        StockMarket.addStock(600444, "Stock_12");
        lv = (ListView)findViewById(R.id.listView1);
        bt1 = (Button)findViewById(R.id.stocklist_button1);
        tv1 = (TextView)findViewById(R.id.stocklist_textView2);
        tv1.setText(df.format(Data.money));
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StockMarket.nextRound();
                MyAdapter adapter = new MyAdapter(lv.getContext());
                lv.setAdapter(adapter);
            }
        });
        MyAdapter adapter = new MyAdapter(lv.getContext());
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stocklistactivity, menu);
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

    public final class ViewHolder{
        public TextView tv_stock_number;
        public TextView tv_stock_name;
        public TextView tv_stock_price;
        public TextView tv_stock_rate;
        public TextView tv_stock_holding;
        public Button bt_purchase;
        public Button bt_sell;
    }


    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return StockMarket.getStockList().size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {

                holder = new ViewHolder();

                convertView = mInflater.inflate(R.layout.vlist_market, null);
                holder.tv_stock_number = (TextView)convertView.findViewById(R.id.tv_stock_number);
                holder.tv_stock_name = (TextView)convertView.findViewById(R.id.tv_stock_name);
                holder.tv_stock_price = (TextView)convertView.findViewById(R.id.tv_stock_price);
                holder.tv_stock_rate = (TextView)convertView.findViewById(R.id.tv_stock_rate);
                holder.tv_stock_holding = (TextView)convertView.findViewById(R.id.tv_stock_holding);
                holder.bt_purchase = (Button)convertView.findViewById(R.id.bt_stock_purchase);
                holder.bt_sell = (Button)convertView.findViewById(R.id.bt_stock_sell);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv_stock_number.setText("" + StockMarket.getStockList().get(position).getStock_number());
            holder.tv_stock_name.setText(StockMarket.getStockList().get(position).getStock_name());
            holder.tv_stock_price.setText("Price: " + df.format(StockMarket.getStockList().get(position).getStock_price()));
            double rate_temp = StockMarket.getStockList().get(position).getStock_rate();
            if(rate_temp > 0){
                holder.tv_stock_rate.setText(Html.fromHtml("Rate: <font color = red>↑" +  df.format(rate_temp * 100) + "%</font>"));
            }else if(rate_temp < 0){
                holder.tv_stock_rate.setText(Html.fromHtml("Rate: <font color = green>↓" +  df.format(rate_temp * 100) + "%</font>"));
            }else {
                holder.tv_stock_rate.setText(Html.fromHtml("Rate: " + df.format(rate_temp * 100) + "%"));
            }
            holder.tv_stock_holding.setText("Holding: " + StockMarket.getStockList().get(position).getHolding_num());
            final ViewHolder finalHolder = holder;
            holder.bt_purchase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    final View view = inflater.inflate(R.layout.integer_getter, null);
                    Button bt_max = (Button)view.findViewById(R.id.bt_integer_getter);
                    bt_max.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((EditText)view.findViewById(R.id.et_integer_getter)).setText("" + (int)(Data.money / StockMarket.getStockList().get(position).getStock_price()));
                        }
                    });
                    AlertDialog.Builder builder = new AlertDialog.Builder(StocklistActivity.this);
                    builder.setTitle("PURCHASE").setView(view).
                        setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).setPositiveButton("Purchase", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String tempStr = ((EditText) view.findViewById(R.id.et_integer_getter)).getText().toString();
                            try {
                                int num = Integer.parseInt(tempStr);
                                if (StockMarket.getStockList().get(position).purchase(num)) {
                                    tv1.setText(df.format(Data.money));
                                    finalHolder.tv_stock_holding.setText("Holding: " + StockMarket.getStockList().get(position).getHolding_num());
                                } else {
                                    Toast toast = Toast.makeText(StocklistActivity.this, "Fail to purchse:" + StockMarket.getStockList().get(position).getStock_name(), Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            } catch (Exception ex) {
                                Toast toast = Toast.makeText(StocklistActivity.this, "Fail when purchase.", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                    }).create().show();
                }
            });
            holder.bt_sell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    final View view = inflater.inflate(R.layout.integer_getter, null);
                    Button bt_max = (Button)view.findViewById(R.id.bt_integer_getter);
                    bt_max.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((EditText) view.findViewById(R.id.et_integer_getter)).setText("" + StockMarket.getStockList().get(position).getHolding_num());
                        }
                    });
                    AlertDialog.Builder builder = new AlertDialog.Builder(StocklistActivity.this);
                    builder.setTitle("SELL").setView(view).
                        setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).setPositiveButton("Sell", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String tempStr = ((EditText) view.findViewById(R.id.et_integer_getter)).getText().toString();
                            try {
                                int num = Integer.parseInt(tempStr);
                                if (StockMarket.getStockList().get(position).sell(num)) {
                                    tv1.setText(df.format(Data.money));
                                    finalHolder.tv_stock_holding.setText("Holding: " + StockMarket.getStockList().get(position).getHolding_num());
                                } else {
                                    Toast toast = Toast.makeText(StocklistActivity.this, "Fail to sell:" + StockMarket.getStockList().get(position).getStock_name(), Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            } catch (Exception ex) {
                                Toast toast = Toast.makeText(StocklistActivity.this, "Fail when sell.", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                    }).create().show();
                }
            });
            return convertView;
        }
    }
}
