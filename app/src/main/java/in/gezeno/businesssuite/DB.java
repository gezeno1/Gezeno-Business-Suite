package in.gezeno.businesssuite;

import android.content.*;import android.database.*;import android.database.sqlite.*;import java.util.*;

public class DB extends SQLiteOpenHelper {
 public DB(Context c){super(c,"gezeno.db",null,1);}
 public void onCreate(SQLiteDatabase d){
  d.execSQL("CREATE TABLE settings(k TEXT PRIMARY KEY,v TEXT)");
  d.execSQL("CREATE TABLE customers(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,person TEXT,phone TEXT,address TEXT,city TEXT,state TEXT,pin TEXT,gstin TEXT,type TEXT)");
  d.execSQL("CREATE TABLE vendors(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,person TEXT,phone TEXT,address TEXT,city TEXT,state TEXT,pin TEXT,gstin TEXT)");
  d.execSQL("CREATE TABLE products(id INTEGER PRIMARY KEY AUTOINCREMENT,sku TEXT UNIQUE,name TEXT,parent TEXT,category TEXT,vendor_id INTEGER,purchase REAL,b2b REAL,mrp REAL,gst REAL,stock REAL,reorder REAL,image TEXT)");
  d.execSQL("CREATE TABLE orders(id INTEGER PRIMARY KEY AUTOINCREMENT,orderno TEXT UNIQUE,customer_id INTEGER,date TEXT,status TEXT,subtotal REAL,discount REAL,transport REAL,gst REAL,total REAL,paid REAL,due REAL,payment_status TEXT)");
  d.execSQL("CREATE TABLE order_items(id INTEGER PRIMARY KEY AUTOINCREMENT,order_id INTEGER,product_id INTEGER,qty REAL,rate REAL,gst REAL,amount REAL)");
  d.execSQL("CREATE TABLE invoices(id INTEGER PRIMARY KEY AUTOINCREMENT,invno TEXT UNIQUE,order_id INTEGER,date TEXT,status TEXT,total REAL,paid REAL,due REAL)");
  d.execSQL("CREATE TABLE payments(id INTEGER PRIMARY KEY AUTOINCREMENT,receipt TEXT,date TEXT,order_id INTEGER,customer_id INTEGER,amount REAL,mode TEXT,note TEXT)");
  d.execSQL("CREATE TABLE purchases(id INTEGER PRIMARY KEY AUTOINCREMENT,pono TEXT UNIQUE,vendor_id INTEGER,date TEXT,status TEXT,subtotal REAL,gst REAL,transport REAL,total REAL,paid REAL,due REAL)");
  d.execSQL("CREATE TABLE purchase_items(id INTEGER PRIMARY KEY AUTOINCREMENT,purchase_id INTEGER,product_id INTEGER,qty REAL,rate REAL,gst REAL,amount REAL)");
  seed(d);
 }
 public void onUpgrade(SQLiteDatabase d,int o,int n){}
 void seed(SQLiteDatabase d){
  put(d,"brand","Gezeno");put(d,"subtitle","Business Suite");put(d,"phone","+91 9711911391");put(d,"email","gezeno.official@gmail.com");put(d,"address","Delhi (Uttam Nagar)");put(d,"gstin","");put(d,"taxmode","CGST+SGST");put(d,"username","admin");put(d,"password","gezeno123");
  d.execSQL("INSERT INTO vendors(name,person,phone,address,city,state,pin,gstin) VALUES('Demo Vendor A','Rajesh','9876500001','12 Industrial Area','Delhi','Delhi','110020','07ABCDE1234F1Z5')");
  d.execSQL("INSERT INTO vendors(name,person,phone,address,city,state,pin,gstin) VALUES('Demo Vendor B','Amit','9876500002','45 Market Road','Delhi','Delhi','110058','07PQRSX5678L1Z2')");
  d.execSQL("INSERT INTO customers(name,person,phone,address,city,state,pin,gstin,type) VALUES('Happy Kids Store','Rohit','9811100001','A-12 Main Market','New Delhi','Delhi','110059','07ABCDE1234F1Z5','Regular')");
  d.execSQL("INSERT INTO customers(name,person,phone,address,city,state,pin,gstin,type) VALUES('New Star Gift House','Pankaj','9811100002','B-45 District Centre','New Delhi','Delhi','110058','07PQRSX5678L1Z2','Interested')");
  d.execSQL("INSERT INTO products(sku,name,parent,category,vendor_id,purchase,b2b,mrp,gst,stock,reorder) VALUES('GZN-001','Interactive Learning Book','Toys','Learning',1,350,450,699,5,25,3)");
  d.execSQL("INSERT INTO products(sku,name,parent,category,vendor_id,purchase,b2b,mrp,gst,stock,reorder) VALUES('GZN-002','2-in-1 LCD Writing Pad Pencil Box','Stationery','Kids',2,120,165,249,12,40,5)");
  d.execSQL("INSERT INTO products(sku,name,parent,category,vendor_id,purchase,b2b,mrp,gst,stock,reorder) VALUES('GZN-003','Air Dry Clay Kit 12 Colors','Toys','Arts & Crafts',1,90,125,199,5,30,5)");
  d.execSQL("INSERT INTO products(sku,name,parent,category,vendor_id,purchase,b2b,mrp,gst,stock,reorder) VALUES('GZN-004','Kids Wireless Headphone','Electronics','Kids',1,280,350,599,18,15,3)");
 }
 public String get(String k){Cursor c=getReadableDatabase().rawQuery("SELECT v FROM settings WHERE k=?",new String[]{k});String x="";if(c.moveToFirst())x=c.getString(0);c.close();return x;}
 public void put(String k,String v){SQLiteDatabase d=getWritableDatabase();ContentValues cv=new ContentValues();cv.put("k",k);cv.put("v",v);d.insertWithOnConflict("settings",null,cv,SQLiteDatabase.CONFLICT_REPLACE);}
 public Cursor q(String sql,String...a){return getReadableDatabase().rawQuery(sql,a);}
 public long exec(String sql,Object...args){SQLiteDatabase d=getWritableDatabase();SQLiteStatement s=d.compileStatement(sql);for(int i=0;i<args.length;i++){Object x=args[i];if(x==null)s.bindNull(i+1);else if(x instanceof Number)s.bindDouble(i+1,((Number)x).doubleValue());else s.bindString(i+1,x.toString());}String q=sql.trim().toUpperCase(Locale.US);if(q.startsWith("INSERT")||q.startsWith("REPLACE"))return s.executeInsert();return s.executeUpdateDelete();}
 public int update(String table,ContentValues v,String w,String...a){return getWritableDatabase().update(table,v,w,a);}
 public int delete(String t,String w,String...a){return getWritableDatabase().delete(t,w,a);}
}
