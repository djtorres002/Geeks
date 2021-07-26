package com.example.geeks.models;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.geeks.R;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;

public class NewPostActivity extends AppCompatActivity {

    public static final String TAG = "PostActivity";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public String photoFileName = "photo.jpg";
    File photoFile;
    Button btCamara;
    Button btMedia;
    Button btPost;
    EditText etDescription;
    ImageView ivGameImage;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        btCamara = findViewById(R.id.btCamara);
        ivGameImage = findViewById(R.id.ivGameImage);
        btPost = findViewById(R.id.btPost);
        etDescription = findViewById(R.id.etDescription);

        btPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etDescription.getText().toString().isEmpty() || ivGameImage.getDrawable() == null){
                    return;
                } else {
                    String description = etDescription.getText().toString();
                    ParseUser user = ParseUser.getCurrentUser();
                    savePost(description, user, photoFile, ivGameImage, context);
                }
            }
        });

        btCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Log.d(TAG, "onClick: camera");
                onLaunchCamara();}
        });
    }

    private void onLaunchCamara() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "Failure taking a photo:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivGameImage.setImageBitmap(imageBitmap);
        }
    }

    private void savePost(String description, ParseUser user, File photoFile, ImageView ivGameImage, Context context) {
        Post post = new Post();
        post.setDescription(description);
        post.setUser(user);
        post.setImage(new ParseFile(photoFile));
        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    etDescription.setText("");
                    ivGameImage.setImageDrawable(null);
                    Toast.makeText(context, "Successfully post this!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Unable to post this! " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}