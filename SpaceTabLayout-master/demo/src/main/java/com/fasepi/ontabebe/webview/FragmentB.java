package com.fasepi.ontabebe.webview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import java.io.InputStream;


public class FragmentB extends Fragment {





    private Bitmap imgTarjeton;
    private WebView myWebView;


    //String url = "https://ontabebe.shop";
    public FragmentB() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view = inflater.inflate(R.layout.fragment_b, container, false);


        myWebView = (WebView) view.findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());

        myWebView.loadUrl("https://ontabebe.shop");

/*
        if (imgTarjeton != null){


            ImageView tarjeton = (ImageView) view.findViewById(R.id.img_tarjeton);
            tarjeton.setImageBitmap(imgTarjeton);

        }else{



            String url = "https://ontabebe.shop";
            //String url = "http://taxislibres.com.co:8035/tcontrol/tarj.php?d="+ sharedPreferences.getString(Constantes.placas_vehiculo, "") + "|16471560";
            new DownloadImageTask((ImageView) view.findViewById(R.id.img_tarjeton)).execute(url);
        }
*/
        return view;
    }




    @Override
    public void onResume() {
        super.onResume();

    }



    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);

                //appState.setCodeQr(mIcon11);

            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
            imgTarjeton = result;

        }
    }
}