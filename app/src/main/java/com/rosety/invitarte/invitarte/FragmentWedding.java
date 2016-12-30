package com.rosety.invitarte.invitarte;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


public class FragmentWedding extends Fragment{
    private View myView;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar myToolbar;
    private CoordinatorLayout cl;
    private ImageView ivCollapsing;
    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_wedding, container, false);

        cl = (CoordinatorLayout) myView.findViewById(R.id.cl_wedding);
        collapsingToolbarLayout = (CollapsingToolbarLayout) myView.findViewById(R.id.ctl_toolbar_layout);
        collapsingToolbarLayout.setTitle("Gonzalo y Patricia");
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#ffffff"));
        ivCollapsing=(ImageView) myView.findViewById(R.id.iv_collapsing_toolbar);

        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference storageRef=storage.getReferenceFromUrl("gs://invitarte-d8a43.appspot.com/fotos_bodas/53587511/weddingg.jpg");

        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String coverPhotoUrl=uri.toString();
                Picasso.with(getActivity()).load(coverPhotoUrl).into(ivCollapsing);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Log.d("storage error",exception.toString());
            }
        });

        mainActivity = (MainActivity) getActivity();

        myToolbar = (Toolbar) myView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(myToolbar);

        myToolbar.setTitle("Gonzalo");
        DrawerLayout mDrawerLayout = mainActivity.getDrawerLayout();

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(), mDrawerLayout, myToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        ) {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            public void onDrawerOpened(View drawerView) {

            }
        };

        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"now",Toast.LENGTH_SHORT).show();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

        final CardView cvConfirmation=(CardView) myView.findViewById(R.id.cv_wedding_confirmation);
        Button btConfYes = (Button) myView.findViewById(R.id.bt_confirmation_yes);
        Button btConfNo = (Button) myView.findViewById(R.id.bt_confirmation_no);
        final Button btConfMaybe = (Button) myView.findViewById(R.id.bt_confirmation_maybe);
        final LinearLayout llContainer= (LinearLayout) myView.findViewById(R.id.ll_wedding_container);

        btConfYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llContainer.removeView(cvConfirmation);
            }
        });

        btConfNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llContainer.removeView(cvConfirmation);
            }
        });

        btConfMaybe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btConfMaybe.setBackgroundResource(R.color.colorPrimaryVeryDark);
            }
        });
        return myView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}