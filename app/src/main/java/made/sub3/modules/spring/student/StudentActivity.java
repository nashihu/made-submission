package made.sub3.modules.spring.student;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import made.sub3.R;
import made.sub3.adapters.StudentAdapter;
import made.sub3.model.Student;

public class StudentActivity extends AppCompatActivity {
    private StudentViewModel viewModel;
    RecyclerView recyclerView;
    private Observer<List<Student>> observer = new Observer<List<Student>>() {
        @Override
        public void onChanged(@Nullable List<Student> studentBasePageable) {

            StudentAdapter adapter = new StudentAdapter(studentBasePageable);
            recyclerView.setAdapter(adapter);


        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String auth = "Bearer " + intent.getStringExtra("token");
        setContentView(R.layout.activity_students);
        viewModel = new StudentViewModel();
        viewModel.hit(auth);
        viewModel.getData().observe(this,observer);
        recyclerView = findViewById(R.id.rv_stud);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
