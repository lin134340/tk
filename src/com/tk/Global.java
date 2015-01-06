package com.tk;

import com.tk.db.asset.AssetsDatabaseManager;

import android.database.sqlite.SQLiteDatabase;

public class Global {

	public static SQLiteDatabase db = null;
	public static AssetsDatabaseManager mg = null;
	public static int tk_code = 0;
	public static boolean[] tk_suzhi = {};
	public static boolean[] tk_qiye = {};
	public static boolean[] tk_jiben = {};
	public static boolean[] tk_zhuying = {};
	public static boolean[] tk_zhuanye = {};
	public static boolean random = false;
	public static int[] random_list = {};
	public static int pRandom = 0;
	public static int max_qid = 0;

	public Global() {
		// TODO Auto-generated constructor stub
	}

}
