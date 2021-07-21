package com.example.geeks.models;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
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
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 532;
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
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Create a File reference for future access
        photoFile = getPhotoFileUri(photoFileName);

        // wrap File object into a content provider
        // required for API >= 24
        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
        Uri fileProvider = FileProvider.getUriForFile(this, "com.codepath.fileprovider", photoFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

        Log.d(TAG, "onClick: Regiter");

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(this.getPackageManager()) != null) {
            // Start the image capture intent to take photo
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    private File getPhotoFileUri(String photoFileName) {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        File mediaStorageDir = new File(this.getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d(TAG, "failed to create directory");
        }

        // Return the file target for the photo based on filename
        File file = new File(mediaStorageDir.getPath() + File.separator + photoFileName);

        return file;
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