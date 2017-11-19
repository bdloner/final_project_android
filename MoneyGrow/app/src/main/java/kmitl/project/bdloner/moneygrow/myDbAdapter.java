package kmitl.project.bdloner.moneygrow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gun on 11/1/2017.
 */

public class myDbAdapter {
    myDbHelper myhelper;
    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);

    }

    public long insertData(String image, String title, String start, String amount, String desc, String date)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.IMAGE_GOAL, image);
        contentValues.put(myDbHelper.TITLE_GOAL, title);
        contentValues.put(myDbHelper.START_GOAL, start);
        contentValues.put(myDbHelper.AMOUNT_GOAL, amount);
        contentValues.put(myDbHelper.DESC_GOAL, desc);
        contentValues.put(myDbHelper.DATE_GOAL, date);
        long id = db.insert(myDbHelper.TABLE_NAME1, null , contentValues);
        db.close();
        return id;
    }

    public long insertDataWallet(String img, String cat_name_wallet, String date_wallet,
                                 String amount_wallet, String note_wallet)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(myDbHelper.IMAGE_WALLET, img);
        contentValues.put(myDbHelper.CAT_NAME_WALLET, cat_name_wallet);
        contentValues.put(myDbHelper.DATE_WALLET, date_wallet);
        contentValues.put(myDbHelper.AMOUNT_WALLET, amount_wallet);
        contentValues.put(myDbHelper.NOTE_WALLET, note_wallet);

        long id = db.insert(myDbHelper.TABLE_NAME2, null , contentValues);
        db.close();
        return id;
    }

    public List<List> getData()
    {
        SQLiteDatabase db = myhelper.getReadableDatabase();
        List<List> datas = new ArrayList<>();
        String[] columns = {myDbHelper.UID, myDbHelper.IMAGE_GOAL ,myDbHelper.TITLE_GOAL, myDbHelper.START_GOAL,
                myDbHelper.AMOUNT_GOAL, myDbHelper.DESC_GOAL, myDbHelper.DATE_GOAL};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME1,columns,null,null,null,null,null);

        while (cursor.moveToNext())
        {
            List<String> data = new ArrayList<>();
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String image =cursor.getString(cursor.getColumnIndex(myDbHelper.IMAGE_GOAL));
            String title =cursor.getString(cursor.getColumnIndex(myDbHelper.TITLE_GOAL));
            String start =cursor.getString(cursor.getColumnIndex(myDbHelper.START_GOAL));
            String amount =cursor.getString(cursor.getColumnIndex(myDbHelper.AMOUNT_GOAL));
            String desc =cursor.getString(cursor.getColumnIndex(myDbHelper.DESC_GOAL));
            String date =cursor.getString(cursor.getColumnIndex(myDbHelper.DATE_GOAL));
            data.add(image);
            data.add(title);
            data.add(start);
            data.add(amount);
            data.add(desc);
            data.add(date);
            datas.add(data);
            data.add(String.valueOf(cid));

        }
        db.close();
        return datas;
    }

    public List<List> getDataWallet()
    {
        SQLiteDatabase db = myhelper.getReadableDatabase();
        List<List> datas = new ArrayList<>();
        String[] columns = {myDbHelper.WID, myDbHelper.IMAGE_WALLET, myDbHelper.CAT_NAME_WALLET,
                myDbHelper.DATE_WALLET, myDbHelper.AMOUNT_WALLET, myDbHelper.NOTE_WALLET};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME2,columns,null,null,null,null,null);

        while (cursor.moveToNext())
        {
            List<String> data = new ArrayList<>();
            int wid =cursor.getInt(cursor.getColumnIndex(myDbHelper.WID));
            String image =cursor.getString(cursor.getColumnIndex(myDbHelper.IMAGE_WALLET));
            String title =cursor.getString(cursor.getColumnIndex(myDbHelper.CAT_NAME_WALLET));
            String date =cursor.getString(cursor.getColumnIndex(myDbHelper.DATE_WALLET));
            String amount =cursor.getString(cursor.getColumnIndex(myDbHelper.AMOUNT_WALLET));
            String note =cursor.getString(cursor.getColumnIndex(myDbHelper.NOTE_WALLET));

            data.add(image);
            data.add(title);
            data.add(date);
            data.add(amount);
            data.add(note);
            datas.add(data);
            data.add(String.valueOf(wid));

        }
        db.close();
        return datas;
    }

    public  int delete(String cid)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs = {cid};

        int count =db.delete(myDbHelper.TABLE_NAME1 , myDbHelper.UID+" = ?",whereArgs);
        db.close();
        return  count;
    }

    public  int deleteWallet(String wid)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs = {wid};

        int count =db.delete(myDbHelper.TABLE_NAME2 , myDbHelper.WID+" = ?",whereArgs);
        db.close();
        return  count;
    }

    public int updateDetailWallet(String old_title, String new_date, String new_amount,
                                  String new_desc, String old_image, String oldId)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(myDbHelper.CAT_NAME_WALLET, old_title);
        contentValues.put(myDbHelper.DATE_WALLET, new_date);
        contentValues.put(myDbHelper.AMOUNT_WALLET, new_amount);
        contentValues.put(myDbHelper.NOTE_WALLET, new_desc);
        contentValues.put(myDbHelper.IMAGE_WALLET, old_image);

        String[] whereArgs= {oldId};
        int count =db.update(myDbHelper.TABLE_NAME2,contentValues, myDbHelper.WID+" = ?",whereArgs );
        db.close();
        return count;
    }

    public int updateMoney(String newMoney, String oldId){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(myDbHelper.START_GOAL, newMoney);

        String[] whereArgs = {oldId};
        int count = db.update(myDbHelper.TABLE_NAME1,contentValues, myDbHelper.UID +" = ?",whereArgs);
        db.close();
        return count;

    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "dbMoneyGrow";    // Database Name

        private static final String TABLE_NAME1 = "goalTable";   // Table1 Name
        private static final String TABLE_NAME2 = "walletTable"; // Table2 Name

        private static final int DATABASE_Version = 1;    // Database Version

        // Value of goalTable
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String IMAGE_GOAL = "Image_goal";    //Column II
        private static final String TITLE_GOAL = "Title_goal";    //Column III
        private static final String START_GOAL = "Start_goal";    //Column IV
        private static final String AMOUNT_GOAL= "Amount_goal";    // Column V
        private static final String DESC_GOAL= "Desc_goal";    // Column VI
        private static final String DATE_GOAL= "Date_goal";    // Column VII

        // Value of walletTable
        private static final String WID="_id";     // Column I (Primary Key)
        private static final String CAT_NAME_WALLET = "Title_wallet";    //Column II
        private static final String DATE_WALLET = "Date_wallet";    //Column III
        private static final String NOTE_WALLET= "Desc_wallet";    // Column IV
        private static final String IMAGE_WALLET= "Img_wallet";    // Column V
        private static final String AMOUNT_WALLET= "Amount_wallet";    // Column VI

        // Create goalTable
        private static final String CREATE_TABLE1 = "CREATE TABLE "+TABLE_NAME1+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ IMAGE_GOAL +" VARCHAR(255) ,"
                +TITLE_GOAL+" VARCHAR(255) ," + START_GOAL +" VARCHAR(255) ,"
                + AMOUNT_GOAL +" VARCHAR(255) ," + DESC_GOAL +" VARCHAR(255) ,"
                + DATE_GOAL +" VARCHAR(225));";

        // Create walletTable
        private static final String CREATE_TABLE2 = "CREATE TABLE "+TABLE_NAME2+
                " ("+WID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAT_NAME_WALLET+" VARCHAR(255) ,"
                + DATE_WALLET +" VARCHAR(255) ," + NOTE_WALLET +" VARCHAR(255) ," + IMAGE_WALLET +" VARCHAR(255) ,"
                + AMOUNT_WALLET +" VARCHAR(225));";

        private static final String DROP_TABLE1 ="DROP TABLE IF EXISTS "+TABLE_NAME1;
        private static final String DROP_TABLE2 ="DROP TABLE IF EXISTS "+TABLE_NAME2;

        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE1);
                db.execSQL(CREATE_TABLE2);
            } catch (Exception e) {

            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE1);
                db.execSQL(DROP_TABLE2);
                onCreate(db);
            }catch (Exception e) {
            }
        }
    }
}


