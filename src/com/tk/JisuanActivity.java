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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class JisuanActivity extends Activity {

	private int qid = 1;
	private String question, q_image = "";
	private String answer, a_image = "";
	Button btn_prev, btn_goto, btn_next, btn_answer;
	TextView text_question, text_answer;
	ImageView image_question, image_answer;

	public JisuanActivity() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jisuan);
		Log.i("JisuanActivity", "here");
		if (Global.random) {
			qid = Global.random_list[Global.pRandom];
		} else {
			qid = DbHelper.getRecord("jisuan");
		}

		Cursor localCursor = DbHelper.getTimu("jisuan", qid);
		if (localCursor.moveToNext()) {
			question = localCursor.getString(localCursor
					.getColumnIndex("question"));
			answer = localCursor
					.getString(localCursor.getColumnIndex("answer"));
			if (question.startsWith("@pic")) {
				q_image = question.substring(1, 8);
				q_image = q_image.replace("/", "_");
				question = question.substring(8);
			}
			if (answer.startsWith("@pic")) {
				a_image = answer.substring(1, 8);
				a_image = a_image.replace("/", "_");
				answer = answer.substring(8);
			}
		}

		btn_prev = (Button) findViewById(R.id.btn_jisuan_prev);
		btn_goto = (Button) findViewById(R.id.btn_jisuan_goto);
		btn_next = (Button) findViewById(R.id.btn_jisuan_next);
		btn_answer = (Button) findViewById(R.id.btn_jisuan_answer);
		text_question = (TextView) findViewById(R.id.text_jisuan_question);
		text_answer = (TextView) findViewById(R.id.text_jisuan_answer);
		image_question = (ImageView) findViewById(R.id.image_jisuan_question);
		image_answer = (ImageView) findViewById(R.id.image_jisuan_answer);
		if (q_image.startsWith("pic")) {
			image_question.setImageResource(getResources().getIdentifier(
					q_image, "drawable", "com.tk"));
		}
		if (a_image.startsWith("pic")) {
			image_answer.setImageResource(getResources().getIdentifier(a_image,
					"drawable", "com.tk"));
		}

		text_question.setText(question);
		text_answer.setText(answer);
		text_answer.setVisibility(View.GONE);
		image_answer.setVisibility(View.GONE);

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

	private class showAnswerListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			text_answer.setText(getString(R.string.str_answer_prefix) + answer);
			text_answer.setTextColor(android.graphics.Color.BLACK);
			text_answer.setVisibility(View.VISIBLE);
			image_answer.setVisibility(View.VISIBLE);
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
				DbHelper.updatetRecord("jisuan", qid - 1);
			}
			Intent i = new Intent();
			i.setClass(JisuanActivity.this, JisuanActivity.class);
			JisuanActivity.this.startActivity(i);
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
				DbHelper.updatetRecord("jisuan", qid + 1);
			}
			Intent i = new Intent();
			i.setClass(JisuanActivity.this, JisuanActivity.class);
			JisuanActivity.this.startActivity(i);
			finish();
		}
	}

	@SuppressLint("InflateParams")
	private class gotoListener implements OnClickListener {

		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			final View localView = LayoutInflater.from(JisuanActivity.this)
					.inflate(R.layout.activity_goto, null);

			new AlertDialog.Builder(JisuanActivity.this)
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
												for (int i = 0; i < Global.random_list.length; i++) {
													if (Global.random_list[i] == qi) {
														Global.pRandom = i;
														break;
													}
												}
											} else {
												DbHelper.updatetRecord(
														"jisuan", qi);
											}
										}
									}
									// TODO Auto-generated method stub
									Intent i = new Intent();
									i.setClass(JisuanActivity.this,
											JisuanActivity.class);
									JisuanActivity.this.startActivity(i);
									finish();
									return;
								}

							}).show();
		}

	}
}