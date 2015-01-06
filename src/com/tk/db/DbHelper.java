package com.tk.db;

import android.database.Cursor;
import android.util.Log;
import com.tk.Global;

public class DbHelper {

	public DbHelper() {
		// TODO Auto-generated constructor stub
	}

	public static void selectDb(int tk_code) {
		Log.i("selectDb", "tk_code-->>" + tk_code);
		switch (tk_code) {
		case 1:
			Global.db = Global.mg.getDatabase("tiku_sz.db");
			break;
		case 2:
			Global.db = Global.mg.getDatabase("tiku_qy.db");
			break;
		case 3:
			Global.db = Global.mg.getDatabase("tiku_jb.db");
			break;
		case 4:
			Global.db = Global.mg.getDatabase("tiku_zy.db");
			break;
		case 5:
			Global.db = Global.mg.getDatabase("tiku.db");
			break;
		default:
			Log.i("selectDb", "selectDb error, no db found.");
		}
	}

	public static Cursor getTimu(String table, int id) {
		String[] args = { String.valueOf(id) };
		return Global.db.query(table, null, "id=?", args, null, null, null);
	}

	public static int getCount(String table) {
		String str = "select count(*) as count from " + table;
		Cursor localCursor = Global.db.rawQuery(str, null);
		localCursor.moveToNext();
		return localCursor.getInt(localCursor.getColumnIndex("count"));
	}

	public static int getRecord(String qtype) {
		String str = "select c_number from record where tixing='" + qtype + "'";
		Cursor localCursor = Global.db.rawQuery(str, null);
		localCursor.moveToNext();
		return localCursor.getInt(localCursor.getColumnIndex("c_number"));
	}

	public static void updatetRecord(String qtype, int recNum) {
		String str = "update record set c_number=" + recNum + " where tixing='"
				+ qtype + "'";
		try {
			Global.db.execSQL(str);
			return;
		} catch (Exception localException) {
			Log.i("updatetRecord", "update record failed!");
		}
	}

}
