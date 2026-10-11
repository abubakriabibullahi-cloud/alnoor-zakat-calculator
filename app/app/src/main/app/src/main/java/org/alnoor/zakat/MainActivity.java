package org.alnoor.zakat;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import android.text.InputType;
import java.util.Locale;

public class MainActivity extends Activity {

private EditText cash, goldGrams, goldPrice;
private EditText silverGrams, silverPrice;
private EditText businessAssets, debts;
private RadioGroup nisabGroup;
private TextView result;

private final int green = 0xFF126B43;
private final int goldColor = 0xFFC49A32;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ScrollView scroll = new ScrollView(this);

    LinearLayout layout = new LinearLayout(this);
    layout.setOrientation(LinearLayout.VERTICAL);
    layout.setPadding(24, 24, 24, 32);
    layout.setBackgroundColor(0xFFF8F7F0);
    scroll.addView(layout);

    TextView title = new TextView(this);
    title.setText("AL-NOOR ZAKAT\nAND CHARITY FOUNDATION");
    title.setTextSize(23);
    title.setTypeface(null, Typeface.BOLD);
    title.setTextColor(green);
    title.setGravity(Gravity.CENTER);
    layout.addView(title);

    TextView subtitle = new TextView(this);
    subtitle.setText("ZAKAT CALCULATOR");
    subtitle.setTextSize(21);
    subtitle.setTypeface(null, Typeface.BOLD);
    subtitle.setTextColor(goldColor);
    subtitle.setGravity(Gravity.CENTER);
    subtitle.setPadding(0, 12, 0, 4);
    layout.addView(subtitle);

    TextView tagline = new TextView(this);
    tagline.setText("Your Zakat. Your Sadaqah. Their Hope.");
    tagline.setTextSize(14);
    tagline.setGravity(Gravity.CENTER);
    tagline.setTextColor(green);
    layout.addView(tagline);

    addLabel(layout, "\nEnter your wealth in Nigerian Naira (₦)");

    cash = addInput(layout, "Cash and savings (₦)");
    goldGrams = addInput(layout, "Gold owned (grams)");
    goldPrice = addInput(layout, "Gold price per gram (₦)");
    silverGrams = addInput(layout, "Silver owned (grams)");
    silverPrice = addInput(layout, "Silver price per gram (₦)");
    businessAssets = addInput(layout, "Eligible business assets (₦)");
    debts = addInput(layout, "Eligible immediate debts (₦)");

    addLabel(layout, "\nSelect your Nisab basis");

    nisabGroup = new RadioGroup(this);
    nisabGroup.setOrientation(RadioGroup.VERTICAL);

    RadioButton goldOption = new RadioButton(this);
    goldOption.setText("Gold Nisab — 85 grams");
    goldOption.setId(1);

    RadioButton silverOption = new RadioButton(this);
    silverOption.setText("Silver Nisab — 595 grams");
    silverOption.setId(2);
    silverOption.setChecked(true);

    nisabGroup.addView(goldOption);
    nisabGroup.addView(silverOption);
    layout.addView(nisabGroup);

    Button calculate = new Button(this);
    calculate.setText("CALCULATE MY ZAKAT");
    calculate.setTextColor(0xFFFFFFFF);
    calculate.setBackgroundTintList(
        android.content.res.ColorStateList.valueOf(green)
    );
    layout.addView(calculate);

    result = new TextView(this);
    result.setTextSize(17);
    result.setTextColor(green);
    result.setPadding(0, 24, 0, 24);
    layout.addView(result);

    Button share = new Button(this);
    share.setText("SHARE MY RESULT");
    layout.addView(share);

    share.setOnClickListener(v -> {
        String text = result.getText().toString();

        if (text.trim().isEmpty()) {
            Toast.makeText(
                this,
                "Calculate your Zakat first.",
                Toast.LENGTH_SHORT
            ).show();
            return;
        }

        android.content.Intent intent =
            new android.content.Intent(
                android.content.Intent.ACTION_SEND
            );

        intent.setType("text/plain");
        intent.putExtra(
            android.content.Intent.EXTRA_TEXT,
            text + "\n\nAl-Noor Zakat and Charity Foundation\n" +
            "https://alnoorzakatfoundation.org"
        );

        startActivity(
            android.content.Intent.createChooser(
                intent,
                "Share Zakat Result"
            )
        );
    });

