package com.example.sign.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.example.sign.Model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class database extends SQLiteAssetHelper {
    private  static  final String  DB_Name="ProductDetail";
    private  static  final  int DB_Version=1;


    public database(Context context) {
        super(context, DB_Name, null, DB_Version);
    }

    public List<Order> getcarts()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder builder=new SQLiteQueryBuilder();
        String [] sqlSelect={"ProductId","ProductName","Quantity","Price","Discount","Image"};
        String sqlTable="OrderProduct";

        builder.setTables(sqlTable);

        Cursor cursor = builder.query(db,sqlSelect,null,null,null,null,null);
        final  List<Order> orders=new ArrayList<>();
        if (cursor.moveToFirst())
        {
            do {
                orders.add(
                        new Order (
                        cursor.getString(cursor.getColumnIndex("ProductId")),
                        cursor.getString(cursor.getColumnIndex("ProductName")),
                        cursor.getString(cursor.getColumnIndex("Quantity")),
                        cursor.getString(cursor.getColumnIndex("Price")),
                        cursor.getString(cursor.getColumnIndex("Discount")),
                        cursor.getString(cursor.getColumnIndex("Image"))
                        ));

            }
            while (cursor.moveToNext());

        }
        return orders;
    }

    public void addcart(Order ordered){

        SQLiteDatabase db=getReadableDatabase();
        String query=String.format("INSERT INTO OrderProduct(ProductId,ProductName,Quantity,Price,Discount,Image) Values('%s','%s','%s','%s','%s','%s');",
                ordered.getProductId(),
                ordered.getProductName(),
                ordered.getQuantity(),
                ordered.getPrice(),
                ordered.getDiscount(),
                ordered.getImage()
       );
        db.execSQL(query);




    }
    public void clearcart(){
        SQLiteDatabase db=getReadableDatabase();
        String query=String.format("DELETE FROM OrderProduct");
        db.execSQL(query);

    }


}
