package made.sub3.modules.spring.student;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import made.sub3.model.BasePageable;
import made.sub3.model.Student;
import made.sub3.rest.API;
import made.sub3.rest.BaseFunction;
import retrofit2.Call;
import retrofit2.Response;

class StudentViewModel extends ViewModel {

    private MutableLiveData<List<Student>> liveData = new MutableLiveData<>();

    MutableLiveData<List<Student>> getData() {
        return liveData;
    }

    void hit(String auth) {
        API.mainItem().getStudents(auth,"0","10").enqueue(new retrofit2.Callback<BasePageable<Student>>() {
            @Override
            public void onResponse(Call<BasePageable<Student>> call, Response<BasePageable<Student>> response) {
                BaseFunction.proceed(call);
                BaseFunction.log("asdf", (response.body() != null ? response.body().getContent().size() : 0) +"");
                if (response.body() != null) {
                    liveData.postValue(response.body().getContent());
                }
            }

            @Override
            public void onFailure(Call<BasePageable<Student>> call, Throwable t) {
                BaseFunction.proceed(t);

            }
        });

    }


}
