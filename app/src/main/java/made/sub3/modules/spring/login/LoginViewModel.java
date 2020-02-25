package made.sub3.modules.spring.login;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import made.sub3.model.User;
import made.sub3.rest.API;
import made.sub3.rest.BaseFunction;
import retrofit2.Call;
import retrofit2.Response;

class LoginViewModel extends ViewModel {

    private MutableLiveData<String> liveData = new MutableLiveData<>();

    MutableLiveData<String> getData() {
        return liveData;
    }

    void login(User user) {
        API.mainItem().login(user).enqueue(new retrofit2.Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                BaseFunction.proceed(call);
                if(response.code()==200) {
                    liveData.postValue(response.body() != null ? response.body().getToken() : null);
                    return;
                }
                liveData.postValue(null);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                BaseFunction.proceed(t);
            }
        });
    }
}
