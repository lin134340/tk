package com.tk;

import com.tk.db.DbHelper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class DuoxuanActivity extends Activity {

	private int qid = 1;
	private String question;
	private String answer;
	private String[] options;
	Button btn_prev, btn_goto, btn_next, btn_submit, btn_answer;
	CheckBox check_a, check_b, check_c, check_d;
	TextView text_question, text_answer;

	public DuoxuanActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_duoxuan);
		Log.i("DuoxuanActivity", "here");
		if (Global.random) {
			qid = Global.random_list[Global.pRandom];
		} else {
			qid = DbHelper.getRecord("duoxuan");
		}

		Cursor localCursor = DbHelper.getTimu("duoxuan", qid);
		if (localCursor.moveToNext()) {
			question = localCursor.getString(localCursor
					.getColumnIndex("question"));
			answer = localCursor
					.getString(localCursor.getColumnIndex("answer"));
			options = localCursor.getString(
					localCursor.getColumnIndex("options")).split("\\|");
		}

		btn_prev = (Button) findViewById(R.id.btn_duoxuan_prev);
		btn_goto = (Button) findViewById(R.id.btn_duoxuan_goto);
		btn_next = (Button) findViewById(R.id.btn_duoxuan_next);
		btn_submit = (Button) findViewById(R.id.btn_duoxuan_submit);
		btn_answer = (Button) findViewById(R.id.btn_duoxuan_answer);
		check_a = (CheckBox) findViewById(R.id.check_duoxuan_a);
		check_b = (CheckBox) findViewById(R.id.check_duoxuan_b);
		check_c = (CheckBox) findViewById(R.id.check_duoxuan_c);
		check_d = (CheckBox) findViewById(R.id.check_duoxuan_d);
		text_question = (TextView) findViewById(R.id.text_duoxuan_question);
		text_answer = (TextView) findViewById(R.id.text_duoxuan_answer);

		text_question.setText(question);
		if (Global.random) {
			text_question.setText("(R" + String.valueOf(Global.pRandom + 1)
					+ "/" + String.valueOf(Global.random_list.length) + ") "
					+ text_question.getText());
		}
		check_a.setText(getString(R.string.str_a) + " " + options[0]);
		check_b.setText(getString(R.string.str_b) + " " + options[1]);
		check_c.setText(getString(R.string.str_c) + " " + options[2]);
		check_d.setText(getString(R.string.str_d) + " " + options[3]);
		text_answer.setText(answer);
		text_answer.setVisibility(View.INVISIBLE);

		btn_submit.setOnClickListener(new checkAnswerListener());
		btn_answer.setOnClickListener(new showAnswerListener());
		btn_prev.setOnClickListener(new prevListener());
		btn_goto.setOnClickListener(new gotoListener());
		btn_next.setOnClickListener(new nextListener());

		if (!Global.random && qid == 1) {
			btn_prev.setClickable(false);
			btn_prev.setTextColor(android.graphics.Color.GRAY);
		} else if (!Global.random && qid == Global.max_qid) {
			btn_next.setClickable(false);
			btn_next.setTextColor(android.graphics.Color.GRAY);
		} else {
			btn_prev.setClickable(true);
			btn_prev.setTextColor(android.graphics.Color.BLACK);
			btn_next.setClickable(true);
			btn_next.setTextColor(android.graphics.Color.BLACK);
		}

	}

	private class checkAnswerListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			if ((!(answer.contains("A") ^ check_a.isChecked()))
					&& (!(answer.contains("B") ^ check_b.isChecked()))
					&& (!(answer.contains("C") ^ check_c.isChecked()))
					&& (!(answer.contains("D") ^ check_d.isChecked()))) {
				text_answer.setText(getString(R.string.str_right_answer));
				text_answer.setTextColor(android.graphics.Color.GREEN);
			} else {
				text_answer.setText(getString(R.string.str_wrong_answer));
				text_answer.setTextColor(android.graphics.Color.RED);
			}
			text_answer.setVisibility(View.VISIBLE);
		}
	}

	private class showAnswerListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			text_answer.setText(getString(R.string.str_answer_prefix) + answer);
			text_answer.setTextColor(android.graphics.Color.BLACK);
			text_answer.setVisibility(View.VISIBLE);
		}
	}

	private class prevListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			Log.i("prevListener", "here");
			if (Global.random) {
				Global.pRandom = (Global.pRandom + Global.random_list.length - 1)
						% Global.random_list.length;
			} else {
				DbHelper.updatetRecord("duoxuan", qid - 1);
			}
			Intent i = new Intent();
			i.setClass(DuoxuanActivity.this, DuoxuanActivity.class);
			DuoxuanActivity.this.startActivity(i);
			finish();
		}
	}

	private class nextListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			if (Global.random) {
				Global.pRandom = (Global.pRandom + 1)
						% Global.random_list.length;
			} else {
				DbHelper.updatetRecord("duoxuan", qid + 1);
			}
			Intent i = new Intent();
			i.setClass(DuoxuanActivity.this, DuoxuanActivity.class);
			DuoxuanActivity.this.startActivity(i);
			finish();
		}
	}

	@SuppressLint("InflateParams")
	private class gotoListener implements OnClickListener {

		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			final View localView = LayoutInflater.from(DuoxuanActivity.this)
					.inflate(R.layout.activity_goto, null);

			new AlertDialog.Builder(DuoxuanActivity.this)
					.setView(localView)
					.setTitle(
							getString(R.string.str_goto_number)
									+ Global.max_qid)
					.setPositiveButton(
							getString(R.string.str_confirm),
							new android.content.DialogInterface.OnClickListener() {

								public void onClick(DialogInterface arg0,
										int arg1) {
									String str = ((EditText) localView
											.findViewById(R.id.edit_goto))
											.getText().toString().trim();
									if (!str.equals("")) {
										int qi = Integer.parseInt(str);
										if ((qi >= 1) && (qi <= Global.max_qid)) {
											if (Global.random) {
												Global.pRandom = qi - 1;
											} else {
												DbHelper.updatetRecord(
														"duoxuan", qi);
											}
										}
									}
									// TODO Auto-generated method stub
									Intent i = new Intent();
									i.setClass(DuoxuanActivity.this,
											DuoxuanActivity.class);
									DuoxuanActivity.this.startActivity(i);
									finish();
									return;
								}

							}).show();
		}

	}

}
