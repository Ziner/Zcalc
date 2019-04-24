package com.bulletn.zcalc;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Objects;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;


public class MainActivity extends AppCompatActivity {

    EditText formula;                                                // Edit Создаем переменную, не инициализируем
    TextView resText;                                                // Result
    String result = "";                                              // Создаем стринг переменную и присваиваем з

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        formula = findViewById(R.id.Formula);                        // Инициализируем переменную
        resText = findViewById(R.id.Result);
    }


    ////////////// Равно //////////////

    @SuppressLint("SetTextI18n")
    public void Calculate(View v) {

        if (formula.getText().toString().isEmpty()) {
            return;
        }

        Calculable calc = null;
        try {
            calc = new ExpressionBuilder(formula.getText().toString()).build();
        } catch (UnknownFunctionException e) {
            e.printStackTrace();
        } catch (UnparsableExpressionException e) {
            e.printStackTrace();
        }
        double result = calc.calculate();

//        int num1 = Integer.parseInt(formula.getText().toString());   // Переобразовуем в числа
//        int resSum = num1 + num1;                                    // Любая формула
        resText.setText(String.valueOf(result));                   // Переобразовуем в строку

        formula.setText(String.valueOf(result));                   // Перемещаем ответ в поле формулы
    }




    ////////////// Очистка //////////////

    public void Delete(View view) {
        formula.setText("");                                         // Очищаем поле для ввода
        resText.setText("");                                         // Очищаем поле с ответом
        result = "";
    }


    ////////////// Цифры //////////////

    public void One(View view) {
        formula.setText(getValue("1"));                       // Вводим число 1
    }

    public void Two(View view) {
        formula.setText(getValue("2"));                       // Вводим число 2
    }

    public void Three(View view) {
        formula.setText(getValue("3"));                       // Вводим число 3
    }

    public void Four(View view) {
        formula.setText(getValue("4"));                       // Вводим число 4
    }

    public void Five(View view) {
        formula.setText(getValue("5"));                       // Вводим число 5
    }

    public void Six(View view) {
        formula.setText(getValue("6"));                       // Вводим число 6
    }

    public void Seven(View view) {
        formula.setText(getValue("7"));                       // Вводим число 7
    }

    public void Eight(View view) {
        formula.setText(getValue("8"));                       // Вводим число 8
    }

    public void Nine(View view) {
        formula.setText(getValue("9"));                       // Вводим число 9
    }

    public void Zero(View view) {
        formula.setText(getValue("0"));                       // Вводим число 0
    }


    ////////////// Знаки //////////////

    public void Parentheses(View view) {

        int left = 0, right = 0;
        for (char element : formula.getText().toString().toCharArray())    // Переобразовует формулу в массив
        {
            if (element == '(') left++;                              // Подсчет кол-ва скобок (
            if (element == ')') right++;                             // Подсчет кол-ва скобок )
        }


        if (left == right)                                           // Если кол-во скобок равно, то
        {
            formula.setText(getValue("("));
        }             // Вводим (
        else                                                         // Если не равно, то
        {
            formula.setText(getValue(")"));
        }             // Вводим )

    }

    public void Percent(View view) {
        formula.setText(getValue("%"));                       // Вводим %
    }

    public void Slash(View view) {
        formula.setText(getValue("/"));                       // Вводим /
    }

    public void Star(View view) {
        formula.setText(getValue("*"));                       // Вводим *
    }

    public void Minus(View view) {
        formula.setText(getValue("-"));                       // Вводим -
    }

    public void Plus(View view) {
        formula.setText(getValue("+"));                       // Вводим +
    }

    public void Comma(View view) {
        formula.setText(getValue(","));                       // Вводим ,
    }

    public void Plus_minus(View view) {
        String value = formula.getText().toString();

        if (value.isEmpty())                                         // Проверяем не пустая ли строка
        {
            formula.setText(getValue("(-"));                  // Если пустая - добавляем скобку со знаком минус
        } else                                                         // Если не пустая, то выполняем следующую проверку
        {
            if (value == "(-")                                       // Если поле равно "(-"
            {
                formula.setText(getValue(""));                // Присваиваем полю пустое значение
            } else                                                     // Если не равно "(-"
            {
                return;                                              // return;
            }
        }
    }


    // Функция для перевода строки в числовой тип
    private Integer stringToInt(String string) {
        // возвращает число из строки
        return Integer.parseInt(string);
    }

    /**
     * Функция для получения текста в TextView
     *
     * @param string Значение для добавления в TextView
     * @return Готовое значение для TextView
     */
    private String getValue(String string) {
        return formula.getText().toString() + string;
    }
}