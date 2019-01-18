package com.example.android.newfoodorder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class Tab3Dessert extends Fragment {

    private ImageButton foodImage;
    private static final int GALLREQ = 1;
    private EditText name, desc, price;
    //for image
    private Uri uri = null;

    //image in firebase
    private StorageReference storageReference = null;

    //to store text in db
    private DatabaseReference mRef;
    private FirebaseDatabase firebaseDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3dessert, container, false);

        name = (EditText) rootView.findViewById(R.id.itemName);
        desc = (EditText) rootView.findViewById(R.id.itemDesc);
        price = (EditText) rootView.findViewById(R.id.itemPrice);
        //you can store image anywhere in the db, it does not matter

        storageReference = FirebaseStorage.getInstance().getReference();
        //but it does matter for the text
        mRef = FirebaseDatabase.getInstance().getReference("Starters");

        ImageButton ib = (ImageButton) rootView.findViewById(R.id.foodImageButton);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*"); //always used to start gallery intent
                startActivityForResult(galleryIntent,GALLREQ);
            }
        });

        Button c = (Button) rootView.findViewById(R.id.addItemButtonClicked);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name_text = name.getText().toString().trim();
                final String desc_text = desc.getText().toString().trim();
                final String price_text = price.getText().toString().trim();

                //upload only if the fields are not empty
                if(!TextUtils.isEmpty(name_text) && !TextUtils.isEmpty(desc_text) && !TextUtils.isEmpty(price_text))
                {
                    StorageReference filepath= storageReference.child(uri.getLastPathSegment());
                    filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            final Uri downloadurl = taskSnapshot.getDownloadUrl();
                            Toast.makeText(getActivity(), "Image uploaded", Toast.LENGTH_LONG).show();
                            //adding it in the database option of firebase
                            final DatabaseReference newPost = mRef.push();
                            newPost.child("name").setValue(name_text);
                            newPost.child("desc").setValue(desc_text);
                            newPost.child("price").setValue(price_text);
                            newPost.child("image").setValue(downloadurl.toString());
                        }
                    });
                }
            }

        });

        return rootView;
    }


  /*  public void imageButtonClicked(View view)
    {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*"); //always used to start gallery intent
        startActivityForResult(galleryIntent,GALLREQ);
    }*/

    //for image
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLREQ && resultCode==getActivity().RESULT_OK)
        {
            uri = data.getData();
            foodImage=(ImageButton) getView().findViewById(R.id.foodImageButton);
            foodImage.setImageURI(uri);
        }
    }




}



