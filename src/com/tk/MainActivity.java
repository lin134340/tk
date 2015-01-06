package com.tk;

import java.util.Random;

import com.tk.db.DbHelper;
import com.tk.db.asset.AssetsDatabaseManager;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private MainActivity mainActivity;
	private Button button_danxuan;
	private Button button_duoxuan;
	private Button button_exit;
	private Button button_jianda;
	private Button button_jisuan;
	private Button button_lunshu;
	private Button button_panduan;
	private Button button_random;
	private Button button_seldb;
	private Button button_tiankong;
	private Button button_tuxiang;
	private TextView text_dbname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.e("MainActivity", "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.mainActivity = this;
		button_danxuan = (Button) findViewById(R.id.btn_danxuan);
		button_duoxuan = (Button) findViewById(R.id.btn_duoxuan);
		button_tiankong = (Button) findViewById(R.id.btn_tiankong);
		button_panduan = (Button) findViewById(R.id.btn_panduan);
		button_jianda = (Button) findViewById(R.id.btn_jianda);
		button_lunshu = (Button) findViewById(R.id.btn_lunshu);
		button_jisuan = (Button) findViewById(R.id.btn_jisuan);
		button_tuxiang = (Button) findViewById(R.id.btn_tuxiang);
		button_random = (Button) findViewById(R.id.btn_random);
		button_exit = (Button) findViewById(R.id.btn_exit);
		button_seldb = (Button) findViewById(R.id.btn_seldb);
		text_dbname = (TextView) findViewById(R.id.text_dbname);

		button_danxuan.setOnClickListener(new btn_danxuanListener());
		button_duoxuan.setOnClickListener(new btn_duoxuanListener());
		button_tiankong.setOnClickListener(new btn_tiankongListener());
		button_panduan.setOnClickListener(new btn_panduanListener());
		button_jianda.setOnClickListener(new btn_jiandaListener());
		button_lunshu.setOnClickListener(new btn_lunshuListener());
		button_jisuan.setOnClickListener(new btn_jisuanListener());
		button_tuxiang.setOnClickListener(new btn_tuxiangListener());
		button_seldb.setOnClickListener(new btn_seldbListener());
		button_random.setOnClickListener(new btn_randomListener());
		button_exit.setOnClickListener(new btn_exitListener());

		// 初始化数据库
		AssetsDatabaseManager.initManager(getApplication());
		Log.i("Global.mg", String.valueOf(Global.mg));
		Global.mg = AssetsDatabaseManager.getManager();
		Global.tk_code = 1;
		DbHelper.selectDb(Global.tk_code);
		text_dbname.setText(getString(R.string.db_suzhi));
		Global.random = false;
		Global.tk_suzhi = new boolean[] { true, true, true, true, true, true,
				false, false };
		Global.tk_qiye = new boolean[] { true, true, true, true, true, true,
				false, false };
		Global.tk_jiben = new boolean[] { true, true, true, true, true, true,
				true, false };
		Global.tk_zhuying = new boolean[] { true, true, true, true, true, true,
				true, false };
		Global.tk_zhuanye = new boolean[] { true, true, true, true, true, true,
				true, true };
		setBtnClickable(Global.tk_suzhi);

	}

	private class btn_danxuanListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, DanxuanActivity.class);
			// intent.putExtra("random", false);
			Global.random = false;
			Global.max_qid = DbHelper.getCount("danxuan");
			MainActivity.this.startActivity(intent);
		}
	}

	private class btn_duoxuanListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, DuoxuanActivity.class);
			// intent.putExtra("random", false);
			Global.random = false;
			Global.max_qid = DbHelper.getCount("duoxuan");
			MainActivity.this.startActivity(intent);
		}
	}

	private class btn_tiankongListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, TiankongActivity.class);
			// intent.putExtra("random", false);
			Global.random = false;
			Global.max_qid = DbHelper.getCount("tiankong");
			MainActivity.this.startActivity(intent);
		}
	}

	private class btn_panduanListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, PanduanActivity.class);
			// intent.putExtra("random", false);
			Global.random = false;
			Global.max_qid = DbHelper.getCount("panduan");
			MainActivity.this.startActivity(intent);
		}
	}

	private class btn_jiandaListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, JiandaActivity.class);
			// intent.putExtra("random", false);
			Global.random = false;
			Global.max_qid = DbHelper.getCount("jianda");
			MainActivity.this.startActivity(intent);
		}
	}

	private class btn_lunshuListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, LunshuActivity.class);
			// intent.putExtra("random", false);
			Global.random = false;
			Global.max_qid = DbHelper.getCount("lunshu");
			MainActivity.this.startActivity(intent);
		}
	}

	private class btn_jisuanListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, JisuanActivity.class);
			// intent.putExtra("random", false);
			Global.random = false;
			Global.max_qid = DbHelper.getCount("jisuan");
			MainActivity.this.startActivity(intent);
		}
	}

	private class btn_tuxiangListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, TuxiangActivity.class);
			// intent.putExtra("random", false);
			Global.random = false;
			Global.max_qid = DbHelper.getCount("tuxiang");
			MainActivity.this.startActivity(intent);
		}
	}

	private class btn_randomListener implements OnClickListener {

		private int[] genRandomList(int c) {
			if (c == 0) {
				Log.i("genRandomList", "error c==0");
				return null;
			}
			int[] sequence = new int[c];
			for (int i = 0; i < c; i++) {
				sequence[i] = i + 1;
			}
			Random random = new Random();
			for (int i = 0; i < c; i++) {
				int p = random.nextInt(c);
				int tmp = sequence[i];
				sequence[i] = sequence[p];
				sequence[p] = tmp;
			}
			random = null;
			return sequence;
		}

		private class randomDialogListener implements
				DialogInterface.OnClickListener {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Log.i("random", "arg1-->>" + arg1);
				Intent intent = new Intent();
				int qCount = 0;
				switch (arg1 + 1) {
				case 1:
					intent.setClass(MainActivity.this, DanxuanActivity.class);
					qCount = DbHelper.getCount("danxuan");
					break;
				case 2:
					intent.setClass(MainActivity.this, DuoxuanActivity.class);
					qCount = DbHelper.getCount("duoxuan");
					break;
				case 3:
					intent.setClass(MainActivity.this, TiankongActivity.class);
					qCount = DbHelper.getCount("tiankong");
					break;
				case 4:
					intent.setClass(MainActivity.this, PanduanActivity.class);
					qCount = DbHelper.getCount("panduan");
					break;
				default:
					Log.i("randomDialogListener", "randomDialogListener error.");
					return;
				}
				Log.i("random", "qCount-->>" + qCount);
				Global.max_qid = qCount;
				Global.random_list = genRandomList(qCount);
				Global.pRandom = 0;
				Global.random = true;
				MainActivity.this.startActivity(intent);
			}
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			new AlertDialog.Builder(MainActivity.this.mainActivity).setItems(
					new String[] { "单选题", "多选题", "填空题", "判断题" },
					new randomDialogListener()).show();
		}
	}

	private class btn_seldbListener implements OnClickListener {

		private class seldbDialogListener implements
				DialogInterface.OnClickListener {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Log.i("seldb", "arg1-->>" + arg1);
				Global.tk_code = arg1 + 1;
				DbHelper.selectDb(Global.tk_code);
				switch (Global.tk_code) {
				case 1:
					text_dbname.setText(getString(R.string.db_suzhi));
					setBtnClickable(Global.tk_suzhi);
					break;
				case 2:
					text_dbname.setText(getString(R.string.db_qiye));
					setBtnClickable(Global.tk_qiye);
					break;
				case 3:
					text_dbname.setText(getString(R.string.db_jiben));
					setBtnClickable(Global.tk_jiben);
					break;
				case 4:
					text_dbname.setText(getString(R.string.db_zhuying));
					setBtnClickable(Global.tk_zhuying);
					break;
				case 5:
					text_dbname.setText(getString(R.string.db_zhuanye));
					setBtnClickable(Global.tk_zhuanye);
					break;
				default:
					Log.i("seldbDialogListener",
							"seldbDialogListener error, no db found.");
					return;
				}
			}
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			new AlertDialog.Builder(MainActivity.this.mainActivity).setItems(
					new String[] { "素质提升", "企业文化", "基本技能", "主营业务", "专业技能" },
					new seldbDialogListener()).show();
		}
	}

	private class btn_exitListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			finish();
		}
	}

	private void setBtnClickable(boolean[] type) {
		button_danxuan.setClickable(type[0]);
		button_duoxuan.setClickable(type[1]);
		button_tiankong.setClickable(type[2]);
		button_panduan.setClickable(type[3]);
		button_jianda.setClickable(type[4]);
		button_lunshu.setClickable(type[5]);
		button_tuxiang.setClickable(type[6]);
		button_jisuan.setClickable(type[7]);
		button_danxuan.setTextColor(type[0] ? android.graphics.Color.BLACK
				: android.graphics.Color.GRAY);
		button_duoxuan.setTextColor(type[1] ? android.graphics.Color.BLACK
				: android.graphics.Color.GRAY);
		button_tiankong.setTextColor(type[2] ? android.graphics.Color.BLACK
				: android.graphics.Color.GRAY);
		button_panduan.setTextColor(type[3] ? android.graphics.Color.BLACK
				: android.graphics.Color.GRAY);
		button_jianda.setTextColor(type[4] ? android.graphics.Color.BLACK
				: android.graphics.Color.GRAY);
		button_lunshu.setTextColor(type[5] ? android.graphics.Color.BLACK
				: android.graphics.Color.GRAY);
		button_tuxiang.setTextColor(type[6] ? android.graphics.Color.BLACK
				: android.graphics.Color.GRAY);
		button_jisuan.setTextColor(type[7] ? android.graphics.Color.BLACK
				: android.graphics.Color.GRAY);

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		Log.e("MainActivity", "onStart");
		super.onStart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.e("MainActivity", "onResume");
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		Log.e("MainActivity", "onPause");
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		Log.e("MainActivity", "onStop");
		super.onStop();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		Log.e("MainActivity", "onRestart");
		super.onRestart();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Log.e("MainActivity", "onDestroy");
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
