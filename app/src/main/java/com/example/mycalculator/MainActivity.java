package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Button> buttonList = new ArrayList<>();

        buttonList.add((Button) findViewById(R.id.b0));

        buttonList.add((Button) findViewById(R.id.b1));

        buttonList.add((Button) findViewById(R.id.b2));

        buttonList.add((Button) findViewById(R.id.b3));

        buttonList.add((Button) findViewById(R.id.b4));

        buttonList.add((Button) findViewById(R.id.b5));

        buttonList.add((Button) findViewById(R.id.b6));

        buttonList.add((Button) findViewById(R.id.b7));

        buttonList.add((Button) findViewById(R.id.b8));

        buttonList.add((Button) findViewById(R.id.b9));

        buttonList.add((Button) findViewById(R.id.bplus));

        buttonList.add((Button) findViewById(R.id.bsub));

        buttonList.add((Button) findViewById(R.id.bmul));

        buttonList.add((Button) findViewById(R.id.bdev));

        buttonList.add((Button) findViewById(R.id.bkanma));

        buttonList.add((Button) findViewById(R.id.bbeq));

        buttonList.add((Button) findViewById(R.id.bc));

        buttonList.add((Button) findViewById(R.id.bac));
        ButtonListener listener = new ButtonListener();
        for (Button button : buttonList) {
            button.setOnClickListener(listener);
        }
    }

    private class ButtonListener implements View.OnClickListener {

        private List<BigDecimal> _numList = new ArrayList<>();
        private List<Character> _opeList = new ArrayList<>();
        private String _inputValue = "";

        @Override
        public void onClick(View view) {
            TextView main_number_tv = findViewById(R.id.main_number_tv);

            int btId = view.getId();
            char inputChar;
            switch (btId) {

                case R.id.b0:
                    inputChar = '0';
                    addTextView(main_number_tv, inputChar);

                    _inputValue += inputChar;
                    break;

                case R.id.b1:
                    inputChar = '1';
                    addTextView(main_number_tv, inputChar);

                    _inputValue += inputChar;
                    break;

                case R.id.b2:
                    inputChar = '2';
                    addTextView(main_number_tv, inputChar);

                    _inputValue += inputChar;
                    break;

                case R.id.b3:
                    inputChar = '3';
                    addTextView(main_number_tv, inputChar);

                    _inputValue += inputChar;
                    break;

                case R.id.b4:
                    inputChar = '4';
                    addTextView(main_number_tv, inputChar);

                    _inputValue += inputChar;
                    break;

                case R.id.b5:
                    inputChar = '5';
                    addTextView(main_number_tv, inputChar);

                    _inputValue += inputChar;
                    break;

                case R.id.b6:
                    inputChar = '6';
                    addTextView(main_number_tv, inputChar);

                    _inputValue += inputChar;
                    break;

                case R.id.b7:
                    inputChar = '7';
                    addTextView(main_number_tv, inputChar);

                    _inputValue += inputChar;
                    break;

                case R.id.b8:
                    inputChar = '8';
                    addTextView(main_number_tv, inputChar);

                    _inputValue += inputChar;
                    break;

                case R.id.b9:
                    inputChar = '9';
                    addTextView(main_number_tv, inputChar);

                    _inputValue += inputChar;
                    break;

                case R.id.bplus:
                    inputChar = '+';
                    if (!(_inputValue.equals(""))) {
                        addList(main_number_tv, _inputValue, inputChar);
                    }
                    break;

                case R.id.bsub:
                    inputChar = '-';
                    if (!(_inputValue.equals(""))) {
                        addList(main_number_tv, _inputValue, inputChar);
                    }
                    break;

                case R.id.bmul:
                    inputChar = '×';
                    if (!(_inputValue.equals(""))) {
                        addList(main_number_tv, _inputValue, inputChar);
                    }
                    break;

                case R.id.bdev:
                    inputChar = '÷';
                    if (!(_inputValue.equals(""))) {
                        addList(main_number_tv, _inputValue, inputChar);
                    }
                    break;

                case R.id.bbeq:
                    inputChar = '=';
                    if (!(_inputValue.equals(""))) {
                        addList(main_number_tv, _inputValue, inputChar);
                    }
                    String result = calculate().toString();
                    main_number_tv.setText(result);
                    _inputValue = result;
                    _numList.clear();
                    _opeList.clear();
                    break;

                case R.id.bac:
                    main_number_tv.setText("");
                    _numList.clear();
                    _opeList.clear();
                    _inputValue = "";
                    break;
                case R.id.bc:
                    String formulaStr = main_number_tv.getText().toString();
                    char formulaStrLastChar = formulaStr.charAt(formulaStr.length() - 1);
                    if (isFourArithmeticOpe(formulaStrLastChar)) {
                        _opeList.remove(_opeList.size() - 1);
                    }
                    if (!formulaStr.isEmpty()) {
                        main_number_tv.setText(formulaStr.subSequence(0, formulaStr.length() - 1));
                    }
                    if (!_inputValue.isEmpty()) {
                        _inputValue = _inputValue.substring(0, _inputValue.length() - 1);
                    }
                    break;
                case R.id.bkanma:
                    inputChar = '.';
                    addTextView(main_number_tv, inputChar);
                    _inputValue += inputChar;
                    break;
            }
        }

        private void addList(TextView tvFormula, String inputValue, char ope) {
            addTextView(tvFormula, ope);
            _numList.add(new BigDecimal(inputValue));
            _opeList.add(ope);
            _inputValue = "";
        }

        private void addTextView(TextView textView, char addStr) {
            textView.setText(textView.getText().toString() + addStr);
        }

        private BigDecimal calculate() {
            int i = 0;
            while (i < _opeList.size()) {
                if (_opeList.get(i) == '×' | _opeList.get(i) == '÷') {
                    BigDecimal resultMultiDiv = _opeList.get(i) == '×' ? _numList.get(i).multiply(_numList.get(i + 1)) : _numList.get(i).divide(_numList.get(i + 1));
                    _numList.set(i, resultMultiDiv);
                    _numList.remove(i + 1);
                    _opeList.remove(i);
                    i--;
                } else if (_opeList.get(i) == '-') {
                    _opeList.set(i, '+');
                    _numList.set(i + 1, _numList.get(i + 1).negate());
                }
                i++;
            }
            BigDecimal result = new BigDecimal("0");
            for (BigDecimal j : _numList) {
                result = result.add(j);
            }
            return result;
        }

        private boolean isFourArithmeticOpe(char c) {
            if (c == '+' | c == '-' | c == '*' | c == '/') return true;
            return false;
        }
    }
}