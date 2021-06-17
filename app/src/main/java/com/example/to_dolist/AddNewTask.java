package com.example.to_dolist;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.to_dolist.Model.ToDoModel;
import com.example.to_dolist.Utils.DataBaseHelper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddNewTask extends BottomSheetDialogFragment {
    public static final String TAG = "AddNewTask";
    //widgets
    private EditText mEdittext;
    private Button mSaveButton;
    private DataBaseHelper myDB;
    public static AddNewTask newInstance()
    {
        return new AddNewTask();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.add_newtask , container , false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEdittext=view.findViewById(R.id.edittext);
        mSaveButton= view.findViewById(R.id.button_save);
        myDB=new DataBaseHelper(getActivity());
        boolean isUpdate=false;
        Bundle bundle=getArguments();
        Log.d("aaaw","aaa");
        if(bundle!=null)
        {
            Log.d("aaa","aaa");
            isUpdate=true;
            String task=bundle.getString("task");
            mEdittext.setText(task);
            if(task.length()>0)
            {
                mSaveButton.setEnabled(false);
            }
            mEdittext.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.toString().equals(""))
                    {
                        mSaveButton.setEnabled(false);
                        mSaveButton.setBackgroundColor(Color.GRAY);
                    }
                    else{
                        mSaveButton.setEnabled(true);
                        mSaveButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            boolean finalIsUpdate = true;//isUpdate;
            mSaveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("aaa","aaa");
                    String text=mEdittext.getText().toString();
                    if(finalIsUpdate)
                    {
                        myDB.updateTask(bundle.getInt("id"), text);


                    }

                    Log.d("aaa","aaa");
                    dismiss();
                }

            });
        }
        else
        {
            Log.d("aaa","aaa");
            isUpdate=true;
//            String task=bundle.getString("task");
            // mEdittext.setText("");
//            if(task.length()>0)
//            {
//                mSaveButton.setEnabled(false);
//            }
//            mEdittext.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//                    if(s.toString().equals(""))
//                    {
//                        mSaveButton.setEnabled(false);
//                        mSaveButton.setBackgroundColor(Color.GRAY);
//                    }
//                    else{
//                        mSaveButton.setEnabled(true);
//                        mSaveButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
//                    }
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//
//                }
//            });
            boolean finalIsUpdate = false;//isUpdate;
            mSaveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("aaa","aaa");
                    String text=mEdittext.getText().toString();
                    if(finalIsUpdate)
                    {
                        //myDB.updateTask(bundle.getInt("id"), text);


                    }
                    else{
                        ToDoModel item = new ToDoModel();
                        item.setTask(text);
                        item.setStatus(0);
                        myDB.insertTask(item);
                    }
                    Log.d("aaa","aaa");
                    dismiss();
                }

            });
        }



    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity=getActivity();
        if(activity instanceof OnDialogCloseListner){
            ((OnDialogCloseListner)activity).onDialogClose(dialog);
        }
    }
}



