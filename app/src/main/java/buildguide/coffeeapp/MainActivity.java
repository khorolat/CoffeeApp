package buildguide.coffeeapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.coffeeapp.R;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        if (quantity == 100) {
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity == 0) {
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }


    public void submitOrder(View view) {

        EditText nameField = (EditText) findViewById(R.id.name_field);
        Editable nameEditable = nameField.getText();
        String name = nameEditable.toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox NoSugar_checkbox = (CheckBox) findViewById(R.id.NoSugar_checkbox);
        boolean hasNoSugar = NoSugar_checkbox.isChecked();

        CheckBox ExtraSugar_checkbox = (CheckBox) findViewById(R.id.ExtraSugar_checkbox);
        boolean hasExtraSugar = ExtraSugar_checkbox.isChecked();

        CheckBox SprinklesCheckbox = (CheckBox) findViewById(R.id.Sprinkles_checkbox);
        boolean hasSprinkles = SprinklesCheckbox.isChecked();

        CheckBox Mini_marshmallows_checkbox = (CheckBox) findViewById(R.id.Mini_marshmallows_checkbox);
        boolean hasMarshmallows = Mini_marshmallows_checkbox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        CheckBox ExtraMilkCheckBox = (CheckBox) findViewById(R.id.ExtraMilkcheckbox);
        boolean hasExtraMilk = ExtraMilkCheckBox.isChecked();

        CheckBox ExtraCoffeeCheckBox = (CheckBox) findViewById(R.id.ExtraCoffee_checkbox);
        boolean hasExtraCoffee = ExtraCoffeeCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate , hasExtraMilk , hasExtraCoffee, hasSprinkles ,hasMarshmallows ,hasExtraSugar, hasNoSugar);

        String message = createOrderSummary(name, price, hasWhippedCream, hasChocolate ,hasExtraMilk ,hasExtraCoffee, hasSprinkles,hasMarshmallows,hasExtraSugar, hasNoSugar);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,
                getString(R.string.order_summary_email_subject, name));
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate ,boolean addExtraMilk ,boolean addExtraCoffee ,boolean addSprinkles, boolean addMarshmallows, boolean addExtraSugar ,boolean addNoSugar) {

        int basePrice = 10;

        if (addWhippedCream) {
            basePrice = basePrice + 4;
        }

        if (addNoSugar) {
            basePrice = basePrice + 0;
        }

        if (addExtraSugar) {
            basePrice = basePrice + 1;
        }
        if (addExtraMilk) {
            basePrice = basePrice + 2;
        }

        if (addChocolate) {
            basePrice = basePrice + 4;
        }
        if (addExtraCoffee) {
            basePrice = basePrice + 2;
        }

        if (addSprinkles) {
            basePrice = basePrice + 4;
        }

        if (addMarshmallows) {
            basePrice = basePrice + 4;
        }

        return quantity * basePrice;
    }

    private String createOrderSummary(String name, int price, boolean addWhippedCream,
                                      boolean addChocolate ,boolean addExtraMilk ,boolean addExtraCoffee, boolean addSprinkles , boolean addMarshmallows, boolean addExtraSugar ,boolean addNoSugar) {
        String priceMessage = getString(R.string.order_summary_name, name);
        priceMessage += "\n" + getString(R.string.order_summary_whipped_cream, addWhippedCream);
        priceMessage += "\n" + getString(R.string.order_summary_chocolate, addChocolate);
        priceMessage += "\n" + getString(R.string.order_summary_ExtraMilk, addExtraMilk);
        priceMessage += "\n" + getString(R.string.order_summary_NoSugar, addNoSugar);
        priceMessage += "\n" + getString(R.string.order_summary_ExtraSugar, addExtraSugar);
        priceMessage += "\n" + getString(R.string.order_summary_Marshmallows, addMarshmallows);
        priceMessage += "\n" + getString(R.string.order_summary_ExtraCoffee, addExtraCoffee);
        priceMessage += "\n" + getString(R.string.order_summary_Sprinkles, addSprinkles);
        priceMessage += "\n" + getString(R.string.order_summary_quantity, quantity);
        priceMessage += "\n" + getString(R.string.order_summary_price,
               "R"+price);
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;
    }

    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }
}