    TextView website = new TextView(this);
    website.setText(
        "\nVisit our website:\nalnoorzakatfoundation.org"
    );
    website.setTextSize(15);
    website.setTextColor(green);
    website.setGravity(Gravity.CENTER);
    layout.addView(website);

    TextView note = new TextView(this);
    note.setText(
        "\nIMPORTANT NOTE\n" +
        "Enter current metal prices manually in this version. " +
        "Automatic online price updates will be added separately. " +
        "This is an estimate, not a final religious ruling. " +
        "Nisab, eligible assets, debts and the completion of one " +
        "lunar year may require guidance from a qualified scholar."
    );
    note.setTextSize(13);
    note.setTextColor(0xFF555555);
    layout.addView(note);

    calculate.setOnClickListener(v -> calculateZakat());

    setContentView(scroll);
}

private void addLabel(LinearLayout layout, String text) {
    TextView label = new TextView(this);
    label.setText(text);
    label.setTextSize(16);
    label.setTextColor(green);
    label.setTypeface(null, Typeface.BOLD);
    label.setPadding(0, 12, 0, 8);
    layout.addView(label);
}

private EditText addInput(LinearLayout layout, String hint) {
    EditText input = new EditText(this);
    input.setHint(hint);
    input.setSingleLine(true);
    input.setInputType(
        InputType.TYPE_CLASS_NUMBER |
        InputType.TYPE_NUMBER_FLAG_DECIMAL
    );
    layout.addView(input);
    return input;
}

private double number(EditText field) {
    try {
        String value = field.getText().toString().trim();

        if (value.isEmpty()) {
            return 0;
        }

        return Double.parseDouble(value);

    } catch (Exception e) {
        return 0;
    }
}

private void calculateZakat() {
    double cashValue = number(cash);
    double goldWeight = number(goldGrams);
    double goldRate = number(goldPrice);
    double silverWeight = number(silverGrams);
    double silverRate = number(silverPrice);
    double businessValue = number(businessAssets);
    double eligibleDebts = number(debts);

    if (cashValue < 0 || goldWeight < 0 || goldRate < 0 ||
        silverWeight < 0 || silverRate < 0 ||
        businessValue < 0 || eligibleDebts < 0) {

        result.setText("Please enter non-negative amounts.");
        return;
    }

    if ((goldWeight > 0 && goldRate <= 0) ||
        (silverWeight > 0 && silverRate <= 0)) {

        result.setText(
            "Enter the current price per gram for each metal owned."
        );
        return;
    }

    double goldValue = goldWeight * goldRate;
    double silverValue = silverWeight * silverRate;

    double netWealth = cashValue + goldValue + silverValue
        + businessValue - eligibleDebts;

    if (netWealth < 0) {
        netWealth = 0;
    }

    double nisab;

    if (nisabGroup.getCheckedRadioButtonId() == 1) {
        if (goldRate <= 0) {
            result.setText(
                "Enter the gold price to calculate the gold Nisab."
            );
            return;
        }

        nisab = 85 * goldRate;

    } else {
        if (silverRate <= 0) {
            result.setText(
                "Enter the silver price to calculate the silver Nisab."
            );
            return;
        }

        nisab = 595 * silverRate;
    }

    double zakat = netWealth >= nisab
        ? netWealth * 0.025
        : 0;

    result.setText(
        "AL-NOOR ZAKAT CALCULATION\n\n" +
        "Eligible net wealth: ₦" +
        String.format(Locale.US, "%,.2f", netWealth) +
        "\nNisab threshold: ₦" +
        String.format(Locale.US, "%,.2f", nisab) +
        "\n\nEstimated Zakat payable: ₦" +
        String.format(Locale.US, "%,.2f", zakat) +
        (netWealth >= nisab
            ? "\n\nRate applied: 2.5%"
            : "\n\nYour entered wealth is below the selected Nisab.")
    );
}

}
