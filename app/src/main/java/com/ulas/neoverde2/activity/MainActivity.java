package com.ulas.neoverde2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.ulas.neoverde2.R;
import com.ulas.neoverde2.databinding.ActivityMainBinding;
import com.ulas.neoverde2.fragment.HomeFragment;
import com.ulas.neoverde2.fragment.ProfileFragment;
import com.ulas.neoverde2.fragment.RequestFragment;
import com.ulas.neoverde2.fragment.WebFragment;
import com.ulas.neoverde2.fragment.foryouFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new HomeFragment()).commit();

        SharedPreferences preferences = getSharedPreferences("prefs",MODE_PRIVATE);
        boolean firstStart = preferences.getBoolean("firstStart",true);


        binding.bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.request2));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.build2));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.building2));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.live2));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.new2));

        binding.bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()){
                    case 1:
                        fragment=new RequestFragment();
                        break;
                    case 2:
                        fragment=new foryouFragment();
                        break;
                    case 3:
                        fragment=new HomeFragment();
                        if (firstStart){
                            showAlertDialog();
                        }
                        break;
                    case 4:
                        fragment=new WebFragment();
                        setClick();
                        break;
                    case 5:
                        fragment=new ProfileFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });

        binding.bottomNavigation.setCount(1,"10");
        binding.bottomNavigation.show(2,true);
        binding.bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //Toast.makeText(getApplicationContext(), "You clicked"+item.getId(), Toast.LENGTH_SHORT).show();
            }
        });
        binding.bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //Toast.makeText(getApplicationContext(), "You reselected"+item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    boolean singleBack = false;

    @Override
    public void onBackPressed() {
        if (singleBack) {
            super.onBackPressed();
            return;
        }

        this.singleBack = true;
        Toast.makeText(this, "Double Back to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                singleBack=false;
            }
        }, 2000);
    }
    private void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
    }
    private void setClick(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Kartal Neoverde");
        builder.setCancelable(true);
        builder.setMessage("Bu alanda IP kamera işlemleri devam etmektedir.En kısa sürede yayına açılacaktır.");
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }
    private void showAlertDialog(){
        new AlertDialog.Builder(this).setTitle("Kartal neoverde")
                .setMessage("5.298.68 m² ada  arazi üzerine 27.500m² inşaat alanına sahip projede 1.200 m² yeşil alan 28  normal kat + zemin kat + 3  bodrum katta toplam 206 daire bulunmaktadır. Neoverde Kartal  benzersiz manzarası, hayranlık verici tasarımı ile sizlere bölge standartları üstünde bir yaşam sunuyor. Getirisi yüksek bir yatırım, yaşanacak keyifli bir konut, olmanın yanı sıra adalar manzarasına hakimdir.")
                .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create().show();
        SharedPreferences preferences = getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("firstStart",false);
        editor.apply();

    }
}