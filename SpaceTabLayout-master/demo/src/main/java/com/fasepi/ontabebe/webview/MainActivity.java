package com.fasepi.ontabebe.webview;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eu.long1.spacetablayout.SpaceTabLayout;


public class MainActivity extends AppCompatActivity {
    SpaceTabLayout tabLayout;
    public static final int REQUEST_STORAGE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add the fragments you want to display in a List
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentA());
        fragmentList.add(new FragmentB());
        fragmentList.add(new FragmentC());
        fragmentList.add(new FragmentD());
        //fragmentList.add(new FragmentE());




        if (weHavePermissionToReadContacts() == false) {
            Log.i("Record", ": ---------------------ENTRA A REVISAR PERMISOS : ");
            ChequearPermiso();


        }


        /*
        String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA};
        if (Build.VERSION.SDK_INT >= 23) {
            if (!hasPermissions(this, PERMISSIONS)) {
                ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST_STORAGE);
            } else {
                Log.e("TAG", "Ya tiene los permisos");
            }
        }
        */



        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (SpaceTabLayout) findViewById(R.id.spaceTabLayout);

        tabLayout.initialize(viewPager, getSupportFragmentManager(), fragmentList);

        tabLayout.setTabOneOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Welcome to SpaceTabLayout", Snackbar.LENGTH_SHORT);

                snackbar.show();
            }
        });

        tabLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "" + tabLayout.getCurrentPosition(), Toast.LENGTH_SHORT).show();
            }
        });
    }











    /******************************************************************************************************************************/
    /*************************************      PARA REVISAR LOS PERMISOS      ****************************************************/
    /******************************************************************************************************************************/
    private boolean weHavePermissionToReadContacts() {
        boolean permisos;
        boolean camera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        boolean capture = ContextCompat.checkSelfPermission(this, Manifest.permission.CAPTURE_AUDIO_OUTPUT) == PackageManager.PERMISSION_GRANTED;
        boolean write = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        boolean read = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;



        if (write && camera && capture && read) {
            permisos = true;
        } else {
            permisos = false;
        }
        //return ContextCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED;
        return permisos;
    }

    public void ChequearPermiso() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
            Log.i("Record", "NO TIENE HABILITADOS PERMISOS DE LA CAMARA");
        } else {
            Log.i("Record", "YA TIENE HABILITADOS PERMISOS DE LAS CAMARA");
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAPTURE_AUDIO_OUTPUT) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAPTURE_AUDIO_OUTPUT}, 2);
            Log.i("Record", "NO TIENE HABILITADOS PERMISOS DE CAPTURE_AUDIO_OUTPUT");
        } else {
            Log.i("Record", "YA TIENE HABILITADOS PERMISOS DE CAPTURE_AUDIO_OUTPUT");
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
            Log.i("Record", "NO TIENE HABILITADOS PERMISOS DE WRITE_EXTERNAL_STORAGE");
        } else {
            Log.i("Record", "YA TIENE HABILITADOS PERMISOS DE WRITE_EXTERNAL_STORAGE");
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 4);
            Log.i("Record", "NO TIENE HABILITADOS PERMISOS DE READ_EXTERNAL_STORAGE");
        } else {
            Log.i("Record", "YA TIENE HABILITADOS PERMISOS DE READ_EXTERNAL_STORAGE");
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Log.d("Record", " -----------------------onRequestPermissionsResult:   " + requestCode);
        switch (requestCode) {
            case 0:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
                    Log.i("Record", "NO TIENE HABILITADOS PERMISOS DE CAMERA");
                } else {
                    Log.i("Record", "YA TIENE HABILITADOS PERMISOS DE CAMERA");
                }
                break;
            case 1:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAPTURE_AUDIO_OUTPUT) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAPTURE_AUDIO_OUTPUT}, 2);
                    Log.i("Record", "NO TIENE HABILITADOS PERMISOS DE LEER CAPTURE_AUDIO_OUTPUT");
                } else {
                    Log.i("Record", "YA TIENE HABILITADOS PERMISOS DE LEER CAPTURE_AUDIO_OUTPUT");
                }
                break;

            case 2:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
                    Log.i("Record", "NO TIENE HABILITADOS PERMISOS DE WRITE_EXTERNAL_STORAGE");
                } else {
                    Log.i("Record", "YA TIENE HABILITADOS PERMISOS DE WRITE_EXTERNAL_STORAGE");
                }
                break;

            case 3:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 4);
                    Log.i("Record", "NO TIENE HABILITADOS PERMISOS DE READ_EXTERNAL_STORAGE");
                } else {
                    Log.i("Record", "YA TIENE HABILITADOS PERMISOS DE READ_EXTERNAL_STORAGE");
                }
                break;

        }
    }




    /*
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    /*get Permissions Result*/
    /*
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("TAG", "PERMISSION_GRANTED");
                } else {
                    Log.e("TAG", "The app was not allowed to write to your storage");
                }
            }

        }
    }
    */
}
