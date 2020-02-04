package id.ac.poliban.roman.capturingimage01;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.ivImage).setOnLongClickListener(v -> {
            captureImage(v);
            return true;
        });

    }

    public void captureImage(View view){
        //panggil intent MediaStore.ACTION_IMAGE_CAPTURE
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(captureIntent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(captureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap thumbBitmap = (Bitmap) extras.get("data");
            ((ImageView)findViewById(R.id.ivImage)).setImageBitmap(thumbBitmap);
        }
    }
}
