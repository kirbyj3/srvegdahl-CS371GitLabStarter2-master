package edu.up.cs371.vegdahl.gitlab;

/**
 * class GitModActivity
 *
 * Allow text to be modified in simple ways with button-presses, and
 * images to be displayed.
 */

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GitLabActivity extends ActionBarActivity implements View.OnClickListener{

    protected Button copyButton = null;
    protected Spinner spinner = null;
    protected EditText editText = null;
    protected Button  upperButton = null;
    protected Button button4 = null;
    protected Button punctButton = null;
    protected Button noSpaceButton = null;


    // array-list that contains our images to display
    private ArrayList<Bitmap> images;

    // instance variables containing widgets
    private ImageView imageView; // the view that shows the image

    protected Button button;

    protected Button button7;
    protected Button alternateButton;

    /**
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */

    protected void onCreate(Bundle savedInstanceState) {

        // perform superclass initialization; load the layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_lab);

        copyButton = (Button)findViewById(R.id.copyButton);
        copyButton.setOnClickListener(this);
        button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(this);
        punctButton = (Button)findViewById(R.id.punctButton);
        punctButton.setOnClickListener(this);

        alternateButton= (Button)findViewById(R.id.alternateButton);
        alternateButton.setOnClickListener(this);

        upperButton = (Button)findViewById(R.id.upperButton);
        upperButton.setOnClickListener(this);
        noSpaceButton = (Button)findViewById(R.id.noSpaceButton);
        noSpaceButton.setOnClickListener(this);


        editText = (EditText)findViewById(R.id.editText);

        // set instance variables for our widgets
        imageView = (ImageView)findViewById(R.id.imageView);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);

        button7 = (Button)findViewById(R.id.button7);
        button7.setOnClickListener(this);

        editText = (EditText)findViewById(R.id.editText);

        // Set up the spinner so that it shows the names in the spinner array resources
        //
        // get spinner object
        spinner = (Spinner)findViewById(R.id.spinner);
        // get array of strings
        String[] spinnerNames = getResources().getStringArray(R.array.spinner_names);
        // create adapter with the strings
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, spinnerNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // bind the spinner and adapter
        spinner.setAdapter(adapter);

        // load the images from the resources
        //
        // create the arraylist to hold the images
        images = new ArrayList<Bitmap>();
        // get array of image-resource IDs
        TypedArray imageIds2 = getResources().obtainTypedArray(R.array.imageIdArray);
        // loop through, adding one image per string
        for (int i = 0; i < spinnerNames.length; i++) {
            // determine the index; use 0 if out of bounds
            int id = imageIds2.getResourceId(i,0);
            if (id == 0) id = imageIds2.getResourceId(0,0);
            // load the image; add to arraylist
            Bitmap img = BitmapFactory.decodeResource(getResources(), id);
            images.add(img);
        }

        // define a listener for the spinner
        spinner.setOnItemSelectedListener(new MySpinnerListener());

    }

    /**
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_git_lab, menu);
        return true;
    }

    /**
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.copyButton) {
            String text = spinner.getSelectedItem().toString();
            editText.append(text);
        }
        else if(v.getId()== R.id.upperButton) {
            editText.setText(editText.getText().toString().toUpperCase());
        }
        else if (v.getId() == R.id.button4){
            editText.setText(new StringBuilder(editText.getText().toString()).reverse());
        }
        if (v.getId() == R.id.button)
        {
            editText.setText("");
        }

        if (v.getId() == R.id.button7)
        {
            editText.setText(editText.getText().toString().toLowerCase());
        }

        if(v.getId() == R.id.punctButton)
        {
            String tmp = editText.getText().toString();
            tmp = tmp.replace(",", "");
            tmp = tmp.replace(".", "");
            tmp = tmp.replace(";", "");
            tmp = tmp.replace("!", "");
            tmp = tmp.replace("?", "");
            tmp = tmp.replace("(", "");
            tmp = tmp.replace(")", "");
            tmp = tmp.replace("{", "");
            tmp = tmp.replace("}", "");
            tmp = tmp.replace("[", "");
            tmp = tmp.replace("]", "");
            tmp = tmp.replace("<", "");
            tmp = tmp.replace(">", "");
            tmp = tmp.replace("%", "");
            editText.setText(tmp);
        }
         else if (v.getId() == R.id.alternateButton){

            Random rand = new Random();
            int n = rand.nextInt(editText.getText().toString().length())+1;
            char c = (char) (rand.nextInt(26) + 'a');
            //System.out.println(c);
            StringBuilder str = new StringBuilder(editText.getText().toString());
            str.insert(n, c);
            editText.setText(str);


        }
        else if (v.getId() == R.id.noSpaceButton) {
            editText.setText(editText.getText().toString().replaceAll("\\s+", ""));
        }
    }

    /**
     * class that handles our spinner's selection events
     */
    private class MySpinnerListener implements OnItemSelectedListener {

        /**
         * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(
         *                  android.widget.AdapterView, android.view.View, int, long)
         */
        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                   int position, long id) {
            // set the image to the one corresponding to the index selected by the spinner
            imageView.setImageBitmap(images.get(position));

        }

        /**
         * @see android.widget.AdapterView.OnItemSelectedListener#onNothingSelected(
         *                  android.widget.AdapterView)
         */
        @Override
        public void onNothingSelected(AdapterView<?> parentView) {
            // your code here
        }
    }





//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_git_lab);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_git_lab, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
