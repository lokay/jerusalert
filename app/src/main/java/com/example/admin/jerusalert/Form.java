package com.example.admin.jerusalert;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Form extends Activity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = Form.class.getName();
    private TextView latituteField;
    private TextView categoryValue;

    private TextView longitudeField;
    private String provider;
    private LocationListener locationListener;
    private LocationManager locationManager;
    private ImageButton loc_btn;
    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg");

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Toast.makeText(Form.this, "hello", Toast.LENGTH_SHORT).show();
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }


    @TargetApi(23)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        categoryValue = (TextView) findViewById(R.id.categoryValue);
        SharedPreferences sp = getSharedPreferences("categories", Context.MODE_PRIVATE);
        String val = sp.getString("categories", null);
        if(val != null){
            categoryValue.setText(val);

        }else{
            categoryValue.setText("xxxxxxx");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("שריפה");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        //send button
        final Context context = this;

        Button send_btn = (Button) findViewById(R.id.send);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Form.this, "תודה שדיווחת", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, MainScreen.class);
                startActivity(intent);
            }
        });

        //camera button
        ImageButton camera_btn = (ImageButton) findViewById(R.id.camera);
        camera_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        //location button
        loc_btn = (ImageButton) findViewById(R.id.location);
        loc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("a", "onclick");
//                locationManager.requestLocationUpdates(provider, 50, 1, Form.this);
                myLocation();
            }
        });
    }


    public void myLocation() {
        latituteField = (TextView) findViewById(R.id.textView1);
        longitudeField = (TextView) findViewById(R.id.textView2);

        // Get the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria c = new Criteria();
        c.setAccuracy(Criteria.ACCURACY_COARSE);
        provider = locationManager.getBestProvider(c, false);
        Location location = locationManager.getLastKnownLocation(provider);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                onLocationChanged1(location);            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                Log.d("a", "d");

            }

            @Override
            public void onProviderEnabled(String s) {
                Log.d("a", "c");

            }

            @Override
            public void onProviderDisabled(String s) {
                Log.d("a", "b");

            }
        });
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                onLocationChanged1(location);            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                Log.d("a", "d");

            }

            @Override
            public void onProviderEnabled(String s) {
                Log.d("a", "c");

            }

            @Override
            public void onProviderDisabled(String s) {
                Log.d("a", "b");

            }
        });

        // Initialize the location fields
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged1(location);
        } else {
            latituteField.setText("Location not available");
            longitudeField.setText("Location not available");
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        //save subcategory
        //subCategory = parent.getContext();

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }



    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
//        locationManager.removeUpdates(this);
    }


    public void onLocationChanged1(Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        latituteField.setText(String.valueOf(lat));
        longitudeField.setText(String.valueOf(lng));
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }

    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}