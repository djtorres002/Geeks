package com.example.geeks.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.geeks.R;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class ProfileFragment extends Fragment {

    public static final String TAG = "ProfileFragment";

    Context context;
    ImageView ivProfilePic;
    TextView tvUsername;
    Button btActiveT;
    String currentPhotoPath;
    Uri photoUri;
    File photoFile;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public String photoFileName = "photo.jpg";

    View view;
    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(Context context) {
        ProfileFragment fragment = new ProfileFragment();
        fragment.context = context;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivProfilePic = view.findViewById(R.id.ivProfilePic);
        tvUsername = view.findViewById(R.id.tvUsername);
        Glide.with(context)
                .load(ParseUser.getCurrentUser().getParseFile("userPic").getUrl())
                .override(40, 40)
                .centerCrop()
                .circleCrop()
                .into(ivProfilePic);
        tvUsername.setText(ParseUser.getCurrentUser().getUsername());
        btActiveT = view.findViewById(R.id.btActiveT);
        btActiveT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLaunchCamara();
            }
        });
        ivProfilePic.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                goToChangeProfilePic(v);
                return false;
            }
        });
    }

    private void goToChangeProfilePic(View v) {
        Toast.makeText(context, "Change your profile picture!", Toast.LENGTH_SHORT).show();
        PopupMenu popup = new PopupMenu(context, v);
        popup.inflate(R.menu.popup_filters);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_keyword:
                        onLaunchCamara();
                        ParseUser user = ParseUser.getCurrentUser();
                        saveNewProfilePic(user, photoFile, ivProfilePic);
                        return true;
                    case R.id.menu_popularity:
                        Toast.makeText(context, "Popularity!", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popup.show();
    }

    private void onLaunchCamara() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            //File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                photoUri = FileProvider.getUriForFile(context,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    private void saveNewProfilePic(ParseUser user, File photoFile, ImageView ivProfilePic) {
        user.put("userPic", photoFile);
        user.saveInBackground(e -> {
            if(e == null){
                Toast.makeText(context, "Save Successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Glide.with(context)
                .load(ParseUser.getCurrentUser().getParseFile("userPic").getUrl())
                .override(40, 40)
                .centerCrop()
                .circleCrop()
                .into(ivProfilePic);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            ivProfilePic.setImageURI(photoUri);
        }
    }
}