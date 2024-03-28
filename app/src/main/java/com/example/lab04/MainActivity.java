package com.example.lab04;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    TextView lr;
    CheckBox[] chk = new CheckBox[4];
    EditText[] num = new EditText[4];
    EditText[] price = new EditText[4];
    TextView[] pTot = new TextView[4];
    int i = 0;
    float sum = 0.0f;
    boolean isDialog = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lr = findViewById(R.id.txt);

        num[0] = findViewById(R.id.txt_1);
        num[1] = findViewById(R.id.txt_2);
        num[2] = findViewById(R.id.txt_3);
        num[3] = findViewById(R.id.txt_4);

        price[0] = findViewById(R.id.pr_1);
        price[1] = findViewById(R.id.pr_2);
        price[2] = findViewById(R.id.pr_3);
        price[3] = findViewById(R.id.pr_4);

        pTot[0] = findViewById(R.id.pT_1);
        pTot[1] = findViewById(R.id.pT_2);
        pTot[2] = findViewById(R.id.pT_3);
        pTot[3] = findViewById(R.id.pT_4);


        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        RadioButton radioButtonDialog = findViewById(R.id.radioButtonDialog);
        RadioButton radioButtonToast = findViewById(R.id.radioButtonToast);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == radioButtonDialog.getId()) {
                    // выбран вывод через диалоговое окно
                    isDialog = true;
                } else if (checkedId == radioButtonToast.getId()) {
                    // выбран вывод через Toast
                    isDialog = false;
                }
            }
        });
    }

private void showDialogWithIcon(String message) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Информация") // устанавливаем заголовок
            .setMessage(message)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            })
            .setIcon(R.drawable.heardic); // добавляем иконку

    AlertDialog dialog = builder.create();
    dialog.show();
}
    public void onCalc(View v) {

        chk[0] = findViewById(R.id.cB_1);
        chk[1] = findViewById(R.id.cB_2);
        chk[2] = findViewById(R.id.cB_3);
        chk[3] = findViewById(R.id.cB_4);

        sum = 0.0f;

        for(i = 0; i < 4; i++)
        {
            if (chk[i].isChecked()) {
                int q;
                int p;
                try {
                    q = Integer.parseInt(num[i].getText().toString());
                    p = Integer.parseInt(price[i].getText().toString());
                } catch (NumberFormatException e) {
                    lr.setText("Ошибка: введите числовые значения.");
                    return;
                }
                if (q <= 0 || p <= 0) {
                    lr.setText("Ошибка: введите положительные значения.");
                    return;
                }
                float val = q * p;
                String vvv = String.valueOf(val);
                pTot[i].setText(vvv);
                sum += val;

                if (isDialog) {
                    // вывод информации через диалоговое окно
                    showDialogWithIcon("Общая сумма: " + sum);
                } else {
                    // вывод информации через Toast
                    Toast.makeText(this, "Общая сумма: " + sum, Toast.LENGTH_SHORT).show();
                }
            }
            }
    }
}