package com.example.taskapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.taskapp.MainActivity;
import com.example.taskapp.R;
import com.example.taskapp.models.Task;

public class FormFragment extends Fragment {

    EditText editText;
    NavController navController;
    private Task task;
    private boolean edit= false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.et_text);
        task = (Task)requireArguments().getSerializable("task");
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
        if (task != null){
            editText.setText(task.getTitle());
            edit = true;
        }
    }

    private void save() {
        String s = editText.getText().toString().trim();
        if (!s.isEmpty()){
            Task task = new Task(s,System.currentTimeMillis());
            Bundle bundle = new Bundle();
            bundle.putSerializable("task",task);
            bundle.putBoolean("edit",edit);
            getParentFragmentManager().setFragmentResult("form",bundle);
            navController.navigateUp();
        }else{
            editText.setError("Заполните это поле");
        }
    }

}