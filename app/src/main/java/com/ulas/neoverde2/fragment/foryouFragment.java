package com.ulas.neoverde2.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ulas.neoverde2.R;
import com.ulas.neoverde2.databinding.FragmentForyouBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link foryouFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class foryouFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FragmentForyouBinding binding;
    public foryouFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment foryouFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static foryouFragment newInstance(String param1, String param2) {
        foryouFragment fragment = new foryouFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_foryou, container, false);
        WebView webview = view.findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("https://neoverde.istanbul/");


        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        final int totalTime = 100;
        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while(jumpTime < totalTime) {
                    try {
                        sleep(200);
                        jumpTime += 10;
                        progressDialog.setProgress(jumpTime);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();


        webview.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //Toast.makeText(getContext(), "Sayfa yüklendi...", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                // Toast.makeText(getContext(), "Bir hata oluştu", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
        return view;
    }
}