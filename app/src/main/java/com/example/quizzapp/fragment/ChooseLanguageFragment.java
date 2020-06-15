package com.example.quizzapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.quizzapp.R;
import com.example.quizzapp.helper.CustomSpinnerAdapter;
import com.example.quizzapp.helper.InputValidation;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class ChooseLanguageFragment  extends Fragment implements AdapterView.OnItemSelectedListener {

    // Text shown in spinner
    private String[] languages = { "Javascript" , "C++", "Java" };
    // Images from res/drawable folder
    private int icons[] = {R.drawable.js_icon, R.drawable.c_icon, R.drawable.java};

    private String selectedLanguage;
    private int selectedIcon;
    private TextInputEditText textInputEditTextName;
    private TextInputLayout textInputLayout;
    private InputValidation inputValidation;
    SendLanguage sendLanguage;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_language, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spinner spinner = (Spinner) view.findViewById(R.id.simpleSpinner);
        spinner.setOnItemSelectedListener(this);
        textInputEditTextName = view.findViewById(R.id.textInputEditTextName);
        textInputLayout = view.findViewById(R.id.textInputLayoutName);
        inputValidation = new InputValidation(this.getActivity());
        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(view.getContext(), icons, languages);
        spinner.setAdapter(customSpinnerAdapter);
        view.findViewById(R.id.nextfab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkData()) {
                    sendLanguage.sendData(selectedLanguage, textInputEditTextName.getText().toString());
                    NavHostFragment.findNavController(ChooseLanguageFragment.this)
                            .navigate(R.id.action_ChooseLanguageFragment_to_createFragment);
                }
            }
        });
    }

    private boolean checkData(){

            if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayout, getString(R.string.error_message_name))) {
                return false;
            }
            return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id)

    {
        selectedIcon = icons[position];
        selectedLanguage = languages[position];

    }

    // not needed
    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {}

   public  interface SendLanguage {
        void sendData(String language, String quizzName);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            sendLanguage = (SendLanguage) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }
}
