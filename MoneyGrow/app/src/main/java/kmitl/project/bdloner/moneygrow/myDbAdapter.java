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

    public long insertData(String title, String start, String amount, String desc, String date)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.TITLE_GOAL, title);
        contentValues.put(myDbHelper.START_GOAL, start);
        contentValues.put(myDbHelper.AMOUNT_GOAL, amount);
        contentValues.put(myDbHelper.DESC_GOAL, desc);
        contentValues.put(myDbHelper.DATE_GOAL, date);
        long id = db.insert(myDbHelper.TABLE_NAME, null , contentValues);
        db.close();
        return id;
    }

    public List<List> getData()
    {
        SQLiteDatabase db = myhelper.getReadableDatabase();
        List<List> datas = new ArrayList<>();
        String[] columns = {myDbHelper.UID, myDbHelper.TITLE_GOAL, myDbHelper.START_GOAL,
                myDbHelper.AMOUNT_GOAL, myDbHelper.DESC_GOAL, myDbHelper.DATE_GOAL};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        //StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            List<String> data = new ArrayList<>();
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String title =cursor.getString(cursor.getColumnIndex(myDbHelper.TITLE_GOAL));
            String start =cursor.getString(cursor.getColumnIndex(myDbHelper.START_GOAL));
            String amount =cursor.getString(cursor.getColumnIndex(myDbHelper.AMOUNT_GOAL));
            String desc =cursor.getString(cursor.getColumnIndex(myDbHelper.DESC_GOAL));
            String date =cursor.getString(cursor.getColumnIndex(myDbHelper.DATE_GOAL));
            data.add(title);
            data.add(start);
            data.add(amount);
            data.add(desc);
            data.add(date);
            datas.add(data);
            data.add(String.valueOf(cid));
            //buffer.append(cid+ " " + title + " " + amount + " " + desc + " " + date + " ");
        }
        db.close();
        return datas;
    }

    public  int delete(String cid)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs = {cid};

        int count =db.delete(myDbHelper.TABLE_NAME , myDbHelper.UID+" = ?",whereArgs);
        db.close();
        return  count;
    }

    public int updateDetail(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.TITLE_GOAL,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.TITLE_GOAL+" = ?",whereArgs );
        return count;
    }

    public int updateMoney(String newMoney, String OldId){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(myDbHelper.START_GOAL, newMoney);

        String[] whereArgs = {OldId};
        int count = db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.UID +" = ?",whereArgs);
        db.close();
        return count;

    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "myTable";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String TITLE_GOAL = "Title_goal";    //Column II
        private static final String START_GOAL = "Start_goal";    //Column III
        private static final String AMOUNT_GOAL= "Amount_goal";    // Column IV
        private static final String DESC_GOAL= "Desc_goal";    // Column V
        private static final String DATE_GOAL= "Date_goal";    // Column VI
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+TITLE_GOAL+" VARCHAR(255) ,"
                + START_GOAL +" VARCHAR(255) ," + AMOUNT_GOAL +" VARCHAR(255) ,"
                + DESC_GOAL +" VARCHAR(255) ," + DATE_GOAL +" VARCHAR(225));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {

            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
            }
        }
    }
}


