package com.akitektuo.dividemynumber;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import static android.view.View.GONE;
import static java.lang.Double.parseDouble;
import static java.lang.Math.sqrt;
import static java.lang.String.valueOf;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView textCalculator;
    private Preference preference;

    private LinearLayout layoutDivider;
    private EditText editDivider;
    private TextView textDivider;

    private LinearLayout layoutDelta;
    private EditText editDeltaX2;
    private EditText editDeltaX1;
    private EditText editDeltaX0;
    private TextView textDeltaX1;
    private TextView textDeltaX2;
    private CheckBox checkX2;
    private CheckBox checkX1;
    private CheckBox checkX0;
    private TextView textDeltaTitle;
    private TextView textDelta;

    private LinearLayout layoutAverage;
    private EditText editGrades;
    private EditText editSemesterGrade;
    private TextView textAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textCalculator = (TextView) findViewById(R.id.text_calculator_name);
        findViewById(R.id.button_options).setOnClickListener(this);
        preference = new Preference(this);

        layoutDivider = (LinearLayout) findViewById(R.id.layout_divider);
        editDivider = (EditText) findViewById(R.id.edit_divider);
        textDivider = (TextView) findViewById(R.id.text_divider);
        findViewById(R.id.button_calculate_divider).setOnClickListener(this);
        findViewById(R.id.button_copy_divider).setOnClickListener(this);

        layoutDelta = (LinearLayout) findViewById(R.id.layout_delta);
        editDeltaX2 = (EditText) findViewById(R.id.edit_delta_x_2);
        editDeltaX1 = (EditText) findViewById(R.id.edit_delta_x_1);
        editDeltaX0 = (EditText) findViewById(R.id.edit_delta_x_0);
        textDeltaX1 = (TextView) findViewById(R.id.text_delta_x_1);
        textDeltaX2 = (TextView) findViewById(R.id.text_delta_x_2);
        findViewById(R.id.button_calculate_delta).setOnClickListener(this);
        findViewById(R.id.button_copy_delta).setOnClickListener(this);
        checkX2 = (CheckBox) findViewById(R.id.check_delta_x_2);
        checkX1 = (CheckBox) findViewById(R.id.check_delta_x_1);
        checkX0 = (CheckBox) findViewById(R.id.check_delta_x_0);
        findViewById(R.id.layout_delta_check_x_2).setOnClickListener(this);
        findViewById(R.id.layout_delta_check_x_1).setOnClickListener(this);
        findViewById(R.id.layout_delta_check_x_0).setOnClickListener(this);
        checkX2.setOnClickListener(this);
        checkX1.setOnClickListener(this);
        checkX0.setOnClickListener(this);
        editDeltaX2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (checkX2.isChecked() && !editDeltaX2.getText().toString().contains("-")) {
                    editDeltaX2.setText("-" + editDeltaX2.getText().toString());
                }
            }
        });
        editDeltaX1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (checkX1.isChecked() && !editDeltaX1.getText().toString().contains("-")) {
                    editDeltaX1.setText("-" + editDeltaX1.getText().toString());
                }
            }
        });
        editDeltaX0.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (checkX0.isChecked() && !editDeltaX0.getText().toString().contains("-")) {
                    editDeltaX0.setText("-" + editDeltaX0.getText().toString());
                }
            }
        });
        textDeltaTitle = (TextView) findViewById(R.id.text_delta_title);
        textDelta = (TextView) findViewById(R.id.text_delta);

        layoutAverage = (LinearLayout) findViewById(R.id.layout_average);
        editGrades = (EditText) findViewById(R.id.edit_grades);
        editSemesterGrade = (EditText) findViewById(R.id.edit_semester_grade);
        textAverage = (TextView) findViewById(R.id.text_average);
        findViewById(R.id.button_calculate_average).setOnClickListener(this);
        findViewById(R.id.button_clear_average).setOnClickListener(this);
        getLayoutFromPreferences();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_calculate_divider:
                showResult();
                break;
            case R.id.button_copy_divider:
                ClipboardManager clipboardDivider = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clipDivider = ClipData.newPlainText(null, textDivider.getText().toString());
                clipboardDivider.setPrimaryClip(clipDivider);
                Toast.makeText(this, "Result copied to clipboard", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_calculate_delta:
                calculateDelta();
                break;
            case R.id.button_copy_delta:
                ClipboardManager clipboardDelta = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clipDelta = ClipData.newPlainText(null, textDeltaX1.getText().toString() + " " + textDeltaX2.getText().toString());
                clipboardDelta.setPrimaryClip(clipDelta);
                Toast.makeText(this, "Result copied to clipboard", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_options:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Select another calculator:");
                builder.setItems(R.array.calculators, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                launchBaseCalculator();
                                preference.setPreference(Preference.KEY_LAYOUT, 0);
                                break;
                            case 1:
                                preference.setPreference(Preference.KEY_LAYOUT, 1);
                                break;
                            case 2:
                                preference.setPreference(Preference.KEY_LAYOUT, 2);
                                break;
                            case 3:
                                preference.setPreference(Preference.KEY_LAYOUT, 3);
                                break;
                        }
                        getLayoutFromPreferences();
                    }
                });
                builder.setNeutralButton("Cancel", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            case R.id.check_delta_x_2:
                changeSign(2);
                break;
            case R.id.check_delta_x_1:
                changeSign(1);
                break;
            case R.id.check_delta_x_0:
                changeSign(0);
                break;
            case R.id.layout_delta_check_x_2:
                checkX2.setChecked(!checkX2.isChecked());
                changeSign(2);
                break;
            case R.id.layout_delta_check_x_1:
                checkX1.setChecked(!checkX1.isChecked());
                changeSign(1);
                break;
            case R.id.layout_delta_check_x_0:
                checkX0.setChecked(!checkX0.isChecked());
                changeSign(0);
                break;
            case R.id.button_calculate_average:
                calculateAverage();
                break;
            case R.id.button_clear_average:
                editGrades.setText("");
                editSemesterGrade.setText("");
                textAverage.setText("");
                break;
        }
    }

    private void showResult() {
        if (editDivider.getText().toString().isEmpty()) {
            textDivider.setText("");
        } else {
            if (editDivider.getText().toString().length() < 18) {
                textDivider.setText(R.string.calculating);
                long num = Long.parseLong(editDivider.getText().toString()), div = 2, pow = 0;
                String res = "";
                if (num < 2) {
                    textDivider.setText(res);
                    return;
                }
                textDivider.setText(R.string.calculating);
                int count = 0;
                boolean first = false;
                while (num != 1) {
                    while (num % div == 0) {
                        pow++;
                        count++;
                        num /= div;
                    }
                    if (pow > 0) {
                        if (first) {
                            res += " * " + isPrime(div, pow);
                        } else {
                            res += isPrime(div, pow);
                            first = true;
                        }
                    }
                    div++;
                    pow = 0;
                }
                if (count == 1) {
                    res = "Prime";
                }
                textDivider.setText(res);
            } else {
                Toast.makeText(getBaseContext(), "The biggest number has 18 digits.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void calculateDelta() {
        if (checkEmptyDeltaField(editDeltaX2) || checkEmptyDeltaField(editDeltaX1) || checkEmptyDeltaField(editDeltaX0)) {
            textDeltaTitle.setText(R.string.delta);
            textDelta.setText("");
            textDeltaX1.setText("");
            textDeltaX2.setText("");
        } else {
            double a = parseDouble(editDeltaX2.getText().toString()), b = parseDouble(editDeltaX1.getText().toString()),
                    c = parseDouble(editDeltaX0.getText().toString());
            double delta = b * b - (4 * a * c), aDouble = 2 * a, sqrtDelta = sqrt(delta), bNegative = -b;
            if (delta < 0) {
                textDeltaTitle.setText(getString(R.string.delta) + " < 0");
                textDelta.setText(valueOf(delta));
                textDeltaX1.setText("");
                textDeltaX2.setText("");
            } else if (delta == 0) {
                double res = bNegative / aDouble;
                textDeltaTitle.setText(getString(R.string.delta) + " = 0");
                textDelta.setText(valueOf(delta));
                textDeltaX1.setText(valueOf(res));
                textDeltaX2.setText(valueOf(res));
            } else {
                textDeltaTitle.setText(getString(R.string.delta) + " > 0");
                textDelta.setText(valueOf(delta));
                double x1 = bNegative + sqrtDelta, res1 = x1 / aDouble;
                double x2 = bNegative - sqrtDelta, res2 = x2 / aDouble;
                if (res1 > res2) {
                    textDeltaX1.setText(valueOf(res2));
                    textDeltaX2.setText(valueOf(res1));
                } else {
                    textDeltaX1.setText(valueOf(res1));
                    textDeltaX2.setText(valueOf(res2));
                }
            }
        }
    }

    private String isPrime(long div, long pow) {
        if (pow > 1) {
            return div + "^" + pow;
        }
        return div + "";
    }

    public void launchBaseCalculator() {
        Intent intentCalculator = new Intent();
        intentCalculator.setAction(Intent.ACTION_MAIN);
        intentCalculator.addCategory(Intent.CATEGORY_APP_CALCULATOR);
        intentCalculator.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intentCalculator);
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(this, "This function is not compatible with your device.", Toast.LENGTH_SHORT).show();
        }
    }

    private void getLayoutFromPreferences() {
        switch (preference.getPreferenceInt(Preference.KEY_LAYOUT)) {
            case 0:
                textCalculator.setText(R.string.base);
                layoutDivider.setVisibility(GONE);
                layoutDelta.setVisibility(GONE);
                layoutAverage.setVisibility(GONE);
                break;
            case 1:
                textCalculator.setText(R.string.divider);
                layoutDivider.setVisibility(View.VISIBLE);
                layoutDelta.setVisibility(GONE);
                layoutAverage.setVisibility(GONE);
                break;
            case 2:
                textCalculator.setText(R.string.delta);
                layoutDivider.setVisibility(GONE);
                layoutDelta.setVisibility(View.VISIBLE);
                layoutAverage.setVisibility(GONE);
                break;
            case 3:
                textCalculator.setText(R.string.average);
                layoutDivider.setVisibility(GONE);
                layoutDelta.setVisibility(GONE);
                layoutAverage.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void changeSign(int whichField) {
        switch (whichField) {
            case 2:
                String numberX2 = editDeltaX2.getText().toString();
                if (numberX2.contains("-")) {
                    editDeltaX2.setText(numberX2.substring(1));
                } else {
                    editDeltaX2.setText("-" + numberX2);
                }
                break;
            case 1:
                String numberX1 = editDeltaX1.getText().toString();
                if (numberX1.contains("-")) {
                    editDeltaX1.setText(numberX1.substring(1));
                } else {
                    editDeltaX1.setText("-" + numberX1);
                }
                break;
            case 0:
                String numberX0 = editDeltaX0.getText().toString();
                if (numberX0.contains("-")) {
                    editDeltaX0.setText(numberX0.substring(1));
                } else {
                    editDeltaX0.setText("-" + numberX0);
                }
                break;
        }
    }

    private boolean checkEmptyDeltaField(EditText constantField) {
        String text = constantField.getText().toString();
        return text.isEmpty() || text.equals("-");
    }

    private void calculateAverage() {
        String[] grades = rearrangeGrades().split(" ");
        double sum = 0;
        for (String x : grades) {
            sum += Integer.parseInt(x);
        }
        sum = sum / grades.length;
        if (editSemesterGrade.getText().toString().isEmpty()) {
            textAverage.setText(String.valueOf(new DecimalFormat("#.####").format(sum)));
        } else {
            int semesterGrade = Integer.parseInt(editSemesterGrade.getText().toString());
            if (semesterGrade > 0 && semesterGrade < 11) {
                textAverage.setText(String.valueOf(new DecimalFormat("#.####").format((sum * 3 + semesterGrade) / 4)));
            } else {
                Toast.makeText(this, "Grades have to be less than 10", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String rearrangeGrades() {
        String grades = editGrades.getText().toString();
        if (grades.isEmpty()) {
            Toast.makeText(this, "Empty field", Toast.LENGTH_SHORT).show();
            return "";
        }
        grades = grades.replaceAll(" ", "");
        for (int i = 1; i < grades.length(); i++) {
            if (!(grades.substring(i, i + 1).equals("0") && grades.substring(i - 1, i).equals("1"))) {
                if (grades.substring(i, i + 1).equals("0")) {
                    grades = grades.substring(0, i) + grades.substring(i + 1, grades.length());
                    i--;
                } else {
                    grades = grades.substring(0, i) + " " + grades.substring(i, grades.length());
                    i++;
                }
            }
        }
        if (grades.startsWith("0")) {
            grades = grades.substring(2, grades.length());
        }
        editGrades.setText(grades);
        return grades;
    }
}
