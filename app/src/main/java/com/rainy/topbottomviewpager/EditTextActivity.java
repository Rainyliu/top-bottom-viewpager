package com.rainy.topbottomviewpager;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rainy.topbottomviewpager.constants.AppConstants;
import com.rainy.topbottomviewpager.utils.StringUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class EditTextActivity extends BaseActivity {

    private final static int MAX        = 10;
    private int maxlength               = MAX;
    @InjectView(R.id.text_input)
    EditText contentView;
    @InjectView(R.id.tag_input_tips)
    TextView numberTips;

    public static void openTextEdit(Activity mContext, String defaultStr, int maxLength, int reqCode) {
        Intent i = new Intent(mContext, EditTextActivity.class);
        i.putExtra(AppConstants.PARAM_EDIT_TEXT, defaultStr);
        if (maxLength != 0) {
            i.putExtra(AppConstants.PARAM_MAX_SIZE, maxLength);
        }
        mContext.startActivityForResult(i, reqCode);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        ButterKnife.inject(this);
        maxlength = getIntent().getIntExtra(AppConstants.PARAM_MAX_SIZE, MAX);

        String defaultStr = getIntent().getStringExtra(AppConstants.PARAM_EDIT_TEXT);
        if (StringUtils.isNotEmpty(defaultStr)) {
            contentView.setText(defaultStr);
            if (defaultStr.length() <= maxlength) {
                numberTips.setText("你还可以输入" + (maxlength - defaultStr.length()) + "个字  ("
                        + defaultStr.length() + "/" + maxlength + ")");
            }
        }
        titleBar.setRightBtnOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String inputTxt = contentView.getText().toString();
                intent.putExtra(AppConstants.PARAM_EDIT_TEXT, inputTxt);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        contentView.addTextChangedListener(mTextWatcher);
    }

    TextWatcher mTextWatcher = new TextWatcher() {
        private int editStart;
        private int editEnd;

        @Override
        public void beforeTextChanged(CharSequence s, int arg1, int arg2,
                                      int arg3) {
        }

        @Override
        public void onTextChanged(CharSequence s, int arg1, int arg2,
                                  int arg3) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            editStart = contentView.getSelectionStart();
            editEnd = contentView.getSelectionEnd();
            if (s.toString().length() > maxlength) {
                toast("你输入的字数已经超过了限制", Toast.LENGTH_LONG);
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                contentView.setText(s);
                contentView.setSelection(tempSelection);
            }
            numberTips.setText("你还可以输入"
                    + (maxlength - s.toString().length())
                    + "个字  (" + s.toString().length() + "/"
                    + maxlength + ")");
        }
    };
}
